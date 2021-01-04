package com.company;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence s = new LongestCommonSubsequence();
        String one = "abcde";
        String two = "cbabdfe";
        System.out.println(s.longest(one, two));  // 4

        one = "sigh";
        two = "asith";
        System.out.println(s.longest(one, two));  // 3
    }

    // Assumptions:
    // The two given strings are not null.
    // M[i][j] represents the length of the longest common subsequence between a[0 … i-1] (the first i letters of a)
    // and b[0 … j-1] (the first j letters of b) that includes a[i-1] and b[j-1].
    // Base case:
    // M[0][0] = 0
    // M[i][0] = 0
    // M[0][j] = 0
    // Induction rule:
    // M[i][j] = 1 + M[i-1][j-1]	                if a[i-1] == b[j-1]
    //	         Math.max(M[i-1][j], M[i][j-1])		if a[i-1] != b[j-1]
    // Time O(mn)
    // Space O(mn)
    public int longest(String source, String target) {
        // using the trick of "add 1" index, will make the base case easier to handle,
        // e.g. the 0th row and the 0th column will be all zero.
        int[][] longest = new int[source.length() + 1][target.length() + 1];
        for (int i = 1; i <= source.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    longest[i][j] = longest[i - 1][j - 1] + 1;
                } else {
                    longest[i][j] = Math.max(longest[i - 1][j], longest[i][j - 1]);
                }
            }
        }
        return longest[source.length()][target.length()];
    }
}
