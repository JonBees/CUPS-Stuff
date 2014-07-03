/**
 * Updated by Jonathan Bees on 7/2/2014.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class CharStats {

    boolean reverse = false;

    public HashMap<Character, SortedMap<Integer, Integer>> charCounts, charCountsReverse;

    public CharStats() {
        charCounts = new HashMap<>();
        charCountsReverse = new HashMap<>();
    }

    public void run(ArrayList<String> passwords) throws Exception{
        stringReader(passwords);
    }

    public void stringReader(ArrayList<String> passwords) throws Exception {
        System.out.println("Started reading the file.");

            //reads each line from the array and passes it to the processLine method
        for (int i = 0; i < passwords.size(); i++) {
            String line = passwords.get(i);
                processLine(line); //sends the line to processLine if we want to check the character frequency
        }

        System.out.println("Finished reading the file.");
        if(!reverse){
            reverse = true;
            stringReader(passwords);
        }
    }

    public void processLine(String curLine) {
        int length = curLine.length();
        char curChar;
        TreeMap<Integer, Integer> curCounts;
        Integer curCount;

        if(reverse)
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
            if(!reverse)
                charCounts.put(curChar, curCounts);
            else
                charCountsReverse.put(curChar, curCounts);
        }
    }
}
