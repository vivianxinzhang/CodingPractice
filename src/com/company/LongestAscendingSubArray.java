package com.company;

public class LongestAscendingSubArray {
    public static void main(String[] args) {
        LongestAscendingSubArray s = new LongestAscendingSubArray();
        int[] array = new int[] {9, 9};
        System.out.println(s.longest(array));
    }

    // M[i] represents length of the longest subarray ending at index i
    // M[i] = M[i-1] + 1 if array[i] > array[i-1]
    //      = 1          otherwise
    // Time O(n)
    // Space O(1)
    public int longest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                M[i] = M[i - 1] + 1;
                maxLength = Math.max(maxLength, M[i]);
            } else {
                M[i] = 1;
            }
        }
        return maxLength;
    }
}
