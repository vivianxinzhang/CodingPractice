package com.company;

public class CelebrityProblem {
    public static void main(String[] args) {
        System.out.println("main");
        CelebrityProblem s = new CelebrityProblem();
        int[][] matrix = {{0, 1, 1},
                {0, 0, 0},
                {1, 1, 0}};
        System.out.println(s.celebrity(matrix));

        matrix = new int[][]{{0, 1, 1},
                {0, 0, 0},
                {1, 1, 0}};
        System.out.println(s.celebrity(matrix));
    }

    // matrix[i][j] represents person i knows person j
    // a celebrity does not know any other person, so for a celebrity
    // 1. matrix[c][j] == 0 for all j
    // 2. matrix[i][c] == 1 for all i except i == c
    // Time O(mn + m)
    // Space O(1)
    public int celebrity(int[][] matrix) {
        // Write your solution here
        for (int c = 0; c < matrix.length; c++) {
            if (allRowZero(matrix, c) && checkCol(matrix, c)) {
                return c;
            }
        }
        return -1;
    }

    private boolean allRowZero(int[][] matrix, int c) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (i == c) {
                continue;
            } else if (matrix[c][i] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int[][] matrix, int c) {
        for (int i = 0; i < matrix.length; i++) {
            if (i == c) {
                continue;
            } else if (matrix[i][c] == 0) {
                return false;
            }
        }
        return true;
    }
}
