package com.company;

public class PossiblePathsWithObstacles {
    public static void main(String[] args) {
        PossiblePathsWithObstacles s = new PossiblePathsWithObstacles();
        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(s.possiblepath(matrix));     // 2

        matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(s.possiblepath(matrix));     // 0

        matrix = new int[][] {{0, 1, 0, 1, 0, 1},
                              {0, 0, 1, 0, 0, 1},
                              {0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 1, 0, 0}};
        System.out.println(s.possiblepath(matrix));     // 4
    }

    // Time O(mn)
    // Space O(mn) --> can optimize to O(n)
    public int possiblepath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] M = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    if (matrix[i][j] != 1) {
                        M[i][j] = 1;
                    }
                } else if (i == 0) {
                    if (matrix[i][j] != 1) {
                        M[i][j] = M[i][j - 1];
                    }
                } else if (j == 0) {
                    if (matrix[i][j] != 1) {
                        M[i][j] = M[i - 1][j];
                    }
                } else if (matrix[i][j] != 1) {
                        M[i][j] += M[i - 1][j];
                        M[i][j] += M[i][j - 1];
                }
            }
        }
        return M[m - 1][n - 1];
    }
}
