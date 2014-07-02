/**
 * Version 1.12 (Now modularized.)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 7/2/2014
 */


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.util.TreeMap;

public class Main {

    static String filePath;
    String csvFilePath;
    CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
    CSVPrinter csvPrinter;

    public static void main(String args[]) throws Exception {
        //checks for command line arguments, then creates an instance of the object and starts the run method
        CharStats counter = new CharStats();
        filePath = args[0];
        counter.run(filePath);

        // 1. Read the list of passwords
        // 2. Compute the char-position stats of the passwords
        // 3. Use computed statistics to score passwords
        // 4. Output statistics and scores
        //
        // Load passwords into ArrayList<String>
        // CharStats charStats = new CharStats(<list of passwords>)
        // StatsScorer scorer = new StatsScorer(charStats)
        // Iterate over password list to get score - scorer.score(<password>)
        // Format output for console/CSV
        //
    }

    public void output() throws Exception{

        //Initializes the FileWriter class, which sends an output file to the same directory with the same name appended by -Output.csv
        csvFilePath = filePath + "-Output.csv";
        csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat);

        // print out the location of the CSV
        System.out.format("Printing character stats to: %s", csvFilePath);
        System.out.println();

        // print out the counts
        if (status == 2)
            csvPrinter.printComment("Number of characters in each position:");
        if (status == 4)
            csvPrinter.printComment("Number of characters (n) from end");

        char curChar;
        TreeMap<Integer, Integer> curCounts;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            if (curCounts != null) { // curCounts is null if there were no instances of a character
                csvPrinter.print(curChar);

                for (int pos = 0; pos < 5; pos++) {
                    if (curCounts.containsKey(pos)) {
                        csvPrinter.print(curCounts.get(pos));
                    } else {
                        csvPrinter.print(0);
                    }
                }

                // find the total count for this character and append to end of CSV record.
                if (status == 2) {
                    int totalCount = 0;
                    for (int val : curCounts.values()) {
                        totalCount += val;
                    }
                    csvPrinter.print("");
                    csvPrinter.print(totalCount);
                }
                csvPrinter.println();
            }
        }
        csvPrinter.flush();

        // print out percentages
        if (status == 2)
            csvPrinter.printComment("Percent of passwords containing the character in each position:");
        if (status == 4)
            csvPrinter.printComment("Percent of passwords containing the character in each position (n) from end:");

        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            if (curCounts != null) {
                csvPrinter.print(curChar);

                for (int pos = 0; pos < 5; pos++) {
                    if (curCounts.containsKey(pos)) {
                        csvPrinter.print(((curCounts.get(pos) / (double) numLines) * 100) + "%");
                    } else {
                        csvPrinter.print((0.0) + "%");
                    }
                }

                // find the total count for this character and append to end of CSV record.
                if(status == 2) {
                    int totalCount = 0;
                    for (int val : curCounts.values()) {
                        totalCount += val;
                    }
                    csvPrinter.print("");
                    csvPrinter.print(((totalCount / (double) numLines) * 100) + "%");
                }
                csvPrinter.println();
            }
        }
    }


}
