/**
 * Version 1.6 (Now checks for all ASCII characters between values 32 and 126, space and tilde)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 6/17/2014
 */

import java.io.BufferedReader;
import java.io.FileReader;
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
        counter.run(args[0]);
    }

    public void run(String filePath) throws Exception {
        stringReader(filePath);
    }

    public void stringReader(String filePath) throws Exception {
        int numLines;
        System.out.println("Starting BufferedReader.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        numLines = 0;
        while ((line = br.readLine()) != null) {
            processLine(line);
            numLines++;
        }
        br.close();
        System.out.println("BufferedReader has finished.");


        // print out counts
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);
            System.out.format("'%s':", curChar); // make it pretty
            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    System.out.format(" %5d", curCounts.get(pos)); // plenty of space
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        // print out percentages
        double pct;
        int curCount;
        for (int i = 32; i <= 126; i++) {
            curChar = (char) i;
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(curChar);
            System.out.format("'%s':", curChar); // make it pretty
            for (int pos = 0; pos < 10; pos++) {
                if (curCounts.containsKey(pos)) {
                    curCount = curCounts.get(pos);
                    pct = (curCount / (double) numLines) * 100;
                    System.out.format(" %.2f%%", pct); // plenty of space
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Total lines processed: " + linesProcessed);
    }


    public void processLine(String curLine) {

        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

        String curLineReverse = new StringBuilder(curLine).reverse().toString();

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