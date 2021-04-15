package com.company;

public class LargestXOfOnes {
    public static void main(String[] args) {
        LargestXOfOnes s = new LargestXOfOnes();
        int[][] matrix = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 1, 1}};
        System.out.println(s.largest(matrix));  // 1
    }

    // Assumptions: matrix is not null, and has size of N * M, N >= 0 and M >= 0
    // Time O(mn)
    // Space O(mn)
    public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] upLeftAndRight = getUpLeftAndRight(matrix, rows, cols);
        int[][] downLeftAndRight = getDownLeftAndRight(matrix, rows, cols);
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int curr = Math.min(upLeftAndRight[i][j], downLeftAndRight[i][j]);
                max = Math.max(max, curr);
            }
        }
        return max;
    }

    private int[][] getDownLeftAndRight(int[][] matrix, int rows, int cols) {
        int[][] downLeft = new int[rows][cols];
        int[][] downRight = new int[rows][cols];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    downLeft[i][j] = getValue(downLeft, i + 1, j - 1) + 1;
                    downRight[i][j] = getValue(downRight, i + 1, j + 1) + 1;
                }
            }
        }
        return merge(downLeft, downRight, rows, cols);
    }

    private int[][] getUpLeftAndRight(int[][] matrix, int rows, int cols) {
        int[][] leftUp = new int[rows][cols];
        int[][] rightUp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    leftUp[i][j] = getValue(leftUp, i - 1, j - 1) + 1;
                    rightUp[i][j] = getValue(rightUp, i - 1, j + 1) + 1;
                }
            }
        }
        return merge(leftUp, rightUp, rows, cols);
    }

    private int[][] merge(int[][] leftUp, int[][] rightUp, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                leftUp[i][j] = Math.min(leftUp[i][j], rightUp[i][j]);
            }
        }
        return leftUp;
    }

    private int getValue(int[][] matrix, int row, int col) {
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length) {
            return matrix[row][col];
        }
        return 0;
    }
}
