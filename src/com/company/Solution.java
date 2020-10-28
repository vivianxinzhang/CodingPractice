package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.diffBits(5, 8));
    }
    // Time O(1)
    // Space O(1)
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























