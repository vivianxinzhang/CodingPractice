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
                        start = i - longest + 1;
                    }
                }
            }
        }
        return source.substring(start, start + longest);
    }
}
