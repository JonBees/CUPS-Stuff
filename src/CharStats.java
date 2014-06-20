/**
 * Version 1.8 (Now reverses based on a command line argument)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 6/17/2014
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CharStats {

    HashMap<Character, SortedMap<Integer, Integer>> charCounts;

    public CharStats() {
        charCounts = new HashMap<>();
    }

    public static void main(String args[]) throws Exception {
        //checks for command line arguments, then creates an instance of the object and starts the run method
        CharStats counter = new CharStats();
        try {
            counter.run(args[0], args[1]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            counter.run(args[0]);
        }
    }

    public void run(String filePath) throws Exception{
        //If only a path is given, don't reverse
        stringReader(filePath, false);
    }

    public void run(String filePath, String reverse) throws Exception {
        //checks if the second argument actually says "reverse"
        if (reverse.toLowerCase().equals("reverse"))
            stringReader(filePath, true);
        else
            stringReader(filePath, false);
    }

    public void stringReader(String filePath, Boolean reverse) throws Exception {
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int numLines = 0;
        while ((line = br.readLine()) != null) {
            processLine(line, reverse);
            numLines++;
        }
        br.close();
        System.out.println("Finished reading the file.");

        //Initializes the FileWriter class, which sends an output file to the same directory with the same name appended by -Output.csv
        String csvFilePath = filePath + "-Output.csv";
        CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat);

        // print out counts
        System.out.format("Printing results to: %s", csvFilePath);
        System.out.println();

        // print out the counts
        csvPrinter.printComment("Number of characters in each position:");

        char curChar;
        TreeMap<Integer, Integer> curCounts;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);
            csvPrinter.print(curChar);

            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    csvPrinter.print(curCounts.get(pos));
                } else {
                    csvPrinter.print(0);
                }
            }

            // find the total count for this character and append to end of CSV record.
            int totalCount = 0;
            for (int val : curCounts.values()) {
                totalCount += val;
            }
            csvPrinter.print(totalCount);
            csvPrinter.println();
        }
        csvPrinter.flush();

        // print out percentages
        csvPrinter.printComment("Percent of passwords containing the character in each position:");

        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            csvPrinter.print(curChar);

            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    csvPrinter.print((curCounts.get(pos) / (double) numLines) * 100);
                } else {
                    csvPrinter.print(0.0);
                }
            }

            // find the total count for this character and append to end of CSV record.
            int totalCount = 0;
            for (int val : curCounts.values()) {
                totalCount += val;
            }
            csvPrinter.print((totalCount / (double) numLines) * 100);
            csvPrinter.println();
        }
        csvPrinter.close();

        System.out.println("Total lines processed: " + numLines);
    }


    public void processLine(String curLine, boolean reverse) {
        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

        if (reverse)
            curLine = new StringBuilder(curLine).reverse().toString();

        for (int i = 0; i < length; i++) {

            curChar = curLine.charAt(i);
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            if (curCounts == null) {
                curCounts = new TreeMap<>();
            }

            curCount = curCounts.get(i);

            if (curCount == null) {
                curCount = 1;
            } else {
                curCount++;
            }

            curCounts.put(i, curCount);
            charCounts.put(curChar, curCounts);
        }
    }

}
