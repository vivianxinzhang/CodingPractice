package com.company;

public class NumberOfDifferentBits {
    public static void main(String[] args) {
        NumberOfDifferentBits s = new NumberOfDifferentBits();

        System.out.println(s.diffBits(2147483647, -2147483648));
        System.out.println(s.diffBits(5, 8));
    }

    // 5(“0101”) and 8(“1000”) has 3 different bits
    // >> is arithmetic shift right, signed right shift, the sign bit is extended to preserve the signedness of the number.
    // >>> is logical shift right, unsigned right shift, fills in from the left with 0s
    // Time O(1)
    // Space O(1)
    public int diffBits(int a, int b) {
        int c = a ^ b;
        return countOnes(c);
    }

    private int countOnes(int c) {
        int count = 0;
        while (c != 0) {
            count += (c & 1);
            c >>>= 1;
        }
        return count;
    }
}
