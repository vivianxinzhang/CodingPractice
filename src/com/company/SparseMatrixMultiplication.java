package com.company;

import java.util.Arrays;

public class SparseMatrixMultiplication {
    public static void main(String[] args) {
        SparseMatrixMultiplication s = new SparseMatrixMultiplication();
        int[][] m1 = new int[][] {{1, 1}};
        int[][] m2 = new int[][] {{1, 2, 3}, {4, 5, 6}};
        int[][] res = s.multiply(m1, m2);
        Printer.printMatrix(res);
        // [5, 7, 9]

        m1 = new int[][] {{1, 0, 0}, {-1, 0, 3}};
        m2 = new int[][] {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        res = s.multiply(m1, m2);
        Printer.printMatrix(res);
        // [7, 0, 0]
        // [-7, 0, 3]
    }

    // Assumption:
    // You may assume that A's column number is equal to B's row number.
    // Time O(mkn)   A - m * n, B- n * k
    // Space O(1)
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int r = 0; r < A[0].length; r++) {
                    res[i][j] += A[i][r] * B[r][j];
                }
            }
        }
        return res;
    }
}
