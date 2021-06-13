package com.company;
import java.util.*;

public class RotateMatrix {
    public static void main(String[] args) {
        RotateMatrix s = new RotateMatrix();
        int[][] matrix = new int[][] {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        s.rotate(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        /*
        [7, 8, 1]
        [6, 9, 2]
        [5, 4, 3]
        * */
    }

    // Method 3: iteration
    // Split into levels and for each level split it into four partitions
    // Time O(n^2)
    // Space O(1)
    public void rotate(int[][] matrix) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            for (int i = 0; i < end - start; i++) {
                int tmp = matrix[start][start + i];
                matrix[start][start + i] = matrix[end - i][start];
                matrix[end - i][start] = matrix[end][end - i];
                matrix[end][end - i] = matrix[start + i][end];
                matrix[start + i][end] = tmp;
            }
            start++;
            end--;
        }
    }

    // Method 2: Recursion
    // Split into levels and for each level split it into four partitions
    // Assumptions: matrix is not null and has size of N * N, N >= 0
    // Time O(N*N)
    // Space O(n)
    public void rotateII(int[][] matrix) {
        if (matrix == null || matrix.length <= 1 || matrix[0].length <= 1) {
            return;
        }
        helper(matrix, 0, matrix.length - 1);
    }

    private void helper(int[][] matrix, int start, int end) {
        // base case
        if (start >= end) {
            return;
        }
        // recursion rule
        // move (end - start) # of elements, i starts at 0, so should be i < end - start, without =
        for (int i = 0; i < end - start; i++) {
            // counter clockwise
            // top row matrix[start][start + i]
            int tmp = matrix[start][start + i];
            // left to top
            matrix[start][start + i] = matrix[end - i][start];
            // left col matrix[end - i][start]
            // bottom to left
            matrix[end - i][start] = matrix[end][end - i];
            // bottom row matrix[end][end - i]
            // right to bottom
            matrix[end][end - i] = matrix[start + i][end];
            // right col matrix[start + i][end]
            // top to right
            matrix[start + i][end] = tmp;
        }
        // subproblem
        helper(matrix, start + 1, end - 1);
    }

    // Method 1: Rotate a point by 90 degree clockwise ==
    // 1. Mirror the point according to y axis, then
    // 2. Mirror the point according the line of y = x.
    // Time O()
    // Space O()
    public void rotateI(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        mirrorY(matrix, n);
        mirrorYEqualX(matrix, n);
    }

    // Mirror the point by y axis.
    private void mirrorY(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix, i, j, i, n - 1 -j);
            }
        }
    }

    // Mirror the point by the line of y = x.
    private void mirrorYEqualX(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j + i < n - 1; j++) {
                swap(matrix, i, j, n - 1 - j, n - 1 - i);
            }
        }
    }

    private void swap(int[][] matrix, int iRow, int iCol, int jRow, int jCol) {
        int tmp = matrix[iRow][iCol];
        matrix[iRow][iCol] = matrix[jRow][jCol];
        matrix[jRow][jCol] = tmp;
    }
}
