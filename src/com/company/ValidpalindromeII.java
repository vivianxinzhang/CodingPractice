package com.company;

public class ValidpalindromeII {
    public static void main(String[] args) {
        ValidpalindromeII s = new ValidpalindromeII();

        System.out.println(s.validPalindrome("aba"));
        System.out.println(s.validPalindrome("abca"));
    }

    // Intuition:
    // If the beginning and ending characters of a string are the same (ie. s[0] == s[s.length - 1]),
    // then whether the inner characters are a palindrome (s[1], s[2], ..., s[s.length - 2])
    // uniquely determines whether the entire string is a palindrome.
    // Algorithm:
    // Suppose we want to know whether s[i], s[i+1], ..., s[j] form a palindrome. If i >= j then we are done.
    // If s[i] == s[j] then we may take i++; j--.
    // Otherwise, the palindrome must be either s[i+1], s[i+2], ..., s[j] or s[i], s[i+1], ..., s[j-1],
    // and we should check both cases.
    // Time O(n)
    // Space O(1)
    public boolean validPalindrome(String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            // left                             right
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                int j = input.length() - 1 - i;
                boolean a = validPalindrome(input, i + 1, j);   // delete i
                boolean b = validPalindrome(input, i, j - 1);   // delete j
                return a || b;
            }
        }
        // do not need to delete
        return true;
    }

    private boolean validPalindrome(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) {
                return false;
            }
        }
        return true;
    }
}
