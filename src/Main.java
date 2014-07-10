/**
 * Version 1.12 (Now modularized.)
 * Created by Jonathan Bees on 6/9/2014
 * Updated by Jonathan Bees on 7/8/2014
 */


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {

    static String filePath;
    ArrayList<String> passwords = new ArrayList<>();
    static int numLines = 0;

    CharStats counter = new CharStats();
    Score score = new Score();

    public static void main(String args[]) throws Exception {
        //checks for command line arguments, then creates an instance of the object and starts the run method
        Main main = new Main();
        filePath = args[0];

        main.run();

        // 1. Read the list of passwords
        // 2. Compute the char-position stats of the passwords
        // 3. Use computed statistics to score passwords
        // 4. Output statistics and scores
        //
        // Load passwords into ArrayList<String>
        // CharStats charStats = new CharStats(<list of passwords>)
        // StatsScorer scorer = new StatsScorer(charStats)
        // Iterate over password list to get score - scorer.score(<password>)
        // Format output for console/CSV
    }

    public void run() throws Exception{

        stringReader(filePath);
        counter.run(passwords);
        score.compileScores(passwords);
        output();
        counter.gottenCheck();
    }

    public void stringReader(String filePath) throws Exception {
        System.out.println("Started reading the file.");

        //buffers the lines and hands off the processing for each line to the processLine method
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            passwords.add(line);
            numLines++;
        }
        br.close();
        System.out.println("Finished reading the file. There were " + numLines + " lines.");

    }

    public static int getNumLines (){
        return numLines;
    }

    public void output() throws Exception{

        String statsFilePath = filePath + "-Stats.csv";
        String scoreFilePath = filePath + "-Score.csv";
        CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
        CSVPrinter csvPrinter;

        //Initializes the FileWriter class, which sends an output file to the same directory with the same name appended by -Output.csv
        csvPrinter = new CSVPrinter(new FileWriter(statsFilePath), csvFormat);

        // print out the location of the CSV
        System.out.println("Printing character stats to: " + statsFilePath);
        System.out.println("Printing password scores to: " + scoreFilePath);

        ArrayList scores = score.getScores();
        // print out the counts

        /*csvPrinter.printComment("Percent of passwords containing the character in each position:");
        csvPrinter.printComment("Percent of passwords containing the character (n) from end");*/

        for (int i = 32; i <= 126; i++) {
            char curChar = (char) i;

            if (counter.charCheck(curChar)) {
                csvPrinter.print(curChar);

                for (int pos = 0; pos < 5; pos++) {
                   csvPrinter.print(counter.getPctForward(curChar, pos) + '%');
                }
                csvPrinter.print("");
                for (int pos = 0; pos < 5; pos++) {
                    csvPrinter.print(counter.getPctReverse(curChar, pos) + '%');
                }
                    csvPrinter.println();
            }
        }
        csvPrinter.close();

        csvPrinter = new CSVPrinter(new FileWriter(scoreFilePath), csvFormat);

        for (int i = 0; i < passwords.size(); i++) {
            csvPrinter.print(i);
            csvPrinter.print(passwords.get(i));
            csvPrinter.print(scores.get(i));
            csvPrinter.println();
        }
        csvPrinter.close();

    }
}