/**
 * Updated by Adam Durity on 7/14/2014.
 */


public class Scorer {
    CharStats charStats;

    public Scorer(CharStats charStats) {
        this.charStats = charStats;
    }

    public double score(String password) {
        int i;
        char ch;
        double passwordScore = 0.0;
        String reversePassword = new StringBuilder(password).reverse().toString();

        // calculate forward score
        i = 0;
        while (i < 5 && i < password.length()) {
            ch = password.charAt(i);
            passwordScore += 1 / charStats.getForwardWeight(ch, i);
            i++;
        }

        // calculate reverse score
        i = 0;
        while (i < 5 && i < reversePassword.length()) {
            ch = reversePassword.charAt(i);
            passwordScore += 1 / charStats.getReverseWeight(ch, i);
            i++;
        }

        return passwordScore;
    }
}