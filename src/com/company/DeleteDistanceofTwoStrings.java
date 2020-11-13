package com.company;

public class DeleteDistanceofTwoStrings {
    public static void main(String[] args) {
        DeleteDistanceofTwoStrings s = new DeleteDistanceofTwoStrings();
        System.out.println(s.minNumOfDelete("x", "xy"));

        System.out.println(s.minNumOfDelete("laioffer", "xaioffey"));
    }

    // Method 1: dp
    // Time O(mn)
    // Space O(mn)
    public int minNumOfDelete(String s1, String s2) {
        // M[i][j] represents minimum number of delete operation to make
        // the first i letters of s1 and the first j letter of s2 equal
        // Case 1: s1.charAt(i-1) == s2.charAt(j - 1), M[i][j] = M[i-1][j-1]
        // Case 2: s1.charAt(i-1) == s2.charAt(j - 1), M[i - 1][j] + 1, M[i][j - 1] + 1
        int[][] M = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    M[i][j] = j;
                } else if (j == 0) {
                    M[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    M[i][j] = M[i - 1][j - 1];
                } else {
                    M[i][j] = Math.min(M[i - 1][j] + 1, M[i][j - 1] + 1);
                }
            }
        }
        return M[s1.length()][s2.length()];
    }
}