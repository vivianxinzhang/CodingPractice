package com.company;

public class RegularExpressionMatching {
    public static void main(String[] args) {
        RegularExpressionMatching s = new RegularExpressionMatching();
        System.out.println(s.isMatch("xaabyc", "xa*b.c"));
        /*  0 1 2 3 4 5 6
              x a * b . c
        0   T F F F F F F
        1 x F T F T F F F
        2 a F F T T F F F
        3 a F F F T F F F
        4 b F F F F T F F
        5 y F F F F F T F
        6 c F F F F F F T
        * **/
    }

    // Base Case:
    // M[0][0] = true
    // M[0][j] = M[0][j - 2] if pattern[j-1] = '*'
    // M[i][0] = false
    // Induction rule:
    // Case 1: if str[i-1] == pattern[j-1] || pattern[j-1] == '.'
    // T[i][j] = T[i-1][j-1]
    // Case 2: pattern[j] == '*'
    // 2.1 T[i][j] = T[i][j-2] -> 0 occurrence
    // ||
    // 2.2 T[i][j] = T[i-1][j] if str[i-1] == pattern[j-2] || pattern[j-1]=='.'
    // Case 3: otherwise
    // T[i][j] = false
    // Time O(mn)
    // Space O(mn)
    public boolean isMatch(String text, String pattern) {
        boolean[][] M = new boolean[text.length() + 1][pattern.length() + 1];
        for (int i = 0; i <= text.length(); i++) {
            for (int j = 0; j <= pattern.length(); j++) {
                if (i == 0 && j == 0) {
                    M[i][j] = true;
                } else if (i == 0) {    // i >= 1
                    if (pattern.charAt(j - 1) == '*') {
                        M[i][j] = M[i][j - 2];
                    }
                } else if (j == 0) {
                    M[i][j] = false;
                } else {
                    if (pattern.charAt(j - 1) == text.charAt(i - 1) || pattern.charAt(j - 1) == '.') {
                        M[i][j] = M[i - 1][j -1];
                    } else if (pattern.charAt(j - 1) == '*') {
                        M[i][j] = M[i][j - 2];      // match 0 occurrence of previous char in pattern
                        // match 1 char in str
                        if (pattern.charAt(j - 2) == '.' || pattern.charAt(j - 2) == text.charAt(i - 1)) {
                            M[i][j] = M[i][j] || M[i - 1][j];
                        }
                    }  else {
                        M[i][j] = false;
                    }
                }
            }
        }
        return M[text.length()][pattern.length()];
    }
}
