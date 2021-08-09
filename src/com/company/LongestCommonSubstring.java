package com.company;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring s = new LongestCommonSubstring();

        String one = "abcde";
        String two = "cdf";
        System.out.println(s.longestCommon(one, two));  // cd

        one = "sigh";
        two = "asith";
        System.out.println(s.longestCommon(one, two));  // si
    }

    // Assumptions:
    // The two given strings are not null.
    // M[i][j] represents the length of the longest common substring between a[0 … i-1] (the first i letters of a)
    // and b[0 … j-1] (the first j letters of b) that includes a[i-1] and b[j-1].
    // Base case:
    // M[0][0] = 0
    // M[i][0] = 0
    // M[0][j] = 0
    // Induction rule:
    // M[i][j] = 1 + M[i-1][j-1]	if a[i-1] == b[j-1]
    //	          0		        	if a[i-1] != b[j-1]
    // Time O(mn)
    // Space O(mn)
    public String longestCommon(String source, String target) {
        char[] sa = source.toCharArray();
        char[] ta = target.toCharArray();
        // record the longest common substring's start position in s.
        int start = 0;
        // record the longest common substring's length.
        int longest = 0;
        int[][] common = new int[sa.length][ta.length];
        for (int i = 0; i < sa.length; i++) {
            for (int j = 0; j < ta.length; j++) {
                if (sa[i] == ta[j]) {
                    if (i == 0 || j == 0) {
                        common[i][j] = 1;
                    } else {
                        common[i][j] = common[i - 1][j - 1] + 1;
                    }
                    if (common[i][j] > longest) {
                        longest = common[i][j];
                        // ending index of current common substring in source is i - 1
                        // start = i - 1 - longest + 1
                        start = i - longest + 1;
                    }
                }
            }
        }
        return source.substring(start, start + longest);
    }

    public String longestCommonI(String source, String target) {
        int[][] M = new int[source.length() + 1][target.length() + 1];
        int longest = 0;
        int start = 0;
        for (int i = 0; i <= source.length(); i++) {
            for (int j = 0; j <= target.length(); j++) {
                if (i == 0) {
                    M[i][j] = 0;
                } else if (j == 0) {
                    M[i][j] = 0;
                } else {
                    if (source.charAt(i - 1) == target.charAt(j - 1)) {
                        M[i][j] = M[i - 1][j - 1] + 1;
                    } else {
                        M[i][j] = 0;
                    }
                    if (M[i][j] > longest) {
                        longest = M[i][j];
                        // ending index of current common substring in source is i - 1
                        // start = i - 1 - longest + 1
                        start = i - longest;
                    }
                }
            }
        }
        return source.substring(start, start + longest);
    }
}
