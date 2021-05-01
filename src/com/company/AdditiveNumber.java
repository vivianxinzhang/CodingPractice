package com.company;

public class AdditiveNumber {
    public static void main(String[] args) {
        AdditiveNumber s = new AdditiveNumber();

        String str = "15423";
        System.out.println(s.isAdditiveNumber(str));    // false

    }

    // Note: Numbers in the additive sequence cannot have leading zeros,
    // so sequence 1, 2, 03 or 1, 02, 3 is invalid.
    // Method 1: Recursion
    // Time O(n^3)
    // Space O(n)
    public boolean isAdditiveNumber(String input) {
        for (int i = 1; i < input.length(); i++) {
            for (int j = i + 1; j < Math.min(i + 10, input.length()); j++) {
                int num1 = Integer.valueOf(input.substring(0, i));
                int num2 = Integer.valueOf(input.substring(i, j));
                if (isAdditiveHelper(num1, num2, input, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditiveHelper(int num1, int num2, String input, int fromIndex) {
        if (fromIndex == input.length()) {
            return true;
        }
        int num3 = num1 + num2;
        int nextIdx = fromIndex + getNumberOfDigits(num3);
        if (nextIdx <= input.length() && Integer.valueOf(input.substring(fromIndex, nextIdx)) == num3) {
            return isAdditiveHelper(num2, num3, input, nextIdx);
        }
        return false;
    }

    private int getNumberOfDigits(int num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }
}