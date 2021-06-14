package com.company;

import java.util.Arrays;

public class SearchInSortedMatrixII {
    public static void main(String[] args) {
        SearchInSortedMatrixII s = new SearchInSortedMatrixII();
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {2, 4, 5},
                {6, 8, 10}
        };
        System.out.println(Arrays.toString(s.search(matrix, 3)));   // [0, 2]

        matrix = new int[][] {{1}, {2}, {3}, {4}};
        System.out.println(Arrays.toString(s.search(matrix, 2)));   // [1, 0]
    }

    // Assumptions:
    // The given matrix is not null.
    // Time O(m+n)
    // Space O(1)
    public int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return new int[] {row, col};
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return new int[] {-1, -1};
    }
}
