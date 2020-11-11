package com.company;

public class LeastInsertionsToFormAPalindrome {
    public static void main(String[] args) {
        LeastInsertionsToFormAPalindrome s = new LeastInsertionsToFormAPalindrome();
        String input = "zzazz";
        System.out.println(s.leastInsertion(input));

        input = "mbadm";
        System.out.println(s.leastInsertion(input));    // 2 "mbdadbm" or "mdbabdm"

        input = "leetcode";
        System.out.println(s.leastInsertion(input));    // 5 "leetcodocteel"
    }

    // dp[i][j] := min chars to insert
    // base case: dp[i][i] = 0, dp[i][i-1] = 0
    // dp[i][j] = dp[i+1][j-1] if s[i] == s[j] else min(dp[i+1][j] , dp[i][j-1]) + 1
    // ans: dp[0][n-1]
    // Time O(n^2)
    // Space O(n^2)
    public int leastInsertion(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        int n = input.length();
        int[][] M = new int[n][n];
        // 填表需要从最短的字符串开始 长度为0 长度为1 ... 长度为n
        for (int length = 2; length <= n; length++) {
            // for (i = 0; i < n - length + 1; i++)
            // start: i
            // end: i + length - 1
            for (int i = 0, j = length - 1; j < n; i++, j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    M[i][j] = M[i + 1][j - 1];
                } else {
                    M[i][j] = Math.min(M[i + 1][j], M[i][j - 1]) + 1;
                }
            }
        }
        return M[0][n - 1];
    }
}
