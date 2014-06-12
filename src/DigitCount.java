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

    public int zeroCount, oneCount, twoCount, threeCount, fourCount, fiveCount, sixCount, sevenCount, eightCount, nineCount,
               aCount, bCount, cCount, dCount, eCount, fCount, gCount, hCount, iCount, jCount, kCount, lCount, mCount, nCount, oCount, pCount, qCount, rCount, sCount, tCount, uCount, vCount, wCount, xCount, yCount, zCount,
               ACount, BCount, CCount, DCount, ECount, FCount, GCount, HCount, ICount, JCount, KCount, LCount, MCount, NCount, OCount, PCount, QCount, RCount, SCount, TCount, UCount, VCount, WCount, XCount, YCount, ZCount,
               exCount, atCount, numCount, dolCount, pctCount, ctCount, andCount, astCount, begpCount, endpCount, dshCount, undCount, equlCount, plsCount, begbCount, begcCount, endbCount, endcCount, bksCount, pipCount, smcCount, clnCount, apsCount, qutCount, cmaCount, lstCount, prdCount, grtCount, fwsCount, qmkCount;

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

    HashMap<Integer, Integer> countMap = new HashMap();
    ArrayList<Integer> charCount = new ArrayList<>();

    public static void main(String args[]) throws Exception
    {
        //creates an instance of the object and starts the run method
        DigitCount counter = new DigitCount();
        counter.run();
    }

    public void run()throws Exception
    {

        //selects the .txt document to be processed

        stringReader("C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_pw.txt");
        //stringMatcher("C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_pw.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_jtr.txt", "C:\\Users\\jonat_000\\Documents\\CUPS Stuff\\fullpw_cond.txt");
        //creates the percent values for the statistics
        percentify();


        for (int i = 32; i<127; i++){
            int numChars = charCount<i>;
            System.out.println((char)i + " " + numChars);

        }

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

    public void processLine (String curLine) {

        int length = curLine.length();
        for (int curChar = 32; curChar<127; curChar++){//curChar is the ascii value of the current character
            int index = curLine.indexOf((char)curChar);
            while (index >=0) {
                System.out.println("Found " + (char)curChar + " at " + index);
                int location = curChar + (127 * index);
                countMap.put(curChar,  charCount[location]++);


                /*if(i == 0)
                    zeroCount++;
                if(i == 1)
                    oneCount++;
                if(i == 2)
                    twoCount++;
                if(i == 3)
                    threeCount++;
                if(i == 4)
                    fourCount++;
                if(i == 5)
                    fiveCount++;
                if(i == 6)
                    sixCount++;
                if(i == 7)
                    sevenCount++;
                if(i == 8)
                    eightCount++;
                if(i == 9)
                    nineCount++;
                if(i == 10)
                    aCount++;
                if(i == 11)
                    bCount++;
                if(i == 12)
                    cCount++;
                if(i == 13)
                    dCount++;
                if(i == 14)
                    eCount++;
                if(i == 14)
                    fCount++;
                if(i == 15)
                    gCount++;
                if(i == 16)
                    hCount++;
                if(i == 17)
                    iCount++;
                if(i == 18)
                    jCount++;
                if(i == 19)
                    kCount++;
                if(i == 20)
                    lCount++;
                if(i == 21)
                    mCount++;
                if(i == 22)
                    nCount++;
                if(i == 23)
                    oCount++;
                if(i == 24)
                    pCount++;
                if(i == 25)
                    qCount++;
                if(i == 26)
                    rCount++;
                if(i == 27)
                    sCount++;
                if(i == 28)
                    tCount++;
                if(i == 29)
                    uCount++;
                if(i == 30)
                    vCount++;
                if(i == 31)
                    wCount++;
                if(i == 32)
                    xCount++;
                if(i == 33)
                    yCount++;
                if(i == 34)
                    zCount++;
                if(i == 35)
                    ACount++;
                if(i == 36)
                    BCount++;
                if(i == 37)
                    CCount++;
                if(i == 38)
                    DCount++;
                if(i == 39)
                    ECount++;
                if(i == 40)
                    FCount++;
                if(i == 41)
                    GCount++;
                if(i == 42)
                    HCount++;
                if(i == 43)
                    ICount++;
                if(i == 44)
                    JCount++;
                if(i == 45)
                    KCount++;
                if(i == 46)
                    LCount++;
                if(i == 47)
                    MCount++;
                if(i == 48)
                    NCount++;
                if(i == 49)
                    OCount++;
                if(i == 50)
                    PCount++;
                if(i == 51)
                    QCount++;
                if(i == 52)
                    RCount++;
                if(i == 53)
                    SCount++;
                if(i == 54)
                    TCount++;
                if(i == 55)
                    UCount++;
                if(i == 56)
                    VCount++;
                if(i == 57)
                    WCount++;
                if(i == 58)
                    XCount++;
                if(i == 59)
                    YCount++;
                if(i == 60)
                    ZCount++;
                if(i == 61)
                    exCount++;
                if(i == 62)
                    atCount++;
                if(i == 63)
                    numCount++;
                if(i == 64)
                    dolCount++;
                if(i == 65)
                    pctCount++;
                if(i == 66)
                    ctCount++;
                if(i == 67)
                    andCount++;
                if(i == 68)
                    astCount++;
                if(i == 69)
                    begpCount++;
                if(i == 70)
                    endpCount++;
                if(i == 71)
                    dshCount++;
                if(i == 72)
                    undCount++;
                if(i == 73)
                    equlCount++;
                if(i == 74)
                    plsCount++;
                if(i == 75)
                    begbCount++;
                if(i == 76)
                    begcCount++;
                if(i == 77)
                    endbCount++;
                if(i == 78)
                    endcCount++;
                if(i == 79)
                    bksCount++;
                if(i == 80)
                    pipCount++;
                if(i  == 81)
                    smcCount++;
                if(i == 82)
                    clnCount++;
                if(i == 83)
                    apsCount++;
                if(i == 84)
                    qutCount++;
                if(i == 85)
                    cmaCount++;
                if(i == 86)
                    lstCount++;
                if(i == 87)
                    prdCount++;
                if(i == 88)
                    grtCount++;
                if(i == 89)
                    fwsCount++;
                if(i == 90)
                    qmkCount++;*/

                index = curLine.indexOf((char)curChar, index + 127);
            }

        }
        linesProcessed++;
        System.out.println("---------------------------" + linesProcessed);
    }

    /*public void processLineOld(String curLine) {

        int zero = '0';
        int one = '1';
        int two = '2';
        int three = '3';
        int four = '4';
        int five = '5';
        int six = '6';
        int seven = '7';
        int eight = '8';
        int nine = '9';

        //checks the length of the line
        int length = curLine.length();

        //finds the first and last zeros (or whatever other number) in the line and records their position
        //if either the first or last zero is at the end of the line, it adds it to the count of end-of-line zeros
        int zeroPos = curLine.indexOf(zero);
        if (zeroPos != -1) {
            zeroCount++;
            int zeroPosRel = length - zeroPos;
            System.out.print("First 0 at pos " + zeroPos + ", ");
            System.out.println(zeroPosRel-1 + " from end.");
            int lastZeroPos = curLine.lastIndexOf(zero);
            int lastZeroPosRel = length - lastZeroPos;
            if (lastZeroPos != zeroPos) {
                System.out.print("Last 0 at pos " + lastZeroPos + ", ");
                System.out.println(lastZeroPosRel-1 +  " from end.");
            }
            if (zeroPosRel == 1 || lastZeroPosRel == 1)
                zeroEnd++;
        }

        int onePos = curLine.indexOf(one);
        if (onePos != -1) {
            oneCount++;
            int onePosRel = length - onePos;
            System.out.print("First 1 at pos " + onePos + ", ");
            System.out.println(onePosRel-1 + " from end.");
            int lastOnePos = curLine.lastIndexOf(one);
            int lastOnePosRel = length - lastOnePos;
            if (lastOnePos != onePos) {
                System.out.print("Last 1 at pos " + lastOnePos + ", ");
                System.out.println(lastOnePosRel-1 +  " from end.");
            }
            if (onePosRel == 1 || lastOnePosRel == 1)
                oneEnd++;
        }
        int twoPos = curLine.indexOf(two);
        if (twoPos != -1) {
            twoCount++;
            int twoPosRel = length - twoPos;
            System.out.print("First 2 at pos " + twoPos + ", ");
            System.out.println(twoPosRel-1 + " from end.");
            int lastTwoPos = curLine.lastIndexOf(two);
            int lastTwoPosRel = length - lastTwoPos;
            if (lastTwoPos != twoPos) {
                System.out.print("Last 2 at pos " + lastTwoPos + ", ");
                System.out.println(lastTwoPosRel-1 +  " from end.");
            }
            if (twoPosRel == 1 || lastTwoPosRel == 1)
                twoEnd++;
        }
        int threePos = curLine.indexOf(three);
        if (threePos != -1) {
            threeCount++;
            int threePosRel = length - threePos;
            System.out.print("First 3 at pos " + threePos + ", ");
            System.out.println(threePosRel-1 + " from end.");
            int lastThreePos = curLine.lastIndexOf(three);
            int lastThreePosRel = length - lastThreePos;
            if (lastThreePos != threePos) {
                System.out.print("Last 3 at pos " + lastThreePos + ", ");
                System.out.println(lastThreePosRel-1 +  " from end.");
            }
            if (threePosRel == 1 || lastThreePosRel == 1)
                threeEnd++;
        }
        int fourPos = curLine.indexOf(four);
        if (fourPos != -1) {
            fourCount++;
            int fourPosRel = length - fourPos;
            System.out.print("First 4 at pos " + fourPos + ", ");
            System.out.println(fourPosRel-1 + " from end.");
            int lastFourPos = curLine.lastIndexOf(four);
            int lastFourPosRel = length - lastFourPos;
            if (lastFourPos != fourPos) {
                System.out.print("Last 4 at pos " + lastFourPos + ", ");
                System.out.println(lastFourPosRel-1 +  " from end.");
            }
            if (fourPosRel == 1 || lastFourPosRel == 1)
                fourEnd++;
        }
        int fivePos = curLine.indexOf(five);
        if (fivePos != -1) {
            fiveCount++;
            int fivePosRel = length - fivePos;
            System.out.print("First 5 at pos " + fivePos + ", ");
            System.out.println(fivePosRel-1 + " from end.");
            int lastFivePos = curLine.lastIndexOf(five);
            int lastFivePosRel = length - lastFivePos;
            if (lastFivePos != fivePos) {
                System.out.print("Last 5 at pos " + lastFivePos + ", ");
                System.out.println(lastFivePosRel-1 +  " from end.");
            }
            if (fivePosRel == 1 || lastFivePosRel == 1)
                fiveEnd++;
        }
        int sixPos = curLine.indexOf(six);
        if (sixPos != -1) {
            sixCount++;
            int sixPosRel = length - sixPos;
            System.out.print("First 6 at pos " + sixPos + ", ");
            System.out.println(sixPosRel-1 + " from end.");
            int lastSixPos = curLine.lastIndexOf(six);
            int lastSixPosRel = length - lastSixPos;
            if (lastSixPos != sixPos) {
                System.out.print("Last 6 at pos " + lastSixPos + ", ");
                System.out.println(lastSixPosRel-1 +  " from end.");
            }
            if (sixPosRel == 1 || lastSixPosRel == 1)
                sixEnd++;
        }
        int sevenPos = curLine.indexOf(seven);
        if (sevenPos != -1) {
            sevenCount++;
            int sevenPosRel = length - sevenPos;
            System.out.print("First 7 at pos " + sevenPos + ", ");
            System.out.println(sevenPosRel-1 + " from end.");
            int lastSevenPos = curLine.lastIndexOf(seven);
            int lastSevenPosRel = length - lastSevenPos;
            if (lastSevenPos != sevenPos) {
                System.out.print("Last 7 at pos " + lastSevenPos + ", ");
                System.out.println(lastSevenPosRel-1 +  " from end.");
            }
            if (sevenPosRel == 1 || lastSevenPosRel == 1)
                sevenEnd++;
        }
        int eightPos = curLine.indexOf(eight);
        if (eightPos != -1) {
            eightCount++;
            int eightPosRel = length - eightPos;
            System.out.print("First 8 at pos  " + eightPos + ", ");
            System.out.println(eightPosRel-1 + " from end.");
            int lastEightPos = curLine.lastIndexOf(eight);
            int lastEightPosRel = length - lastEightPos;
            if (lastEightPos != eightPos) {
                System.out.print("Last 8 at pos " + lastEightPos + ", ");
                System.out.println(lastEightPosRel-1 +  " from end.");
            }
            if (eightPosRel == 1 || lastEightPosRel == 1)
                eightEnd++;
        }
        int ninePos = curLine.indexOf(nine);
        if (ninePos != -1) {
            nineCount++;
            int ninePosRel = length - ninePos;
            System.out.print("9 at pos " + ninePos + ", ");
            System.out.println(ninePosRel-1 + " from end.");
            int lastNinePos = curLine.lastIndexOf(nine);
            int lastNinePosRel = length - lastNinePos;
            if (lastNinePos != ninePos) {
                System.out.print("Last 9 at pos " + lastNinePos + ", ");
                System.out.println(lastNinePosRel-1 +  " from end.");
            }
            if (ninePosRel == 1 || lastNinePosRel == 1)
                nineEnd++;
        }

        //counts the number of lines processed so far and outputs the line number to the console
        linesProcessed++;
        System.out.println("---------------------------" + linesProcessed);
    }*/

    public void percentify()
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
    }
}
