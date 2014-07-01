/**
 * Version 1.10 (Now prints both first5 and last5 automagically instead of needing a command line argument and separate runs.)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 7/1/2014
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

    String csvFilePath;
    CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
    CSVPrinter csvPrinter;

    Boolean firstHalfDone = false;

    int numLines;

    public CharStats() {
        charCounts = new HashMap<>();
    }

    public static void main(String args[]) throws Exception {
        //checks for command line arguments, then creates an instance of the object and starts the run method
        CharStats counter = new CharStats();
        counter.run(args[0]);
    }

    public void run(String filePath) throws Exception {
        stringReader(filePath);
        output(filePath);
    }

    public void stringReader(String filePath) throws Exception {
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        numLines = 0;
        while ((line = br.readLine()) != null) {
            processLine(line);
            numLines++;
        }
        br.close();
        System.out.println("Finished reading the file.");

        if (!firstHalfDone){
            output(filePath);
            firstHalfDone = true;
            System.out.println("First half done");
            stringReader(filePath);
        }
    }

    public void processLine(String curLine) {
        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

        if (firstHalfDone)
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


    public void output(String filePath) throws Exception{

        //Initializes the FileWriter class, which sends an output file to the same directory with the same name appended by -Output.csv
        if (!firstHalfDone) {
            csvFilePath = filePath + "-Output.csv";
            csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat);
        }

        // print out counts
        System.out.format("Printing results to: %s", csvFilePath);
        System.out.println();

        // print out the counts
        if (!firstHalfDone)
            csvPrinter.printComment("Number of characters in each position:");
        else
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
                if (firstHalfDone) {
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
        if(!firstHalfDone)
            csvPrinter.printComment("Percent of passwords containing the character in each position:");
        else
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
                if(firstHalfDone) {
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
        if(firstHalfDone) {
            csvPrinter.close();

            System.out.println("Total lines processed: " + numLines);
        }
    }


}
