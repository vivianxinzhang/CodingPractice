package com.company;

public class SmallestRange {
    // Time O(kn)
    // Space O(k)
    public int[] smallestRange(int[][] arrays) {
        // Write your solution here
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        int[] result = new int[2];
        int[] ptr = new int[arrays.length];
        int minRange = Integer.MAX_VALUE;
        while (true) {
            int minIndex = -1, minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < ptr.length; i++) {
                if (ptr[i] == arrays[i].length) {
                    return result;
                }
                if (arrays[i][ptr[i]] < minVal) {
                    minVal = arrays[i][ptr[i]];
                    minIndex = i;
                }
                if (arrays[i][ptr[i]] > maxVal) {
                    maxVal = arrays[i][ptr[i]];
                }
            }
            if (maxVal - minVal < minRange) {
                result[0] = minVal;
                result[1] = maxVal;
                minRange = maxVal - minVal;
            }
            ptr[minIndex]++;
        }
    }
}