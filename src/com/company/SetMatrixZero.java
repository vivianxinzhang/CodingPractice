package com.company;

import java.util.Arrays;

public class SetMatrixZero {
    public static void main(String[] args) {
        SetMatrixZero s = new SetMatrixZero();
        int[][] matrix = {{1, 0}, {0, 1}};
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        s.setZero(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void setZero(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        boolean flagFristRowZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                flagFristRowZero = true;
            }
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
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
        if (matrix[0][0] == 0) {
            setColZero(matrix, 0);
        }
        if (flagFristRowZero) {
            setRowZero(matrix, 0);
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
