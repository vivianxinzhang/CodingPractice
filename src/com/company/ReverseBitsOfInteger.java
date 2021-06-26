package com.company;

public class ReverseBitsOfInteger {
    public static void main(String[] args) {
        ReverseBitsOfInteger s = new ReverseBitsOfInteger();

        System.out.println(s.reverseBits(15));      // 4026531840
        System.out.println(s.reverseBits(1234));    // 1260388352

        System.out.println(s.reverseBitsI(15));
        System.out.println(s.reverseBitsII(15));
    }

    // Time O(1)
    // Space O(1)
    public long reverseBits(long n) {
        int i = 0;
        int j = 31;
        while (i < j) {
            long bi = (n >> i) & 1L;
            long bj = (n >> j) & 1L;
            if (bi != bj) {
                // 1L << i means 1L left shift i bits, which is * 2^i
                // i << 1L means i left shift 1 bit, which is *2
                n = n ^ ((1L << i) | (1L << j));
            }
            i++;
            j--;
        }
        return n;
    }

    public long reverseBitsI(long num) {
        for (int offset = 0; offset < 16; ++offset) {
            long right = (num >> offset) & 1L;
            long left = (num >> (31 - offset)) & 1L;
            if (left != right) {
                num ^= (1L << offset);
                num ^= (1L << (31 - offset));
            }
        }
        int i = 0;
        int j = 31;
        return num;
    }

    // merge sort way of reversing all the bits
    public long reverseBitsII(long num) {
        num = ((num & 0xFFFF0000 >>> 16) | (num & 0x0000FFFF << 16));
        num = ((num & 0xFF00FF00 >>> 8) | (num & 0x00FF00FF << 8));
        num = ((num & 0xF0F0F0F0 >>> 4) | (num & 0x0F0F0F0F << 4));
        num = ((num & 0xCCCCCCCC >>> 2) | (num & 0x33333333 << 2));
        num = ((num & 0xAAAAAAAA >>> 1) | (num & 0x55555555 << 1));
        return num;
    }
}