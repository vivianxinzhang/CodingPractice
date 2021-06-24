package com.company;

public class LongestConsecutiveOnes {
    public static void main(String[] args) {
        LongestConsecutiveOnes s = new LongestConsecutiveOnes();
        int[] array = {0, 1, 0, 1, 1, 1, 0};
        System.out.println(s.longest(array));    // 3
    }

    // Time O(n)
    // Space O(n)
    public int longest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = array[0];
        int longest = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                M[i] = 0;
            } else {
                M[i] = M[i - 1] + 1;
                longest = Math.max(longest, M[i]);
            }
        }
        return longest;
    }
}
