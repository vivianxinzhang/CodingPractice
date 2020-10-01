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
    }

    // split into levels and for each level split it into four partitions
    // Assumptions: matrix is not null and has size of N * N, N >= 0
    // Time O(N*N)
    // Space O(1)
    public void rotate(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length <= 1 || matrix[0].length <= 1) {
            return;
        }
        helper(matrix, 0, matrix.length - 1);
    }

    private void helper(int[][] matrix, int start, int end) {
        if (start >= end) {
            return;
        }
        for (int i = 0; i < end - start; i++) {
            // top row
            int tmp = matrix[start][start + i];
            // fill top row
            matrix[start][start + i] = matrix[end - i][start];
            // fill left col
            matrix[end - i][start] = matrix[end][end - i];
            // fill bottom row
            matrix[end][end - i] = matrix[start + i][end];
            // fill right col
            matrix[start + i][end] = tmp;
        }
        start++;
        end--;
        helper(matrix, start, end);
    }
}
