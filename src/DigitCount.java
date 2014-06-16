/**
 * Version 1.4 (Now checks the first 10 characters of a password)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Adam Durity on 6/16/2014
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class DigitCount {
    //initializes all of the variables

    public int conditionOne; //990 total
    public int conditionTwo; //983 total
    public int conditionThree; //3062 total
    public int conditionFour; //2054 total
    public int conditionFive; //987 total
    public int conditionSix; //4239 total
    public int conditionSeven; //15000 total
    public int conditionEight; //15000 total
    public int conditionNine; //1394 total
    public int conditionTen; //564 total
    public int conditionEleven; //1155 total


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
        //stringMatcher("C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_pw.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_jtr.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_cond.txt");
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
        for (int i = (int) '0'; i <= (int) '9'; i++) {
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
        for (int i = (int) '0'; i <= (int) '9'; i++) {
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
    }

    /*public void stringMatcher (String passwordPath, String crackerPath, String conditionPath) throws Exception
    {
        BufferedReader passBr = new BufferedReader(new FileReader(passwordPath));
        BufferedReader crackBr = new BufferedReader(new FileReader(crackerPath));
        BufferedReader condBr = new BufferedReader(new FileReader(conditionPath));

        while (true){
            String crackLine = crackBr.readLine();
            String passLine = passBr.readLine();
            String condLine = condBr.readLine();

            if (passLine == null || crackLine == null || condLine == null)
                break;

            //defines all of the strings indexOf should be looking for
            String notCracked = "-5";
            String condOne = "3class12";
            String condTwo = "3class16";
            String condThree = "basic8";
            String condFour = "basic16";
            String condFive = "basic20";
            String condSix = "comp8";
            String condSeven = "rockyou";
            String condEight = "yahoo";
            String condNine = "metersStandard";
            String condTen = "metersStringent";
            String condEleven = "blacklist";

            //checks if a string has been cracked
            int crackCheck = crackLine.indexOf(notCracked);
            //checks which condition a password was created under
            int condOneMatch = condLine.indexOf(condOne);
            int condTwoMatch = condLine.indexOf(condTwo);
            int condThreeMatch = condLine.indexOf(condThree);
            int condFourMatch = condLine.indexOf(condFour);
            int condFiveMatch = condLine.indexOf(condFive);
            int condSixMatch = condLine.indexOf(condSix);
            int condSevenMatch = condLine.indexOf(condSeven);
            int condEightMatch = condLine.indexOf(condEight);
            int condNineMatch = condLine.indexOf(condNine);
            int condTenMatch = condLine.indexOf(condTen);
            int condElevenMatch = condLine.indexOf(condEleven);

            //does the actual check for if a password was cracked or not, and, if so, checks what condition it was created under
            if (crackCheck != 0){
                processLine(passLine);
                if (condOneMatch != -1)
                    conditionOne++;
                if (condTwoMatch != -1)
                    conditionTwo++;
                if (condThreeMatch != -1)
                    conditionThree++;
                if (condFourMatch != -1)
                    conditionFour++;
                if (condFiveMatch != -1)
                    conditionFive++;
                if (condSixMatch != -1)
                    conditionSix++;
                if (condSevenMatch != -1)
                    conditionSeven++;
                if (condEightMatch != -1)
                    conditionEight++;
                if (condNineMatch != -1)
                    conditionNine++;
                if (condTenMatch != -1)
                    conditionTen++;
                if (condElevenMatch != -1)
                    conditionEleven++;

                linesMatched++;
            }
        }
        passBr.close();
        crackBr.close();
        condBr.close();
    }*/
//
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

        linesProcessed++;
    }
}