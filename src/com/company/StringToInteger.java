package com.company;

public class StringToInteger {
    public static void main(String[] args) {
        StringToInteger s = new StringToInteger();
        System.out.println();
    }

    // The string can contain additional characters after those that form the integral number,
    // which are ignored and have no effect on the behavior of this function.
    // If the first sequence of non-whitespace characters in str is not a valid integral number,
    // or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
    // If no valid conversion could be performed, a zero value is returned.
    // If the correct value is out of the range of representable values, return INT_MAX (2147483647) if it is positive, or INT_MIN (-2147483648) if it is negative.
    // Time O(n)
    // Space O(1)
    public int atoi(String input) {
        input.trim();
        if (input == null || input.length() == 0) {
            return 0;
        }
        int sign = 1;
        int index = 0;
        char c = input.charAt(0);
        if (c == '+') {
            sign = 1;
            index++;
        } else if (c == '-') {
            sign = -1;
            index++;
        }
        long sum = 0;
        for (int i = index; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(index))) {
                return (int) sum * sign;
            }
            sum = sum * 10 + input.charAt(i) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) sum * sign;
    }
}
