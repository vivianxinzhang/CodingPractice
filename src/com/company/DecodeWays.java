package com.company;

public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays s = new DecodeWays();
        System.out.println(s.numDecodeWay("0"));
        System.out.println(s.numDecodeWay("212"));
    }

    // Method 1: DP
    // M[i] represents total number of ways to decode the first number of i chars, [0, i - 1]
    // Time O(n)
    // Space O(n)
    public int numDecodeWay(String input) {
        // Write your solution here
        int[] M = new int[input.length() + 1];
        M[0] = 1;
        M[1] = input.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i < M.length; i++) {
            // case 1: decode the curr digit
            int currDigit = input.charAt(i - 1) - '0';
            if (currDigit != 0) {
                M[i] += M[i - 1];
            }
            // case 2: decode curr and pre digit together
            int twoDigits = toNumber(input, i - 2, i - 1);
            if (twoDigits >= 10 && twoDigits <= 26) {
                M[i] += M[i - 2];
            }
        }
        return M[M.length - 1];
    }

    private int toNumber(String input, int i, int j) {
        return 10 * (input.charAt(i) - '0') + (input.charAt(j) - '0');
    }
}
