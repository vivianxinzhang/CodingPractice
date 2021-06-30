package com.company;

public class FibonacciNumber {
    public static void main(String[] args) {
        FibonacciNumber s = new FibonacciNumber();

        System.out.println(s.fibonacci(-1));    // 1
        System.out.println(s.fibonacci(0));     // 0
        System.out.println(s.fibonacci(5));     // 5
        System.out.println(s.fibonacci(80));    // 23416728348467685
    }

    // Corner Cases:
    // What if K < 0? in this case, we should always return 0.
    // Is it possible the result fibonacci number is overflowed?
    // We can assume it will not be overflowed when we solve this problem on this online judge,
    // but we should also know that it is possible to get an overflowed number,
    // and sometimes we will need to use something like BigInteger.
    // Time O(n)
    // Space O(1)
    public long fibonacci(int K) {
        if (K == 0) {
            return 0;
        }
        if (K == 1) {
            return 1;
        }
        long a = 0;
        long b = 1;
        for (int i = 2; i <= K; i++) {
            long tmp = a;
            a = b;
            b += tmp;
        }
        return b;
    }
}
