package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println();
    }



    public boolean allUnique(String word) {
        // Write your solution here
        int[] bitMap = new int[8];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int row = c / 32;
            int col = c % 32;
            if (((bitMap[row] >> col) & 1) == 1) {
                return false;
            }
            bitMap[row] |= (1 << col);
        }
        return true;
    }

    public int diffBits(int a, int b) {
        // Write your solution here
        int c = a ^ b;
        return countOnes(c);
    }

    private int countOnes(int number) {
        int count = 0;
        while (number != 0) {
            count += number & 1;
            number >>= 1;
        }
        return count;
    }
}























