package com.company;
import java.util.*;

// Method 4: k pointers
// each round find the largest value and move all indexes pointing to smaller values
// Time O(k * kn)
// Space O(1)
public class Solution {
    public int search(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        int[] idx = new int[matrix.length];
        while (true) {
            int currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE, maxIdx = -1;
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == matrix[i].length) {
                    return -1;
                }
                currMin = matrix[i][idx[i]] < currMin ? matrix[i][idx[i]] : currMin;
                if (matrix[i][idx[i]] > currMax) {
                    currMax = matrix[i][idx[i]];
                    maxIdx = i;
                }
            }
            if (currMin == currMax) {
                return matrix[maxIdx][idx[maxIdx]];
            }
            for (int i = 0; i < idx.length; i++) {
                if (matrix[i][idx[i]] == matrix[maxIdx][idx[maxIdx]]) {
                    continue;
                }
                idx[i]++;
            }
        }
    }
}
