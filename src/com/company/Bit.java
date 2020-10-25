package com.company;

public class Bit {
    // print binary representation of a int value
    public static void printBinary(int value) {
        System.out.println(value + " : ");
        StringBuilder builder = new StringBuilder();
        for (int shift = 31; shift >= 0; shift--) {
            builder.append((value >>> shift) & 1);
        }
        System.out.println(builder.toString());
        System.out.println();
    }

    public static void main(String[] args) {
        // 0
        int a = 0;
        printBinary(a);

        // positive number
        a = 5;
        printBinary(a);

        // negative number
        a = -5;
        printBinary(a);

        a = Integer.MIN_VALUE;
        printBinary(a);
        // 10000000000000000000000000000000

        a = Integer.MAX_VALUE;
        printBinary(a);
        // 01111111111111111111111111111111

        // Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
        // Integer.MIN_VALUE - 1 = Integer.MAX_VALUE

        // all "1"
        a = -1;
        printBinary(a);
        // 11111111111111111111111111111111

        // signed shift - leftmost bit depends on previous leftmost bit
        int b = a >> 5;
        printBinary(b);
        // 11111111111111111111111111111111

        // unsigned shift - left most bit "0"
        b = a >>> 5;
        printBinary(b);
        // 00000111111111111111111111111111
    }
}
