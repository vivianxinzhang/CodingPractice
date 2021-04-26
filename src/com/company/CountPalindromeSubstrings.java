package com.company;

public class CountPalindromeSubstrings {
    public static void main(String[] args) {
        CountPalindromeSubstrings s = new CountPalindromeSubstrings();

        System.out.println(s.countPalindromeSubs("aaa"));   // 6
        System.out.println(s.countPalindromeSubs("abc"));   // 3
    }

    // Assumptions:
    // The input string is not null.
    // Time O(mn)
    // Space O(mn)
    public int countPalindromeSubs(String input) {
        boolean[][] M = new boolean[input.length()][input.length()];
        int res = 0;
        // base case:
        for (int i = 0; i < input.length(); i++) {
            M[i][i] = true;
            if (M[i][i]) {
                res++;
            }
            if (i > 0 && input.charAt(i - 1) == input.charAt(i)) {
                M[i - 1][i] = true;
                if (M[i - 1][i]) {
                    res++;
                }
            }
        }
        for (int length = 3; length <= input.length(); length++) {
            for (int left = 0; left <= input.length() - length; left++) {
                int right = left + length - 1;
                if (input.charAt(left) == input.charAt(right)) {
                    M[left][right] = M[left + 1][right - 1];
                    if (M[left][right]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
