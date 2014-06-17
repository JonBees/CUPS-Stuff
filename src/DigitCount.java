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

public class DigitCount {
    //initializes all of the variables

    public int linesProcessed = 0;

    HashMap<Character, SortedMap<Integer, Integer>> charCounts;

    public DigitCount() {
        charCounts = new HashMap<>();
    }

    public static void main(String args[]) throws Exception {
        //creates an instance of the object and starts the run method
        DigitCount counter = new DigitCount();
        try {
            counter.run(args[0], args[1]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            counter.run(args[0]);
        }
    }

    public void run(String filePath) throws Exception{
        stringReader(filePath, false);
    }

    public void run(String filePath, String reverse) throws Exception {
        if (reverse.toLowerCase().equals("reverse"))
            stringReader(filePath, true);
        else
            stringReader(filePath, false);
    }

    public void stringReader(String filePath, Boolean reverse) throws Exception {
        int numLines;
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        numLines = 0;
        while ((line = br.readLine()) != null) {
            processLine(line, reverse);
            numLines++;
        }
        br.close();
        System.out.println("Finished reading the file.");

        FileWriter writer = new FileWriter(filePath + "-Output.csv");

        // print out counts
        System.out.println("Printing results to: " + filePath + "-Output.csv");

        writer.append("Number of characters in each position:");
        writer.append('\n');

        char curChar;
        TreeMap<Integer, Integer> curCounts;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);
            writer.append('-');
            writer.append(curChar);
            writer.append('-');
            writer.append(',');

            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    writer.append(curCounts.get(pos).toString());
                    writer.append(',');
                }
            }
            writer.append('\n');
        }
        writer.flush();


        // print out percentages
        writer.append('\n');
        writer.append("Percent of passwords containing the character in each position:");
        writer.append('\n');

        double pct;
        int curCount;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);

            writer.append('-');
            writer.append(curChar);
            writer.append('-');
            writer.append(',');

            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    curCount = curCounts.get(pos);
                    pct = (curCount / (double) numLines) * 100;
                    writer.append(String.valueOf(pct));
                    writer.append('%');
                    writer.append(',');
                }
            }
            writer.append('\n');
        }
        writer.flush();
        writer.close();

        System.out.println("Total lines processed: " + linesProcessed);
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

        linesProcessed++;
    }

}