/**
 * Updated by Jonathan Bees on 7/2/2014.
 */


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeMap;

public class Score {

    ArrayList<Double> passwordScore = new ArrayList<>();

    String csvFilePath;
    CSVFormat csvFormat = CSVFormat.EXCEL.withCommentStart(' ');
    CSVPrinter csvPrinter;



    public void checkPasswordStrength (String filePath, String curLine, int curLineNum) throws Exception{

            csvFilePath = filePath + "-PasswordStrength.csv";
            csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat);

            System.out.format("Printing password scores to: %s", csvFilePath);
            System.out.println();


        char curChar;
        TreeMap<Integer, Integer> curCounts;
        double linePct = 0;

        for (int i = 0; i < 5; i++) {
            curChar = curLine.charAt(i);
            curCounts = (TreeMap<Integer, Integer>) CharStats.charCounts.get(curChar);

            double charPct = (curCounts.get(i) / (double) numLines) * 100;
            charPct = 1/charPct;
            linePct += charPct;
        }


        if (status == 2) {
            //try {
            passwordScore.add(curLineNum, linePct);
            /*}
            catch (IndexOutOfBoundsException ex){
                passwordScore.add(0.0);
            }*/
        }

        if (status == 4) {
            double totalScore = passwordScore.get(curLineNum);
            csvPrinter.print("Line score for password at line " + curLineNum + ": ");
            csvPrinter.print(totalScore);
            curLine = new StringBuilder(curLine).reverse().toString();
            csvPrinter.print(curLine);
            csvPrinter.println();
        }
    }
}
