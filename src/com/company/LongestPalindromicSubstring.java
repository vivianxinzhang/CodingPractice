package com.company;
import java.util.Arrays;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring s = new LongestPalindromicSubstring();
        System.out.println(s.longestPalindrome("bbc")); // bb

        System.out.println(s.longestPalindrome("abcbcbd")); // bcbcb

        System.out.println(s.longestPalindrome("abbc"));    // bb
    }

    // Optimization: remove redundant computation / reuse computation / DP
    // 1. if s[i ~ j] is a palindrome, s[i+1 ~ j-1] is also a one.
    // 2. if s[i ~ j] is not a palindrome, then s[i-1 ~ j+1] is also not a palindrome.
    // e.g. abcea, bce is not a palindrome, abcea can't be one
    // Instead of starting from two sides, we can start from the center. For each index i,
    // or pair (i, i+1) we take it as the middle elements and expand toward tow ends to find
    // the longest palindrome.
    // if dp[l+1][r-1] and s[l] == s[r]: dp[l][r] = true
    // This step takes O(n), and there are O(n) indices
    // Try all possible i and find the longest palindromic string
    // whose center is i (odd case) or i and i + 1 (even case).
    // Time O(n^2)
    // Space O(1)
    public String longestPalindrome(String input) {
        int longest = 0;
        int left = 0;
        for (int i = 0; i < input.length(); i++) {
            // odd length - length of longest longest palindromic string with center i
            int oddLength = getLongestPalindromeLength(input, i, i);
            // even length - length of longest longest palindromic string with center i and i + 1
            int evenLength = getLongestPalindromeLength(input, i, i + 1);
            int currMaxLength = Math.max(oddLength, evenLength);
            if (currMaxLength > longest) {
                longest = currMaxLength;
                left = i - (currMaxLength - 1) / 2;
            }
        }
        return input.substring(left, left + longest);
    }

    // Compute length of longest Palindromic Substring with center left and right
    // odd length (center: right = left)
    // even length (center: right = left + 1)
    private int getLongestPalindromeLength(String input, int left, int right) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return right - left -1;
    }

    // Method 1: brute force
    // Try all possible substrings, and check each substring is palindrome or not
    // There are O(n^2) substrings, and takes O(n) to to check.
    // Time O(n^3)
    // Space O(1)
    public String longestPalindromeI(String input) {
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

    // Time O(n)
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

