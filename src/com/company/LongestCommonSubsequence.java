package com.company;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence s = new LongestCommonSubsequence();
        String one = "abcde";
        String two = "cbabdfe";
        System.out.println(s.longest(one, two));  // cd

        one = "sigh";
        two = "asith";
        System.out.println(s.longest(one, two));  // si
    }

    // Assumptions:
    // The two given strings are not null.
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
