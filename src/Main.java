/**
 * Version 1.13 (Now dumps arrays to -dump.txt if supplied the -dump argument)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 8/7/2014
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Main {

    static CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');

    public static void main(String args[]) throws Exception {
        String filePath = "";
        List<String> passwords;
        boolean dump = false;

        try {
            filePath = args[0];
            if (args[1].toLowerCase().equals("dump"))
                dump = true;
        } catch(IndexOutOfBoundsException ex) {
            System.err.println("Usage: <filepath> [mode]");
            System.exit(1);
        }

        // Read the list of passwords
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        passwords = Arrays.asList(br.lines().toArray(String[]::new));

        // Compute the character-position statistics of the passwords and output
        CharStats charStats = new CharStats(passwords);
        if(!dump) {
            String charCountsFilePath = filePath + "-counts.csv";
            outputCharCounts(charCountsFilePath, charStats);
            String charWeightsFilePath = filePath + "-weights.csv";
            outputCharWeights(charWeightsFilePath, charStats);

            // Score list of passwords and output
            Scorer scorer = new Scorer(charStats);
            String scoresFilePath = filePath + "-scores.csv";
            outputScores(scoresFilePath, scorer, passwords);
        }

        // Dump the weight arrays to a file
        else {
            String dumpFilePath = filePath + "-arrays.txt";
            dumpArray(dumpFilePath, charStats);
        }
    }

    public static void outputCharCounts(String filePath, CharStats charStats) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), csvFormat);

        for (char c = ' '; c <= '~'; c++) {
            csvPrinter.print(String.format("'%s'", c));

            // print forward position counts
            for (int pos = 0; pos < 5; pos++) {
                csvPrinter.print(String.format("%d", charStats.getForwardCount(c, pos)));
            }

            csvPrinter.print(""); // total count (empty for now)

            // print reverse position counts
            for (int pos = 0; pos < 5; pos++) {
                csvPrinter.print(String.format("%d", charStats.getReverseCount(c, pos)));
            }

            csvPrinter.println();
        }

        csvPrinter.close();
        System.out.format("Character counts written to %s\n", filePath);
    }

    public static void outputCharWeights(String filePath, CharStats charStats) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), csvFormat);

        for (char c = ' '; c <= '~'; c++) {
            csvPrinter.print(String.format("'%s'", c));

            // print forward position weights
            for (int pos = 0; pos < 5; pos++) {
                csvPrinter.print(String.format("%.3f", charStats.getForwardWeight(c, pos)));
            }

            csvPrinter.print(""); // total weight (empty for now)

            // print reverse position weights
            for (int pos = 4; pos >= 0; pos--) {
                csvPrinter.print(String.format("%.3f", charStats.getReverseWeight(c, pos)));
            }

            csvPrinter.println();
        }

        csvPrinter.close();
        System.out.format("Character weights written to %s\n", filePath);
    }

    public static void outputScores(String filePath, Scorer scorer, List<String> passwords) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), csvFormat);

        for (String password : passwords) {
            csvPrinter.print(String.format("%s", password));
            csvPrinter.print(String.format("%.2f", scorer.score(password)));
            csvPrinter.println();
        }

        csvPrinter.close();
        System.out.format("Password scores written to %s\n", filePath);
    }
    public static void dumpArray(String filePath, CharStats charStats) throws IOException{
        FileWriter fileWriter = new FileWriter(filePath);

        fileWriter.append("charFreqs =");
        fileWriter.append("\n");
        for (char c = ' '; c <= '~'; c++) {
           fileWriter.append("  ");
           fileWriter.append(String.format("'%s'", c) + ": [");

           // print forward position weights
           for (int pos = 0; pos < 5; pos++) {
               fileWriter.append(String.format("%.3f", charStats.getForwardWeight(c, pos)));
               if(pos < 4)
                   fileWriter.append(", ");
           }

           fileWriter.append("]" + "\n");
        }

        fileWriter.append("charFreqsReverse =");
        fileWriter.append("\n");
        for (char c = ' '; c <= '~'; c++){
            fileWriter.append("  ");
            fileWriter.append(String.format("'%s'", c) + ": [");

            // print reverse position weights
            for (int pos = 4; pos >= 0; pos--) {
                fileWriter.append(String.format("%.3f", charStats.getReverseWeight(c, pos)));
                if(pos >= 1)
                    fileWriter.append(", ");
            }

            fileWriter.append("]" + "\n");
        }

        fileWriter.close();
        System.out.format("Character weight arrays written to %s\n", filePath);
    }
}