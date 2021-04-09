package com.company;

public class LargestSquareOfOnes {
    public static void main(String[] args) {
        LargestSquareOfOnes s = new LargestSquareOfOnes();
        int[][] matrix = {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 1}};
        System.out.println(s.largest(matrix));  // 2

        matrix = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        System.out.println(s.largest(matrix));  // 4

        matrix = new int[][]{{1}};
        System.out.println(s.largest(matrix));  // 1
    }

    // Assumption:
    // The given matrix is not null and guaranteed to be of size N * N, N >= 0
    // The matrix is a binary matrix (only contains 0 or 1 as the values)
    // M[i][j] represents the edge length of largest all 1 squares with a[i][j] as right bottom corner
    // Base case:
    // first row and first column
    // M[i][j] = matrix[i][j] if i == 0 || j == 0
    // Induction rule:
    // Case 1: matrix[i][j] == 0, M[i][j] = 0
    // Case 2: matrix[i][j] == 1, M[i][j] depends M[i-1][j-1], M[i-1][j] and M[i][j-1]
    //                            M[i][j] bounded by the smallest of the above three + 1
    // Time O(N^2)
    // Space O(N^2)
    public int largest(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int reuslt = 0;
        int[][] M = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0) {
                    M[i][j] = matrix[i][j] == 1 ? 1 : 0;
                } else if (matrix[i][j] == 1) {
                    M[i][j] = Math.min(M[i][j - 1] + 1, M[i - 1][j] + 1);
                    M[i][j] = Math.min(M[i - 1][j - 1] + 1, M[i][j]);
                }
                reuslt = Math.max(reuslt, M[i][j]);
            }
        }
        return reuslt;
    }
}
