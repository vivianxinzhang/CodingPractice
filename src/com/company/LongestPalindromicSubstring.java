package com.company;
import java.util.Arrays;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring s = new LongestPalindromicSubstring();
        System.out.println(s.longestPalindrome("bbc"));

        System.out.println(s.longestPalindrome("abcbcbd"));

        System.out.println(s.longestPalindrome("abbc"));
    }

    // Try all possible i and find the longest palindromic string
    // whose center is i (odd case) and i / i + 1 (even case).
    // Time O(n^2)
    // Space O(1)
    public String longestPalindrome(String input) {
        // Write your solution here
        int maxLength = 0;
        int start = 0;
        int globalLeft = 0;
        int globalRight = 0;
        for (int i = 0; i < input.length(); i++) {
            // odd length(center i)
            int oldLength = getLen(input, i, i);
            // even length(center i, i+1)
            int evenLength = getLen(input, i, i + 1);
            int currLength = Math.max(oldLength, evenLength);
            if (currLength > maxLength) {
                maxLength = currLength;
                globalLeft = i - (currLength - 1) / 2;
            }
        }
        return input.substring(globalLeft, globalLeft + maxLength);
    }

    private int getLen(String input, int l, int r) {
        while (l >= 0 && r < input.length() && input.charAt(l) == input.charAt(r)) {
            --l;
            ++r;
        }
        return r - l -1;
    }

    // Time O(n^2)
    // Space O(1)
    public String longestPalindromeII(String input) {
        // Write your solution here
        int maxLength = 0;
        int start = 0;
        int globalLeft = 0;
        int globalRight = 0;
        for (int i = 1; i < input.length(); i++) {
            int currLength = 1;
            // Case 1: palindrome has odd length
            int left = i;
            int right = i;
            while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
                left--;
                right++;
            }
            currLength = right - left - 1;
            if (currLength > maxLength) {
                maxLength = currLength;
                globalLeft = left + 1;
                globalRight = right - 1;
            }
            // Case 1: palindrome has even length
            left = i;
            right = i + 1;
            while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
                left--;
                right++;
            }
            currLength = right - left - 1;
            if (currLength > maxLength) {
                maxLength = currLength;
                globalLeft = left + 1;
                globalRight = right - 1;
            }
        }
        return input.substring(globalLeft, globalRight + 1);
    }


    // Method 1: brute force
    // Time O(n^3)
    // Space O(1)
    public String longestPalindromeI(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        int maxLength = 0;
        int globalLeft = 0;
        int globalRight = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j < input.length(); j++) {
                int currLength = j - i + 1;
                if (currLength > maxLength && isPalindrome(input, i, j)) {
                    maxLength = currLength;
                    globalLeft = i;
                    globalRight = j;
                }
            }
        }
        return input.substring(globalLeft, globalRight + 1);
    }

    private boolean isPalindrome(String input, int i, int j) {
        while (i < j) {
            if (input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

