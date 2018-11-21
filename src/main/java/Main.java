
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

        test.readFile(labelsIn, labelsOut);
        test.findRedirects(redirectsIn, redirectsOut);
        test.findGoodURIs(labelsIn2, labelsOut2);
        test.findLabels(instanceIn, instanceOut);
        test.findLabels(disambiguationsIn, disambiguationsOut);
        test.findLabels(imagesIn, imagesOut);
        test.findLinks(linksIn, linksOut);
        test.wikiDump(wikiDumpIn, wikiDumpOut);


    }
}
