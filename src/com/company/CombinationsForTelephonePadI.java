package com.company;
import java.util.*;

public class CombinationsForTelephonePadI {
    public static void main(String[] args) {
        CombinationsForTelephonePadI s = new CombinationsForTelephonePadI();

        System.out.println(Arrays.toString(s.combinations(231)));
        // [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(Arrays.toString(s.combinations(78)));
        // [pt, pu, pv, qt, qu, qv, rt, ru, rv, st, su, sv]
    }

    // Assumptions: The given number >= 0.
    // Time O(4^n)  n is # of digits in input
    // Space O(n)
    public String[] combinations(int number) {
        // charPad[i] represents characters represented by number i
        String[] charPad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(Integer.toString(number), charPad, 0, sb, result);
        return result.toArray(new String[0]);
    }

    private void dfs(String String, String[] charPad, int level, StringBuilder sb, List<String> result) {
        // no more number to consider
        if (level == String.length()) {
            result.add(sb.toString());
            return;
        }
        // what is the number at index i? String.charAt(level) - '0'
        // what are the character need to be considered at index i? charPad[String.charAt(level) - '0']
        char[] charArray = charPad[String.charAt(level) - '0'].toCharArray();
        // no char to consider at this level
        if (charArray.length == 0) {
            dfs(String, charPad, level + 1, sb, result);
        } else {
            for (int i = 0; i < charArray.length; i++) {
                sb.append(charArray[i]);
                dfs(String, charPad, level + 1, sb, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
