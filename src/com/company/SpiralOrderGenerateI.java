package com.company;

import java.util.Arrays;

public class SpiralOrderGenerateI {
    public static void main(String[] args) {
        SpiralOrderGenerateI s = new SpiralOrderGenerateI();
        int[][] result = s.spiralGenerate(3);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Recursive:
    // Time O(n^2)
    // Space O(n)
    public int[][] spiralGenerate(int n) {
        int[][] result = new int[n][n];
        if (n <= 0) {
            return result;
        }
        helper(result, 0, n - 1, 1);
        return result;
    }

    private void helper(int[][] matrix, int start, int end, int firstNumber) {
        if (start > end) {
            return;
        }
        if (start == end) {
            matrix[start][start] = firstNumber;
            return;
        }
        // top row
        for (int i = start; i < end; i++) {
            matrix[start][i] = firstNumber;
            firstNumber++;
        }
        // right col
        for (int i = start; i < end; i++) {
            matrix[i][end] = firstNumber;
            firstNumber++;
        }
        // bottom row
        for (int i = end; i > start; i--) {
            matrix[end][i] = firstNumber;
            firstNumber++;
        }
        // left col
        for (int i = end; i > start; i--) {
            matrix[i][start] = firstNumber;
            firstNumber++;
        }
        start++;
        end--;
        helper(matrix, start, end, firstNumber);
    }
}
