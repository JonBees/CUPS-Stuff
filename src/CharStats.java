/**
 * Updated by Jonathan Bees on 7/2/2014.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class CharStats {

    HashMap<Character, SortedMap<Integer, Integer>> charCounts;

    int numLines;

    public CharStats() {
        charCounts = new HashMap<>();
    }

    public void run(String filePath) throws Exception {
        stringReader(filePath);
    }

    public void stringReader(String filePath) throws Exception {
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        numLines = 0;

        while ((line = br.readLine()) != null) {
            if(reverse) {
                line = new StringBuilder(line).reverse().toString();
                processLine(line); //sends the line to processLine if we want to check the character frequency
            }
            else
                processLine(line);

            numLines++;
        }
        br.close();
        System.out.println("Finished reading the file.");
    }

    public void processLine(String curLine) {
        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

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
