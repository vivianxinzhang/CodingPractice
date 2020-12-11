package com.company;
import java.util.Arrays;

public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching s = new WildcardMatching();
        String input = "aabbbcaa";
        String pattern = "?a?bc";
        System.out.println(s.match(input, pattern));    // false
        System.out.println(s.matchI(input, pattern));    // false
        System.out.println(s.matchII(input, pattern));    // false

        input = "baaabab";
        pattern = "******ba*****ab";
        System.out.println(s.match(input, pattern));    // true
        System.out.println(s.matchI(input, pattern));    // true
        System.out.println(s.matchII(input, pattern));    // true

        pattern = "******ba*****ab";
        System.out.println(s.match(input, pattern));    // true
        System.out.println(s.matchI(input, pattern));    // true
        System.out.println(s.matchII(input, pattern));    // true


        pattern = "******ba*****ab";
        System.out.println(s.match(input, pattern));    // true
        System.out.println(s.matchI(input, pattern));    // true
        System.out.println(s.matchII(input, pattern));    // true
    }

    // Method 2:
    // M[i][j represents whether the first i characters of input string
    // matches the first j characters of pattern.
    // Time O(mn*n)
    // Space O(mn)
    public boolean match(String input, String pattern) {
        boolean[][] M = new boolean[input.length() + 1][pattern.length() + 1];
        for (int i = 0; i <= input.length(); i++) {
            for (int j = 0; j <= pattern.length(); j++) {
                if (i == 0 && j == 0) {
                    M[i][j] = true;
                } else if (j == 0) {
                    M[i][j] = false;
                } else if (i == 0) {
                    // only if all previous chars are also stars and true
                    // can match at current position
                    if (pattern.charAt(j - 1) == '*') {
                        M[i][j] = M[i][j - 1];
                    } else {
                        M[i][j] = false;
                    }
                } else {
                    if (pattern.charAt(j - 1) == input.charAt(i - 1) || pattern.charAt(j - 1) == '?') {
                        M[i][j] = M[i - 1][j - 1];
                    } else if (pattern.charAt(j - 1) == '*') {
                        //         * match 1  or  * match 0
                        M[i][j] = M[i - 1][j] || M[i][j - 1];
                    } else {
                        M[i][j] = false;
                    }
                }
            }
        }
        return M[input.length()][pattern.length()];
    }

    // Method 1:
    // Time O()
    // Space O()
    public boolean matchI(String input, String pattern) {
        boolean[][] M = new boolean[input.length() + 1][pattern.length() + 1];
        for (int i = 0; i <= input.length(); i++) {
            for (int j = 0; j <= pattern.length(); j++) {
                if (i == 0 && j == 0) {
                    M[i][j] = true;
                } else if (j == 0) {
                    M[i][j] = false;
                } else if (i == 0) {
                    // only if all previous chars are also stars and true
                    // can match at current position
                    if (pattern.charAt(j - 1) == '*') {
                        M[i][j] = M[i][j - 1];
                    } else {
                        M[i][j] = false;
                    }
                } else {
                    if (pattern.charAt(j - 1) == input.charAt(i - 1) || pattern.charAt(j - 1) == '?') {
                        M[i][j] = M[i - 1][j - 1];
                    } else if (pattern.charAt(j - 1) == '*') {
                        // * match 0
                        M[i][j] = M[i][j - 1];
                        // * match 0 - i
                        for (int k = 0; k < i; k++) {
                            if (M[i][j] == true) {
                                break;
                            } else if (M[k][j] == true) {
                                M[i][j] = true;
                            }
                        }
                    } else {
                        M[i][j] = false;
                    }
                }
            }
        }
        return M[input.length()][pattern.length()];
    }

    // another implementation
    public boolean matchII(String str, String pattern) {
        boolean[][] M = new boolean[str.length() + 1][pattern.length() + 1];
        for (int i = 0; i <= str.length(); i++) {
            Arrays.fill(M[i], false);
        }
        // empty pattern can match with empty string
        M[0][0] = true;
        // only '*' can match with empty string
        for (int j = 1; j <= pattern.length(); j++) {
            if (pattern.charAt(j - 1) == '*') {
                M[0][j] = M[0][j - 1];
            }
        }
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                // Two cases if we see a '*'
                // a) we ignore '*' character and move to next character
                // in the pattern, i.e., '*' indicates an empty sequence.
                // b) '*' character matches with ith character in input
                if (pattern.charAt(j - 1) == '*') {
                    M[i][j] = M[i][j - 1] || M[i - 1][j];
                }
                // a) the current character of the pattern is '?'
                // b) characters actually match
                else if (pattern.charAt(j - 1) == '?' ||
                        str.charAt(i - 1) == pattern.charAt(j - 1)) {
                    M[i][j] = M[i - 1][j - 1];
                } else {
                    M[i][j] = false;
                }
            }
        }
        return M[str.length()][pattern.length()];
    }
}
