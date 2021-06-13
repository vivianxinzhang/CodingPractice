package com.company;

import java.util.Arrays;

public class SpiralOrderGenerateII {
    public static void main(String[] args) {
        SpiralOrderGenerateII s = new SpiralOrderGenerateII();
        int[][] result = s.spiralGenerate(3, 4);
        Printer.printMatrix(result);
        // [1,  2,   3, 4]
        // [10, 11, 12, 5]
        // [9,  8,   7, 6]
    }

    // Assumptions: M >= 0, N >= 0
    // Method 2: Iteration
    // Time O(mn)
    // Space O(1)
    public int[][] spiralGenerate(int m, int n) {
        int[][] matrix = new int[m][n];
        int num = 1;
        int startRow = 0;
        int endRow = m - 1;
        int startCol = 0;
        int endCol = n - 1;
        while (startRow < endRow && startCol < endCol) {
            // top
            for (int i = startCol; i < endCol; i++) {
                matrix[startRow][i] = num++;
            }
            // right
            for (int i = startRow; i < endRow; i++) {
                matrix[i][endCol] = num++;
            }
            // bottom
            for (int i = endCol; i > startCol; i--) {
                matrix[endRow][i] = num++;
            }
            // left
            for (int i = endRow; i > startRow; i--) {
                matrix[i][startCol] = num++;
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        if (startRow == endRow) {
            for (int i = startCol; i <= endCol; i++) {
                matrix[startRow][i] = num++;
            }
        } else if (startCol == endCol) {
            for (int i = startRow; i <= endRow; i++) {
                matrix[i][endCol] = num++;
            }
        }
        return matrix;
    }

    // Method 1: Recursion
    // Time O(mn)
    // Space O(min(m,n))
    public int[][] spiralGenerateI(int m, int n) {
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
