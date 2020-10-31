package com.company;

public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        LongestPalindromeSubsequence s = new LongestPalindromeSubsequence();
        System.out.println(s.longestPalindrome("bbc"));

        System.out.println(s.longestPalindrome("abcbcbd"));

        System.out.println(s.longestPalindrome("abca"));
    }

    // M[i][j] is the length of the longest palindromic subsequence in s[i ... j]
    // index  0 1 2 3 4
    // s =    b b b a b

    // Base case
    // M[i][i-1] = 0 (size = 0) empty string
    // M[i][j] = 1   (size = 1)

    // Induction rule
    // size = 2
    // M[0][1] = 2       a[0] = a[1]
    // M[1][2] = 2       a[1] = a[2]
    // M[2][3] = max(M[2][2], M[3][3]) = 1      a[2] != a[3]
    // M[3][4] = max(M[3][3], M[4][4] = 1       a[3] != a[4]

    // size = 3
    // M[0][2] = 2 + M[1][1]    a[0] = a[2]
    // M[1][3] = max(M[1][2], M[2][3]) = 2  a[1] != a[3]
    // M[2][4] = 2 + M[3][3] = 3    a[2] = a[4]

    //Generalized
    // Case 1: if s[i] == s[j], M[i][j] = 2 + M[i+1][j-1]
    // Case 2: if s[i] != s[j], M[i][j] = max(M[i+1][j], M[i][j-1])

    // Method 1:
    // Time O(n^2)
    // Space O(n^2)
    public int longestPalindromeI(String input) {
        // Write your solution here
        int[][] M = new int[input.length()][input.length()];
        // Base case
        // M[i][i-1] = 0 (size = 0) empty string
        // M[i][j] = 1   (size = 1)
        for (int i = 0; i < input.length(); i++) {
            M[i][i] = 1;
            if (i > 1) {
                M[i][i - 1] = 0;
            }
        }
        // M[i]j] depends on M[i+1][j-1](左下), M[i+1][j](下), M[i][j-1](左)
        // from bottom to up, left to right to fill M
        for (int i = input.length() - 1; i >= 0; i--) { // i represents row
            for (int j = i + 1; j < input.length(); j++) {  // j represents col
                if (input.charAt(i) == input.charAt(j)) {
                    M[i][j] = 2 + M[i + 1][j - 1];
                } else {
                    M[i][j] = Math.max(M[i + 1][j], M[i][j - 1]);
                }
            }
        }
        return M[0][input.length() - 1];
    }

    // Method 2:
    // Time O(n^2)
    // Space O(n)
    public int longestPalindromeII(String input) {
        // Write your solution here
        int[][] M = new int[input.length()][input.length()];
        // Base case
        // M[i][i-1] = 0 (size = 0) empty string
        // M[i][j] = 1   (size = 1)
        for (int i = 0; i < input.length(); i++) {
            M[i][i] = 1;
            if (i > 1) {
                M[i][i - 1] = 0;
            }
        }
        int[] f = new int[input.length()];
        // M[i]j] depends on M[i+1][j-1](左下), M[i+1][j](下), M[i][j-1](左)
        // from bottom to up, left to right to fill M
        for (int i = input.length() - 1; i >= 0; i--) { // i represents row
            f[i] = 1;
            int mIPlusOneJMinusOne = 0;
            for (int j = i + 1; j < input.length(); j++) {  // j represents col
                // Invariant: f[k] == M[i][k]    if k < j, or
                //                    M[i+1][k]  if K >= j
                //            mIPlusOneJMinusOne = M[i+1][j-1]
                int mIJ;
                if (input.charAt(i) == input.charAt(j)) {
                    mIJ = 2 + mIPlusOneJMinusOne;
                } else {
                    mIJ = Math.max(f[j - 1], f[j]);
                }
                mIPlusOneJMinusOne = f[j];
                f[j] = mIJ;
            }
        }
        return f[input.length() - 1];
    }

    // Another implementation:
    // Time O(n^2)
    // Space O(n^2)
    public int longestPalindromeIII(String input) {
        int[][] M = new int[input.length()][input.length()];
        // Base case
        // M[i][i-1] = 0 (size = 0) empty string
        // M[i][j] = 1   (size = 1)
        // M[i]j] depends on M[i+1][j-1](左下), M[i+1][j](下), M[i][j-1](左)
        // from bottom to up, left to right to fill M
        for (int length = 1; length <= input.length(); length++) {
            for (int i = 0; i <= input.length() - length; i++) {
                int j = i + length - 1;
                if (i == j) {
                    M[i][j] = 1;
                    continue;
                }
                if (input.charAt(i) == input.charAt(j)) {
                    M[i][j] = 2 + M[i + 1][j - 1];
                } else {
                    M[i][j] = Math.max(M[i + 1][j], M[i][j - 1]);
                }
            }
        }
        return M[0][input.length() - 1];
    }

    // Another implementation with space optimization: ???
    // Time O(n^2)
    // Space O(n)
    public int longestPalindrome(String input) {
        // int[][] M = new int[input.length()][input.length()];
        // dp0[i]表示起始位置为i, 长度为l的解
        int[] dp0 = new int[input.length()];    // sols of len = l
        int[] dp1 = new int[input.length()];    // sols of len = l - 1
        int[] dp2 = new int[input.length()];    // sols of len = l - 2
        for (int length = 1; length <= input.length(); length++) {
            for (int i = 0; i <= input.length() - length; i++) {
                int j = i + length - 1;
                if (i == j) {
                    // M[i][j] = 1;
                    dp0[i] = 1;
                    continue;
                }
                if (input.charAt(i) == input.charAt(j)) {
                    // M[i][j] = 2 + M[i + 1][j - 1];
                    dp0[i]= dp2[i + 1] + 2;
                } else {
                    // M[i][j] = Math.max(M[i + 1][j], M[i][j - 1]);
                    dp0[i] = Math.max(dp1[i], dp1[i + 1]);
                }
            }
            int[] tmp = dp2;
            dp2 = dp1;
            dp1 = dp0;
            dp0 = tmp;
        }
        // return M[0][input.length() - 1];
        return dp1[0];
    }
}
