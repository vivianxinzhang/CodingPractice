package com.company;

import java.util.Arrays;
import java.util.List;

public class SpiralGenerateII {
    public static void main(String[] args) {
        SpiralGenerateII s = new SpiralGenerateII();
        int[][] result = s.spiralGenerate(10, 4);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] spiralGenerate(int m, int n) {
        int[][] result = new int[m][n];
        if (m <= 0 || n <= 0) {
            return result;
        }
        helper(result, 0, n - 1, 0, m - 1, 1);
        return result;
    }

    private void helper(int[][] matrix, int left, int right, int top, int bottom, int startNumber) {
        // no element left
        if (left > right || top > bottom) {
            return;
        }
        // one row left
        if (top == bottom) {
            for (int col = left; col <= right; col++) {
                matrix[top][col] = startNumber;
                startNumber++;
            }
            return;
        }
        // one col left
        if (left == right) {
            for (int row = top; row <= bottom; row++) {
                matrix[row][left] = startNumber;
                startNumber++;
            }
            return;
        }
        // top row
        for (int i = left; i < right; i++) {
            matrix[top][i] = startNumber++;
        }
        // right col
        for (int i = top; i < bottom; i++) {
            matrix[i][right] = startNumber++;
        }
        // bottom row
        for (int i = right; i > left; i--) {
            matrix[bottom][i] = startNumber++;
        }
        // left col
        for (int i = bottom; i > top; i--) {
            matrix[i][left] = startNumber++;
        }
        left++;
        right--;
        top++;
        bottom--;
        helper(matrix, left, right, top, bottom, startNumber);
    }
}

