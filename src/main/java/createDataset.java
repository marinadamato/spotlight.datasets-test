import java.io.*;
import java.lang.String;
import java.util.*;

public class createDataset {

    private int N=10;
    private int cnt=0;
    private int flag=0;
    private int flag2=0;
    private int flag3=0;
    private ArrayList labels=new ArrayList();
    private ArrayList redirects=new ArrayList();
    private ArrayList title=new ArrayList();
    private String[] args;

    /*
        readFile reads the labels_en.nt file, copies the first N URIs in the output file and saves the N labels
        in an ArrayList which is useful for the next steps
    */

    protected void readFile(BufferedReader in, BufferedWriter out) {

        Properties config = new Properties();

        try {

            String line;

            while ((line = in.readLine()) != null && cnt <= N) {
                Scanner s = new Scanner(line);
                s.useDelimiter(" ");

                String label = s.next();

                out.write(line);
                out.newLine();

                if (cnt > 0) {
                    labels.add(label);

                    addTitle(label);
                }


                s.close();
                cnt++;


            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
         findRedirects selects the lines of redirects_en.nt related to the labels saved before and copies them in the output file;
         it also saves the redirection labels into a new ArrayList
     */

    protected void findRedirects(BufferedReader in, BufferedWriter out)
    {

        Properties config=new Properties();

        try

        {
            String line;

            while((line=in.readLine())!=null){

                if(line.contains("#"))
                {
                    out.write(line);
                    out.newLine();
                }

                Scanner s = new Scanner(line);
                s.useDelimiter(" ");

                String label=s.next();
                String unused=s.next();
                String redirect=s.next();

                if(labels.contains(label))
                {
                    out.write(line);
                    out.newLine();
                    redirects.add(redirect);

                }

                s.close();
            }
            out.close();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /*
         findLabels is used to analyze the instance_types_en.nt, disambiguations_en.nt and images_en.nt files and it saves the lines
         of these files which contain references to the original or redirection labels of the two ArrayList
     */

    protected void findLabels(BufferedReader in, BufferedWriter out)
    {

        try
        {

            String line;

            while((line=in.readLine())!=null){

                Scanner s = new Scanner(line);
                s.useDelimiter(" ");

                String label=s.next();

                if(redirects.contains(label) || labels.contains(label))
                {
                    out.write(line);
                    out.write("\n");
                }


                s.close();
            }
            out.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /*
         findLinks works exactly like the previous method, but it also contains the addTitle method whose functionalities are explained after
     */

    protected void findLinks(BufferedReader in, BufferedWriter out)
    {
        try
        {
            String line;

            while((line=in.readLine())!=null){
                Scanner s = new Scanner(line);
                s.useDelimiter(" ");

                String label=s.next();
                String unused=s.next();
                String link=s.next();

                if(redirects.contains(label) || labels.contains(label))
                {
                    out.write(line);
                    out.write("\n");
                    addTitle(link);
                }

                s.close();

            }
            out.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /*
         addTitle modifies the URIs structure: it extracts the label title from the URI and it adds a tag which makes
         searching the entities in the Wikipedia Dump simple
     */

    private void addTitle(String label)
    {
        String erase1="<http://dbpedia.org/resource/";
        String erase2=">";

        String r1 = label.replace(erase1, "");
        String r2 = r1.replace(erase2, "");
        String r3 = "<title>" + r2 + "</title>";

        title.add(r3);
    }

    /*
         wikiDump reads all the XML file in order to select all the dump sections related to the original entities
         or the entities found in the findLinks method
     */

    protected void wikiDump(BufferedReader in, BufferedWriter out)
    {
        try
        {
            String line;

            while((line=in.readLine())!=null){

                if(flag3==0 && !(line.contains("<page>")))
                {
                    out.write(line);
                    out.write("\n");
                }

                if(line.contains("<page>"))
                {
                    flag=0;
                    flag2=0;
                    flag3=1;

                    while((line=in.readLine())!=null && !(line.contains("</page>")))
                    {

                        String linea2=line.trim();
                        if(!(title.contains(linea2)) && flag==0)
                            break;

                        if(flag==0)
                        {
                            out.write("<page>");
                            out.write("\n");
                            flag=1;
                        }

                        out.write(line);
                        out.write("\n");

                        flag2=1;
                    }


                    if(flag2==1)
                    {
                        out.write("</page>");
                        out.write("\n");
                    }

                }

            }

            out.write("</mediawiki>");
            out.close();

        }catch(IOException e){System.out.println(e.getMessage());}

    }

    /*
         findGoodURIs is used to find concept URIs: it selects the lines of the labels_en.nt file which contain the good URIs
         found in the redirects_en.nt file and saved in the redirects ArrayList
     */

    protected void findGoodURIs(BufferedReader in, BufferedWriter out)
    {
        try

        {
            String line, lastLine="";

            while((line=in.readLine())!=null) {

                Scanner s = new Scanner(line);
                s.useDelimiter(" ");

                String label = s.next();

                if (redirects.contains(label)) {
                    out.write(line);
                    out.write("\n");

                }
                lastLine=line;
            }
            out.write(lastLine);
            out.close();

        }catch(IOException e){System.out.println(e.getMessage());}

    }
}

