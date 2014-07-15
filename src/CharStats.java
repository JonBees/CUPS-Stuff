/**
 * Updated by Adam Durity on 7/14/2014.
 */


import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class CharStats {

    int numPasswords;
    HashMap<Character, SortedMap<Integer, Integer>> charCountsForward, charCountsReverse;

    public CharStats(List<String> passwords) {
        numPasswords = passwords.size();
        charCountsForward = countChars(passwords, false);
        charCountsReverse = countChars(passwords, true);
    }

    public HashMap<Character, SortedMap<Integer, Integer>> countChars(List<String> passwords, boolean reverse) {
        HashMap<Character, SortedMap<Integer, Integer>> charCounts = new HashMap<>();
        Character c;
        TreeMap<Integer, Integer> cCounts;
        Integer posCount;

        for (String s : passwords) {
            if (reverse) {
                s = new StringBuilder(s).reverse().toString();
            }

            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                cCounts = (TreeMap<Integer, Integer>) charCounts.get(c);

                if (cCounts == null) {
                    cCounts = new TreeMap<>();
                }

                posCount = cCounts.get(i);

                if (posCount == null) {
                    posCount = 1;
                } else {
                    posCount++;
                }

                cCounts.put(i, posCount);
                charCounts.put(c, cCounts);
            }
        }

        return charCounts;
    }

    protected int getCount(char ch, int pos, boolean reverse) {
        HashMap<Character, SortedMap<Integer, Integer>> charCounts;
        TreeMap <Integer, Integer> cCounts;
        int posCount = 0;

        if (reverse) {
            charCounts = charCountsReverse;
        } else {
            charCounts = charCountsForward;
        }

        if (charCounts.containsKey(ch)) {
            cCounts = (TreeMap<Integer, Integer>) charCounts.get(ch);

            if (cCounts.containsKey(pos)) {
                posCount = cCounts.get(pos);
            }
        }

        return posCount;
    }

    public int getForwardCount(char ch, int pos) {
        return getCount(ch, pos, false);
    }

    public int getReverseCount(char ch, int pos) {
        return getCount(ch, pos, true);
    }

    protected double getWeight(char ch, int pos, boolean reverse) {
        return (getCount(ch, pos, reverse) * 100.0) / numPasswords;
    }

    public double getForwardWeight(char ch, int pos) {
        return getWeight(ch, pos, false);
    }

    public double getReverseWeight(char ch, int pos) {
        return getWeight(ch, pos, true);
    }
}