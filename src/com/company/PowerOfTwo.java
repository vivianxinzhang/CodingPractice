package com.company;

public class PowerOfTwo {
    public static void main(String[] args) {
        PowerOfTwo s = new PowerOfTwo();

        System.out.println(s.isPowerOfTwo(16));     // true
        System.out.println(s.isPowerOfTwo(3));      // false
        System.out.println(s.isPowerOfTwo(0));      // false
        System.out.println(s.isPowerOfTwo(-1));     // false
    }

    // Time O(1)
    // Space O(1)
    public boolean isPowerOfTwo(int number) {
        return number > 0 && countOnes(number) == 1;
    }

    private int countOnes(int number) {
        int count = 0;
        while (number > 0) {
            count += (number & 1);
            number >>= 1;
        }
        return count;
    }
}
