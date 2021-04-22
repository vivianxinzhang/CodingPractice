package com.company;

public class StringAbbreviationMatching {
    public static void main(String[] args) {
        StringAbbreviationMatching s = new StringAbbreviationMatching();

        System.out.println(s.match("apple", "5"));  // true
        System.out.println(s.match("sophisticated", "s11d"));   // true
    }

    // Assumptions:
    // 1. The original string only contains alphabetic characters.
    // 2. Both input and pattern are not null.
    // 3. Pattern would not contain invalid information like "a0a","0"
    // Method 2: Iteration:
    // Time O(min(input.length, pattern.length))
    // Space O(1)
    public boolean match(String input, String pattern) {
        int i = 0;
        int j = 0;
        while (i < input.length() && j < pattern.length()) {
            if (input.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else if (Character.isDigit(pattern.charAt(j))) {
                int count = 0;
                while (j < pattern.length() && Character.isDigit(pattern.charAt(j))) {
                    count = count * 10 + (pattern.charAt(j) - '0');
                    j++;
                }
                i += count;
            } else {
                return false;
            }
        }
        return i == input.length() && j == pattern.length();
    }

    // Method 1: recursive way
    // Time O(min(input.length, pattern.length))
    // Space O(min(input.length, pattern.length))
    public boolean matchI(String input, String pattern) {
        return match(input, pattern, 0, 0);
    }

    private boolean match(String s, String t, int si, int ti) {
        // only when we run out of s and t at the same time there is a match
        if (si == s.length() && ti == t.length()) {
            return true;
        }
        // if we run out of one of s and t but there is still some
        // characters left for the other one, we cannot find the match
        if (si >= s.length() || ti >= t.length()) {
            return false;
        }
        // case 1. if the current character in t is not a digit
        if (!Character.isDigit(t.charAt(ti))) {
            if (s.charAt(si) == t.charAt(ti)) {
                return match(s, t, si + 1, ti + 1);
            }
            return false;
        }
        // we need to find in total what is the number
        // e.g. "123" means number 123
        // case 2. if the current character in t is a digit.
        int count = 0;
        while (ti < t.length() && Character.isDigit(t.charAt(ti))) {
            count = count * 10 + (t.charAt(ti) - '0');
            ti++;
        }
        return match(s, t, si + count, ti);
    }
}
