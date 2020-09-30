package com.company;

public class DividedTwoIntegersWithRestrictions {
    public int divide(int dividend, int divisor) {
        // Write your solution here
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean isPositive  = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? true : false;
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        int left = 0;
        int right = absDividend;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (mid * absDivisor == absDividend) {
                return isPositive ? mid : -mid;
            } else if (mid * absDivisor < absDividend) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (right * absDivisor <= absDividend) {
            return isPositive ? right : -right;
        }
        return isPositive ? left : -left;
    }
}
