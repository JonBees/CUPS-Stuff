/**
 * Updated by Jonathan Bees on 7/8/2014.
 */


import java.util.ArrayList;

public class Score {

    CharStats charStats = new CharStats();
    ArrayList<Double> passwordScore = new ArrayList<>();

    public double checkPasswordStrength (String curLine) {

        char curChar;
        double lineScore = 0;

        for (int i = 0; i < 5; i++) {
            curChar = curLine.charAt(i);
            double charScore = 1 / charStats.getPctForward(curChar, i);
            lineScore += charScore;
        }
        for (int i = curLine.length()-1; i > curLine.length() - 6; i--) {
            curChar = curLine.charAt(i);
            double charScore = 1 / charStats.getPctReverse(curChar, i);
            lineScore += charScore;
        }

        return lineScore;
    }

    public void compileScores(ArrayList<String> passwords) {
        for (int i = 0; i < passwords.size(); i++) {
                passwordScore.add(i, checkPasswordStrength(passwords.get(i)));
        }
    }

    public ArrayList getScores (){
        return passwordScore;
    }
}