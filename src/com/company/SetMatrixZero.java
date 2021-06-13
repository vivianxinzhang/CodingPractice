package com.company;

import java.util.Arrays;

public class SetMatrixZero {
    public static void main(String[] args) {
        SetMatrixZero s = new SetMatrixZero();
        int[][] matrix = {{1, 0}, {0, 1}};
        Printer.printMatrix(matrix);
        System.out.println();
        s.setZero(matrix);
        Printer.printMatrix(matrix);
    }

    // first row (excluding matrix[0][0]) is used to flag each col, mark the first element to 0 if there is 0 in the col
    // first col (including matrix[0][0]) is used to flag each row, mark the first element to 0 if there is 0 in the row
    // Time O(mn)
    // Space O(1)
    public void setZero(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        boolean markFristColZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // check elements of first col
                if (j == 0 && matrix[i][j] == 0) {
                    markFristColZero = true;
                } else if (matrix[i][j] == 0) {   // check other elements
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // first row
        if (matrix[0][0] == 0) {
            setRowZero(matrix, 0);
        }
        // first col
        if (markFristColZero) {
            setColZero(matrix, 0);
        }
    }

    private void setRowZero(int[][] matrix, int i) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][0] = 0;
        }
    }

    private void setColZero(int[][] matrix, int i) {
        for (int col = 0; col < matrix[0].length; col++) {
            matrix[i][col] = 0;
        }
    }
}
