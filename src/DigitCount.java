/**
 * Version 1.3 (now with a cracked check and more comments)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 6/10/2014
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DigitCount {
    //initializes all of the variables
    public int zeroEnd;
    public int oneEnd;
    public int twoEnd;
    public int threeEnd;
    public int fourEnd;
    public int fiveEnd;
    public int sixEnd;
    public int sevenEnd;
    public int eightEnd;
    public int nineEnd;

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

    public double zeroPercent;
    public double onePercent;
    public double twoPercent;
    public double threePercent;
    public double fourPercent;
    public double fivePercent;
    public double sixPercent;
    public double sevenPercent;
    public double eightPercent;
    public double ninePercent;

    public double zeroEndPercent;
    public double oneEndPercent;
    public double twoEndPercent;
    public double threeEndPercent;
    public double fourEndPercent;
    public double fiveEndPercent;
    public double sixEndPercent;
    public double sevenEndPercent;
    public double eightEndPercent;
    public double nineEndPercent;

    public double conditionOnePercent;
    public double conditionTwoPercent;
    public double conditionThreePercent;
    public double conditionFourPercent;
    public double conditionFivePercent;
    public double conditionSixPercent;
    public double conditionSevenPercent;
    public double conditionEightPercent;
    public double conditionNinePercent;
    public double conditionTenPercent;
    public double conditionElevenPercent;

    public int linesProcessed = 0;
    public int linesMatched = 0;

    public int numOfLines = 45428;

    HashMap<Character, ArrayList<Integer>> charCounts;

    public DigitCount() {
        charCounts = new HashMap<>();
    }

    public static void main(String args[]) throws Exception
    {
        //creates an instance of the object and starts the run method
        DigitCount counter = new DigitCount();
        counter.run(args[0]);
    }

    public void run(String filePath)throws Exception
    {
        stringReader(filePath);
        //selects the .txt document to be processed
        //stringMatcher("C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_pw.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_jtr.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_cond.txt");
        //creates the percent values for the statistics
        //percentify();
        //prints out all of the statistics in a semi-readable format
        /*System.out.println("Totals - " + "Zeros: " + zeroCount + ", Ones: " + oneCount + ", Twos: " + twoCount + ", Threes: " + threeCount + ", Fours: " + fourCount + ", Fives: " + fiveCount + ", Sixes: " + sixCount + ", Sevens: " + sevenCount + ", Eights: " + eightCount + ", Nines: " + nineCount);
        System.out.println("Total Percentages - " + "Zeros: " + zeroPercent + "%, Ones: " + onePercent + "%, Twos: " + twoPercent + "%, Threes: " + threePercent + "%, Fours: " + fourPercent + "%, Fives: " + fivePercent + "%, Sixes: " + sixPercent + "%, Sevens: " + sevenPercent + "%, Eights: " + eightPercent + "%, Nines: " + ninePercent + "%");
        System.out.println("Ending Numbers - " + "Zeros: " + zeroEnd + ", Ones: " + oneEnd + ", Twos: " + twoEnd + ", Threes: " + threeEnd + ", Fours: " + fourEnd + ", Fives: " + fiveEnd + ", Sixes: " + sixEnd + ", Sevens: " + sevenEnd + ", Eights: " + eightEnd + ", Nines: " + nineEnd);
        System.out.println("Ending Percentages - " + "Zeros: " + zeroEndPercent + "%, Ones: " + oneEndPercent + "%, Twos: " + twoEndPercent + "%, Threes: " + threeEndPercent + "%, Fours: " + fourEndPercent + "%, Fives: " + fiveEndPercent + "%, Sixes: " + sixEndPercent + "%, Sevens: " + sevenEndPercent + "%, Eights: " + eightEndPercent + "%, Nines: " + nineEndPercent + "%");
        System.out.println("Passwords Cracked: " + linesMatched);
        System.out.println("Cracked Password Conditions- " + "3class12: " + conditionOne + ", 3class16: " + conditionTwo + ", basic8: " + conditionThree + ", basic16: " + conditionFour + ", basic20: " + conditionFive + ", comp8: " + conditionSix + ", rockyou: " + conditionSeven + ", yahoo: " + conditionEight + ", metersStandard: " + conditionNine + ", metersStringent: " + conditionTen + ", blacklist: " + conditionEleven);
        System.out.println("Cracked Password Condition Percentages- " + "3class12: " + conditionOnePercent + "%, 3class16: " + conditionTwoPercent + "%, basic8: " + conditionThreePercent + "%, basic16: " + conditionFourPercent + "%, basic20: " + conditionFivePercent + "%, comp8: " + conditionSixPercent + "%, rockyou: " + conditionSevenPercent + "%, yahoo: " + conditionEightPercent + "%, metersStandard: " + conditionNinePercent + "%, metersStringent: " + conditionTenPercent + "%, blacklist: " + conditionElevenPercent + "%");*/
    }

    public void stringReader (String filePath) throws Exception
    {



        System.out.println("About to start BufferedReader.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            processLine(line);
        }
        br.close();

        System.out.println("BufferedReader has finished.");
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
    public void processLine (String curLine) {

        int length = curLine.length();
        char curChar;
        ArrayList<Integer> curCounts;
        Integer curCount;

        for(int i = 0; i < length; i++){
            curChar = curLine.charAt(i);
            try {
                curCounts = charCounts.get(i);
            } catch (NullPointerException ex) {
                curCounts = new ArrayList<>();
            }

            try {
                curCount = curCounts.get(i);
                curCount++;
                curCounts.set(i, curCount);
            } catch (NullPointerException ex) {
                curCounts.set(i, 1);
            }

            charCounts.put(curChar, curCounts);
        }

        linesProcessed++;
        System.out.println("---------------------------" + linesProcessed);
    }



    /*public void percentify()
    {
        //turns the totals into a percentage of the number of lines
        zeroPercent = zeroCount;
        zeroPercent = (zeroPercent/numOfLines)*100;
        onePercent = oneCount;
        onePercent = (onePercent/numOfLines)*100;
        twoPercent = twoCount;
        twoPercent = (twoPercent/numOfLines)*100;
        threePercent = threeCount;
        threePercent = (threePercent/numOfLines)*100;
        fourPercent = fourCount;
        fourPercent = (fourPercent/numOfLines)*100;
        fivePercent = fiveCount;
        fivePercent = (fivePercent/numOfLines)*100;
        sixPercent = sixCount;
        sixPercent = (sixPercent/numOfLines)*100;
        sevenPercent = sevenCount;
        sevenPercent = (sevenPercent/numOfLines)*100;
        eightPercent = eightCount;
        eightPercent = (eightPercent/numOfLines)*100;
        ninePercent = nineCount;
        ninePercent = (ninePercent/numOfLines)*100;

        //truncates to two decimal places
        zeroPercent = Math.floor(zeroPercent * 100) / 100;
        onePercent = Math.floor(onePercent * 100) / 100;
        twoPercent = Math.floor(twoPercent * 100) / 100;
        threePercent = Math.floor(threePercent * 100) / 100;
        fourPercent = Math.floor(fourPercent * 100) / 100;
        fivePercent = Math.floor(fivePercent * 100) / 100;
        sixPercent = Math.floor(sixPercent * 100) / 100;
        sevenPercent = Math.floor(sevenPercent * 100) / 100;
        eightPercent = Math.floor(eightPercent * 100) / 100;
        ninePercent = Math.floor(ninePercent * 100) / 100;


        zeroEndPercent = zeroEnd;
        zeroEndPercent = (zeroEndPercent/numOfLines)*100;
        oneEndPercent = oneEnd;
        oneEndPercent = (oneEndPercent/numOfLines)*100;
        twoEndPercent = twoEnd;
        twoEndPercent = (twoEndPercent/numOfLines)*100;
        threeEndPercent = threeEnd;
        threeEndPercent = (threeEndPercent/numOfLines)*100;
        fourEndPercent = fourEnd;
        fourEndPercent = (fourEndPercent/numOfLines)*100;
        fiveEndPercent = fiveEnd;
        fiveEndPercent = (fiveEndPercent/numOfLines)*100;
        sixEndPercent = sixEnd;
        sixEndPercent = (sixEndPercent/numOfLines)*100;
        sevenEndPercent = sevenEnd;
        sevenEndPercent = (sevenEndPercent/numOfLines)*100;
        eightEndPercent = eightEnd;
        eightEndPercent = (eightEndPercent/numOfLines)*100;
        nineEndPercent = nineEnd;
        nineEndPercent = (nineEndPercent/numOfLines)*100;

        zeroEndPercent = Math.floor(zeroEndPercent * 100) / 100;
        oneEndPercent = Math.floor(oneEndPercent * 100) / 100;
        twoEndPercent = Math.floor(twoEndPercent * 100) / 100;
        threeEndPercent = Math.floor(threeEndPercent * 100) / 100;
        fourEndPercent = Math.floor(fourEndPercent * 100) / 100;
        fiveEndPercent = Math.floor(fiveEndPercent * 100) / 100;
        sixEndPercent = Math.floor(sixEndPercent * 100) / 100;
        sevenEndPercent = Math.floor(sevenEndPercent * 100) / 100;
        eightEndPercent = Math.floor(eightEndPercent * 100) / 100;
        nineEndPercent = Math.floor(nineEndPercent * 100) / 100;

        //dividing by the total number of passwords created under each condition
        conditionOnePercent = conditionOne;
        conditionOnePercent = (conditionOnePercent/990)*100;
        conditionTwoPercent = conditionTwo;
        conditionTwoPercent = (conditionTwoPercent/983)*100;
        conditionThreePercent = conditionThree;
        conditionThreePercent = (conditionThreePercent/3062)*100;
        conditionFourPercent = conditionFour;
        conditionFourPercent = (conditionFourPercent/2054)*100;
        conditionFivePercent = conditionFive;
        conditionFivePercent = (conditionFivePercent/987)*100;
        conditionSixPercent = conditionSix;
        conditionSixPercent = (conditionSixPercent/4239)*100;
        conditionSevenPercent = conditionSeven;
        conditionSevenPercent = (conditionSevenPercent/15000)*100;
        conditionEightPercent = conditionEight;
        conditionEightPercent = (conditionEightPercent/15000)*100;
        conditionNinePercent = conditionNine;
        conditionNinePercent = (conditionNinePercent/1394)*100;
        conditionTenPercent = conditionTen;
        conditionTenPercent = (conditionTenPercent/564)* 100;
        conditionElevenPercent = conditionEleven;
        conditionElevenPercent = (conditionElevenPercent/1155)*100;

        conditionOnePercent = Math.floor(conditionOnePercent * 100) / 100;
        conditionTwoPercent = Math.floor(conditionTwoPercent * 100) / 100;
        conditionThreePercent = Math.floor(conditionThreePercent * 100) / 100;
        conditionFourPercent = Math.floor(conditionFourPercent * 100) / 100;
        conditionFivePercent = Math.floor(conditionFivePercent * 100) / 100;
        conditionSixPercent = Math.floor(conditionSixPercent * 100) / 100;
        conditionSevenPercent = Math.floor(conditionSevenPercent * 100) / 100;
        conditionEightPercent = Math.floor(conditionEightPercent * 100) / 100;
        conditionNinePercent = Math.floor(conditionNinePercent * 100) / 100;
        conditionTenPercent = Math.floor(conditionTenPercent * 100) / 100;
        conditionElevenPercent = Math.floor(conditionElevenPercent * 100) / 100;
    }*/
}
