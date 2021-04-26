package com.company;

public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber s = new PalindromeNumber();

        System.out.println(s.isPalindrome(123321));
        System.out.println(s.isPalindrome(123));
        System.out.println(s.isPalindrome(121));
        System.out.println(s.isPalindrome(221));
    }

    // Time O(n)
    // Space O(1)
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int numOfDigits = countDigits(x);
        while (numOfDigits > 1) {
            int i = numOfDigits - 1;
            int j = 1;
            // 1 2 3 3 2 1
            int leftDigit = x / (int) (Math.pow(10, i));
            int rightDigit = x % (int) (Math.pow(10, j));
            if (leftDigit != rightDigit) {
                return false;
            }
            x %= (int) (Math.pow(10, i));       // delete first digit
            x /= 10;                            // delete last digit
            // 2 3 3 2
            numOfDigits -= 2;
        }
        return true;
    }

    private int countDigits(int x) {
        int count = 0;
        for (int num = x; num > 0; num /= 10) {
            count++;
        }
        return count;
    }
}
