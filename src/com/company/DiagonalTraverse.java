package com.company;
import java.util.Arrays;

public class DiagonalTraverse {
    public static void main(String[] args) {
        DiagonalTraverse s = new DiagonalTraverse();
        int[][] matrix = new int[][] {
                {1, 3, 5, 7},
                {2, 4, 8, 9},
                {3, 5, 11,15},
                {6, 8, 13,18} };
        System.out.println(Arrays.toString(s.findDiagonalOrder(matrix)));
        // [1, 3, 2, 3, 4, 5, 7, 8, 5, 6, 8, 11, 9, 15, 13, 18]
    }

    // numOfScans = m + n - 1
    // even index: go up right 0, 2, 4 ...
    // int x = s < rows ? s : rows - 1;
    // int y = s < rows ? 0 : s - (rows - 1);
    // odd index: go down left 1, 3, 5 ...
    // int x = s < cols ? 0 : s - (cols - 1);
    // int y = s < cols ? s : cols - 1;
    // Time O(mn)
    // Space O(mn)
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int numOfScans = rows + cols - 1;
        int index = 0;
        for (int i = 0; i < numOfScans; i++) {
            if (i % 2 == 0) {
                int x = i < rows ? i : rows - 1;
                int y = i < rows ? 0 : i - (rows - 1);
                while (x >= 0 && y < cols) {
                    res[index++] = matrix[x--][y++];
                }
            } else {
                int x = i < cols ? 0 : i - (cols - 1);
                int y = i < cols ? i : cols - 1;
                while (x < rows && y >= 0) {
                    res[index++] = matrix[x++][y--];
                }
            }
        }
        return res;
    }
}











