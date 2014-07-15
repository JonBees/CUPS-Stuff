/**
 * Version 1.12 (Now modularized.)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Adam Durity on 7/14/2014
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

        try {
            filePath = args[0];
        } catch(IndexOutOfBoundsException ex) {
            System.err.println("Please specify a password data file.");
            System.exit(1);
        }

        // Read the list of passwords
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        passwords = Arrays.asList(br.lines().toArray(String[]::new));

        // Compute the character-position statistics of the passwords and output
        CharStats charStats = new CharStats(passwords);
        String charCountsFilePath = filePath + "-counts.csv";
        outputCharCounts(charCountsFilePath, charStats);
        String charWeightsFilePath = filePath + "-weights.csv";
        outputCharWeights(charWeightsFilePath, charStats);

        // Score list of passwords and output
        Scorer scorer = new Scorer(charStats);
        String scoresFilePath = filePath + "-scores.csv";
        outputScores(scoresFilePath, scorer, passwords);
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

            // print forward position counts
            for (int pos = 0; pos < 5; pos++) {
                csvPrinter.print(String.format("%.3f", charStats.getForwardWeight(c, pos)));
            }

            csvPrinter.print(""); // total weight (empty for now)

            // print reverse position counts
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
}