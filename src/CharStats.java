/**
 * Version 1.11 (Now outputs scores for all given passwords.)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 7/2/2014
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CharStats {

    HashMap<Character, SortedMap<Integer, Integer>> charCounts;

    static String filePath;
    String csvFilePath;
    CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
    CSVPrinter csvPrinter;

    int numLines;

    ArrayList passwordScore = new ArrayList();

    int status = 1; //Status of 1 is first5 letter frequency analysis. Status of 2 is first5 password score analysis. Status of 3 is last5 letter frequency analysis. Status of 4 is last5 password score analysis.

    public CharStats() {
        charCounts = new HashMap<>();
    }

    public static void main(String args[]) throws Exception {
        //checks for command line arguments, then creates an instance of the object and starts the run method
        CharStats counter = new CharStats();
        filePath = args[0];
        counter.run();
    }

    public void run() throws Exception {
        stringReader();
        output();
    }

    public void stringReader() throws Exception {
        System.out.println("Status: " + status);
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        int curLineNum = 1;

        while ((line = br.readLine()) != null) {
            if(status == 1 || status == 3) {
                processLine(line); //sends the line to processLine if we want to check the character frequency
            }
            if(status == 2 || status == 4){
                checkPasswordStrength(line, curLineNum); //sends the line to checkPasswordStrength if we want to check the password's strength
            }
            curLineNum++;
        }
        br.close();
        System.out.println("Finished reading the file.");

        if (status == 1){
            numLines = curLineNum;
            output();
            status = 2;
            System.out.println("Finished generating character stats for first5.");
            csvPrinter.close();
            stringReader();
        }

        if(status == 2){
            status = 3;
            System.out.println("Finished checking password scores for first5.");
            csvPrinter.close();
            charCounts.clear();
            stringReader();
        }

        if (status == 3){
            output();
            status = 4;
            System.out.println("Finished generating character stats for last5.");
            csvPrinter.close();
            stringReader();
        }

        if(status == 4){
            status = 5;
            System.out.println("Finished checking password scores for last5.");
            csvPrinter.close();
            charCounts.clear();
        }
    }

    public void processLine(String curLine) {
        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

        if (status == 3)
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

    public void checkPasswordStrength (String curLine, int curLineNum) throws Exception{

        if(curLineNum == 1) {
            csvFilePath = filePath + "-PasswordStrength.csv";
            csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat);
            System.out.format("Printing password scores to: %s", csvFilePath);
            System.out.println();
        }

        if (status == 4)
            curLine = new StringBuilder(curLine).reverse().toString();

        char curChar;
        TreeMap<Integer, Integer> curCounts;
        double linePct = 0;

        for (int i = 0; i < 5; i++) {
            curChar = curLine.charAt(i);
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            double charPct = (curCounts.get(i) / (double) numLines) * 100;
            charPct = 1/charPct;
            linePct += charPct;
            }


        if (status == 2)
            csvPrinter.print("Line score for first 5 in line " + curLineNum + ": ");
        if (status == 4)
            csvPrinter.print("Line score for last 5 in line " + curLineNum + ": ");



        csvPrinter.print(linePct);
        if (status == 4)
            curLine = new StringBuilder(curLine).reverse().toString();
        csvPrinter.print(curLine);
        csvPrinter.println();

        //check each character for percentage frequency in that position

        //take 1/that percentage and add it together with the other fractions so far.
    }
}
