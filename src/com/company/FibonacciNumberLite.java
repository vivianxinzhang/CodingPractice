package com.company;

public class FibonacciNumberLite {
    public static void main(String[] args) {
        FibonacciNumberLite s = new FibonacciNumberLite();

        System.out.println(s.fibonacci(-1));     // 0
        System.out.println(s.fibonacci(0));     // 0
        System.out.println(s.fibonacci(3));     // 2
        System.out.println(s.fibonacci(6));     // 8
    }

    // Corner Cases:
    // 1. What if K < 0? in this case, we should always return 0.
    // 2. Is it possible the result fibonacci number is overflowed?
    //    We can assume it will not be overflowed when we solve this problem on this online judge, but we should also know that it is possible to get an overflowed number, and sometimes we will need to use something like BigInteger.
    // Time O(n)
    // Space O(1)
    public int fibonacci(int k) {
        if (k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= k; i++) {
            int tmp = a;
            a = b;
            b = tmp + b;
        }
        return b;
    }
}
