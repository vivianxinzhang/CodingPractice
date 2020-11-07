package com.company;

public class LongestCrossOfOnes {
    public static void main(String[] args) {
        LongestCrossOfOnes s = new LongestCrossOfOnes();
        int[][] matrix = {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println();
    }

    // Assumptions: matrix is not null, has size of N * M, where N >= 0 and M >= 0
    // pre-processing:
    // longest consecutive 1s ending at index(i,j) (including)
    // need to know top, down, left, right four directions, minimum of the four bound the arm length of the cross
    // Time O(mn)
    // Space O(mn)
    public int largest(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length;
        if (M == 0) {
            return 0;
        }
        // leftup records the longest possible length for left and up arms ending at each cells in the matrix
        int[][] leftUp = leftUp(matrix, N, M);
        // rightdown records the longest possible length for right and down arms ending at each cells in the matrix
        int[][] rightDown = rightDown(matrix, N, M);
        // merge the two matrix by getting the min value for each cell,
        // and among all the cells get the max value
        return merge(leftUp, rightDown, N, M);
    }

    // merge leftup and rightdown matrix into leftup,
    // the value of each cell is the min value of the corresponding cells
    // in the two matrix, also it returns the max value among all the cells in the merged matrix
    private int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
                result = Math.max(result, leftUp[i][j]);
            }
        }
        return result;
    }

    // calculate the max possible length of left and up arms
    // for each of the cells in the matrix
    private int[][] leftUp(int[][] matrix, int N, int M) {
        // up[i][j] represents length of longest consecutive 1s ending at index i (including i) on top of i
        // left[i][j] represents length of longest consecutive 1s ending at index i (including i) on left of i
        int[][] left = new int[N][M];
        int[][] up = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        up[i][j] = 1;
                        left[i][j] = 1;
                    } else if (i == 0) {
                        up[i][j] = 1;
                        left[i][j] = left[i][j - 1] + 1;
                    } else if (j == 0) {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = 1;
                    } else {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }
        // merge left and up, return the merged matrix
        merge(left, up, N, M);
        return left;
    }

    // calculate the max possible length of right and down arms
    // for each of the cells in the matrix
    private int[][] rightDown(int[][] matrix, int N, int M) {
        // down[i][j] represents length of longest consecutive 1s ending at index i (including i) down of i
        // right[i][j] represents length of longest consecutive 1s ending at index i (including i) on right of i
        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == N - 1 && j == M - 1) {
                        down[i][j] = 1;
                        right[i][j] = 1;
                    } else if (i == N - 1) {
                        down[i][j] = 1;
                        right[i][j] = right[i][j + 1] + 1;
                    } else if (j == M - 1) {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = 1;
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = right[i][j + 1] + 1;
                    }
                }
            }
        }
        // merge right and down, return the merged matrix
        merge(right, down, N, M);
        return right;
    }
}
