package com.company;

public class SquareRootI {
    public static void main(String[] args) {
        SquareRootI s = new SquareRootI();

        System.out.println(s.sqrt(0));           // 0
        System.out.println(s.sqrt(4));           // 2
        System.out.println(s.sqrt(18));          // 4
        System.out.println(s.sqrt(462959863));   // 21516
    }

    // Assumption:
    // n is guaranteed to be >= 0.
    // binary search largest smaller x such that: x * x <= n
    // Time O(logn)
    // Space O(1)
    public int sqrtI(int n) {
        long left = 0;
        long right = n;
        while (left < right - 1) {
            long mid = left + (right - left) / 2;
            if (mid * mid > n) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right * right <= n) {
            return (int) right;
        }
        return (int) left;
    }

    // Method 1:
    // Time O(sqrt(x))
    // Space O(1)
    public int sqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int num = 1;
        while (num * num <= x) {
            num++;
        }
        return num - 1;
    }
}
