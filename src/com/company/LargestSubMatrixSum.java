package com.company;

public class LargestSubMatrixSum {
    public static void main(String[] args) {
        LargestSubMatrixSum s = new LargestSubMatrixSum();
        int[][] matrix = new int[][]{
                {1, -2, -1, 4},
                {1, -1,  1, 1},
                {0, -1, -1, 1},
                {0,  0,  1, 1} };
        System.out.println(s.largest(matrix));  // 7

        matrix = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 1, 1}};
        System.out.println(s.largest(matrix));  // 16
    }

    // Assumptions:
    // The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
    // Step 1: get prefix sum for each col
    // Step 2: enumerate all top row and bottom rows
    //         can use O(1) get sum for each col between top row and bottom row
    //         run largest subarray sum for in between values
    // Time O(m^2 * n)
    // Space O(n^2)
    public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] colPrefixSum = getPrefixSum(matrix);
        int max = Integer.MIN_VALUE;
        for (int top = 0; top < matrix.length; top++) {
            for (int bottom = top; bottom < matrix.length; bottom++) {
                int currMax = largestSubarraySum(colPrefixSum,top, bottom);
                max = Math.max(currMax, max);
            }
        }
        return max;
    }

    private int[][] getPrefixSum(int[][] matrix) {
        int[][] M = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    M[i][j] = matrix[i][j];
                } else {
                    M[i][j] = matrix[i][j] + M[i - 1][j];
                }
            }
        }
        return M;
    }

    private int largestSubarraySum(int[][] matrix, int top, int bottom) {
        int[] M = new int[matrix[0].length];
        M[0] = getValue(matrix, 0, top, bottom);
        int max = M[0];
        for (int i = 1; i < matrix[0].length; i++) {
            int currValue = getValue(matrix, i, top, bottom);
            if (M[i - 1] > 0) {
                M[i] = M[i - 1] + currValue;
            } else {
                M[i] = currValue;
            }
            max = Math.max(max, M[i]);
        }
        return max;
    }

    private int getValue(int[][] matrix, int i, int top, int bottom) {
        if (top == 0) {
            return matrix[bottom][i];
        } else {
            return matrix[bottom][i] - matrix[top - 1][i];
        }
    }
}
