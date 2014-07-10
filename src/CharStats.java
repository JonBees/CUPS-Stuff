/**
 * Updated by Jonathan Bees on 7/8/2014.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class CharStats {

    int forwardGotten;
    int reverseGotten;

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
        System.out.println("Started reading the ArrayList.");

            //reads each line from the array and passes it to the processLine method
        for (int i = 0; i < passwords.size(); i++) {
            String line = passwords.get(i);
            processLine(line); //sends the line to processLine
        }

        if(!reverse){
            reverse = true;
            System.out.println("Finished reading the ArrayList forwards.");
            stringReader(passwords);
        }
        System.out.println("Finished reading the ArrayList reversed.");
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

    public double getPctForward (char ch, int pos){
        TreeMap <Integer, Integer> curCounts;
        double pct;
        if (charCounts.get(ch) != null) {
            curCounts = (TreeMap<Integer, Integer>) charCounts.get(ch);
            forwardGotten++;
                if (curCounts.containsKey(pos)) {
                    pct = ((curCounts.get(pos) / (double) Main.getNumLines()) * 100);
                } else {
                    pct = (0.0);
            }
        }
        else {
            System.out.println("The value in charCounts, character " + ch + ", position " + pos + " doesn't exist.");
            pct = 0.0;
        }
        return pct;
    }
    public double getPctReverse (char ch, int pos){
        TreeMap <Integer, Integer> curCounts;
        double pct;
        if (charCountsReverse.get(ch) != null) {
            curCounts = (TreeMap<Integer, Integer>) charCountsReverse.get(ch);
            reverseGotten++;
            if (curCounts.containsKey(pos)) {
                pct = ((curCounts.get(pos) / (double) Main.getNumLines()) * 100);
            } else {
                pct = (0.0);
            }
        }
        else {
            System.out.println("The value in charCountsReverse, character " + ch + ", position " + pos + " doesn't exist.");
            pct = 0.0;
        }
        return pct;
    }
    public boolean charCheck (char ch){
        boolean exists = false;
        if (charCounts.get(ch) != null)
            exists = true;
        return exists;
    }
    public void gottenCheck(){
       System.out.println("forwardGotten: " + forwardGotten);
       System.out.println("reverseGotten: " + reverseGotten);
    }
}