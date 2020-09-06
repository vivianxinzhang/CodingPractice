package com.company;
import java.util.*;

public class SmallestRange {
    public static void main(String[] args) {
        SmallestRange s = new SmallestRange();
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(s.smallestRange(matrix)));
    }
    // Time O(kn * k)
    // Space O(k)
    public int[] smallestRange(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[2];
        int[] idx = new int[matrix.length];
        int minRange = Integer.MAX_VALUE;
        while (true) {
            int minIndex = -1, currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE;
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == matrix[i].length) {
                    return result;
                }
                if (matrix[i][idx[i]] < currMin){
                    currMin = matrix[i][idx[i]];
                    minIndex = i;
                }
                if (matrix[i][idx[i]] > currMax) {
                    currMax = matrix[i][idx[i]];
                }
            }
            if ((currMax - currMin) < minRange) {
                minRange = currMax - currMin;
                result[0] = currMin;
                result[1] = currMax;
            }
            idx[minIndex]++;
        }
    }
}