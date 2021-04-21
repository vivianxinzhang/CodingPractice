package com.company;

public class MaximalSquare {
    public static void main(String[] args) {
        MaximalSquare s = new MaximalSquare();
        System.out.println();
        int[][] matrix = new int[][]{{1}};
        System.out.println(s.maximalSquare(matrix));  // 1

        matrix = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 1}};
        System.out.println(s.maximalSquare(matrix));  // 2

        matrix = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        System.out.println(s.maximalSquare(matrix));  // 4
    }

    // Time O(mn)
    // Space O(mn)
    public int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLength = 0;
        int[][] M = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    M[i][j] = Math.min(getNumber(M, i - 1, j - 1), Math.min(getNumber(M, i, j - 1), getNumber(M, i - 1, j))) + 1;
                }
                maxLength = Math.max(maxLength, M[i][j]);
            }
        }
        return maxLength * maxLength;
    }

    private int getNumber(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return matrix[i][j];
    }
}
