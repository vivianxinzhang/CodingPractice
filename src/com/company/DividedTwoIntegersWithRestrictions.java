package com.company;

public class DividedTwoIntegersWithRestrictions {
    public static void main(String[] args) {
        DividedTwoIntegersWithRestrictions s = new DividedTwoIntegersWithRestrictions();
        // Examples:
        // 0 / 1 = 0
        System.out.println(s.divide(0, 1));     // 0
        // 1 / 0 = Integer.MAX_VALUE
        System.out.println(s.divide(1, 0));     // 2147483647
        // -1 / 0 = Integer.MAX_VALUE
        System.out.println(s.divide(-1, 0));    // 2147483647
        // 11 / 2 = 5
        System.out.println(s.divide(11, 2));    // 5
        // -11 / 2 = -5
        System.out.println(s.divide(-11, 2));   // -5
        // 11 / -2 = -5
        System.out.println(s.divide(11, -2));   // -5
        // -11 / -2 = 5
        System.out.println(s.divide(-11, -2));  // 5
    }

    // Method 2: bit operation
    // 除法转换成减法 本题的基本思想就是将divisor不断用左移的方法乘以2来逼近dividend，然后将dividend减去倍乘之后的divisor，再重复这个过程直至被除数小于除数。
    // 记录这个过程中divisor“倍乘”的总次数，即为答案。
    // quotient的总和是n 的话  外层循环应该是logn 里面循环每次最多也是logn 更精确一些里层循环是(logn + log(n/2) + log(n/4) +.... +1) ?
    // Time O((logn)^2)
    // Space O(1)
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean isPositive  = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? true : false;
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        int res = 0;
        while (absDividend >= absDivisor) {
            int c = absDivisor;
            int count = 1;
            while ((c << 1) < absDividend) {
                c = c << 1;
                // count is # of absDivisor's < dividend
                count = count << 1;
            }
            res += count;
            absDividend -= c;
        }

        return isPositive ? res : -res;
    }

    // Assuming any number divided by 0 is Integer.MAX_VALUE.
    // Method 1:
    // Binary Search: find largest smaller n
    //                divisor * n <= dividend
    // Time O(log(dividend))
    // Space O(1)
    public int divideI(int dividend, int divisor) {
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
            if (mid * absDivisor <= absDividend) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (absDivisor * right <= absDividend) {
            return isPositive ? right : -right;
        }
        return isPositive ? left : -left;
    }
}
