import java.io.*;
import java.lang.String;
import java.util.Properties;

public class configProperties {

    private String[] args;
    private String inputPath;
    private String outputPath;
    private String[] paths;
    private int cnt=0;
    Properties config = new Properties();

    public void setArgs(String[] args){
        this.args=args;
    }

    public BufferedReader getFileIn(String file){
        BufferedReader in=null;
        String type="in";
        String inputPath=setProperties(file, type);
        try{
            in = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
        }catch (IOException e){
            e.printStackTrace();
        }
       return in;
    }

    public BufferedWriter getFileOut(String file){
        BufferedWriter out=null;
        String type="out";
        String outputPath=setProperties(file, type);

        if(file.equals("labels"))
            cnt++;

        try{
            out= new BufferedWriter (new FileWriter(outputPath, false));
            if(file.equals("labels")&&cnt>1)
                out= new BufferedWriter (new FileWriter(outputPath, true));
        }catch (IOException e){
            e.printStackTrace();
        }
        return out;
    }


    private String setProperties(String file, String type) {

        String path="";
        try {

            InputStream input = new FileInputStream(new File(args[0]));
            config.load(input);

            switch(file)
            {
                case "labels":
                    paths= labelsProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "redirects":
                    paths= redirectsProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "disambiguations":
                    paths= disambiguationsProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "instance_types":
                    paths= instance_typesProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "links":
                    paths= linkProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "images":
                    paths= imagesProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

                case "wikiDump":
                    paths=wikiDumpProperties(input).split("\\+");
                    inputPath=paths[0];
                    outputPath=paths[1];
                break;

            }

            switch(type)
            {
                case "in":
                    path= inputPath;
                break;

                case "out":
                    path= outputPath;
                break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    private String labelsProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.labels", "").trim();
        String outputPath = config.getProperty("datasets.data.output.labels", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String redirectsProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.redirects", "").trim();
        String outputPath = config.getProperty("datasets.data.output.redirects", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String disambiguationsProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.disambiguations", "").trim();
        String outputPath = config.getProperty("datasets.data.output.disambiguations", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String instance_typesProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.instance_types", "").trim();
        String outputPath = config.getProperty("datasets.data.output.instance_types", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String wikiDumpProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.wikiDump", "").trim();
        String outputPath = config.getProperty("datasets.data.output.wikiDump", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String linkProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.links", "").trim();
        String outputPath = config.getProperty("datasets.data.output.links", "").trim();

        return redirectPath+"+"+outputPath;
    }

    private String imagesProperties(InputStream input){
        String redirectPath = config.getProperty("datasets.data.images", "").trim();
        String outputPath = config.getProperty("datasets.data.output.images", "").trim();

        return redirectPath+"+"+outputPath;
    }

}
