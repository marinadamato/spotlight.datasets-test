
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.String;



public class Main {

    public static void main(String[] args) {

        configProperties prop = new configProperties();
        createDataset test;
        test = new createDataset();
	prop.setArgs(args);

        BufferedReader labelsIn=prop.getFileIn("labels");
        BufferedWriter labelsOut=prop.getFileOut("labels");
        BufferedReader labelsIn2=prop.getFileIn("labels");
        BufferedWriter labelsOut2=prop.getFileOut("labels");
        BufferedReader redirectsIn=prop.getFileIn("redirects");
        BufferedWriter redirectsOut=prop.getFileOut("redirects");
        BufferedReader disambiguationsIn=prop.getFileIn("disambiguations");
        BufferedWriter disambiguationsOut=prop.getFileOut("disambiguations");
        BufferedReader instanceIn=prop.getFileIn("instance_types");
        BufferedWriter instanceOut=prop.getFileOut("instance_types");
        BufferedReader imagesIn=prop.getFileIn("images");
        BufferedWriter imagesOut=prop.getFileOut("images");
        BufferedReader linksIn=prop.getFileIn("links");
        BufferedWriter linksOut=prop.getFileOut("links");
        BufferedReader wikiDumpIn=prop.getFileIn("wikiDump");
        BufferedWriter wikiDumpOut=prop.getFileOut("wikiDump");

        System.out.println("Reading labels_en.nt...");
        test.readFile(labelsIn, labelsOut);
        System.out.println("Finding redirections URIs...");
        test.findRedirects(redirectsIn, redirectsOut);
        System.out.println("Finding good URIs...");
        test.findGoodURIs(labelsIn2, labelsOut2);
        System.out.println("Reading instance_types_en.nt...");
        test.findLabels(instanceIn, instanceOut);
        System.out.println("Reading disambiguations_en.nt...");
        test.findLabels(disambiguationsIn, disambiguationsOut);
        System.out.println("Reading images_en.nt...");
        test.findLabels(imagesIn, imagesOut);
        System.out.println("Reading page_links_en.nt...");
        test.findLinks(linksIn, linksOut);
        System.out.println("Reading Wikipedia Dump...");
        test.wikiDump(wikiDumpIn, wikiDumpOut);


    }
}
