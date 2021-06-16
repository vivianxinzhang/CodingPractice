package com.company;

import java.util.Arrays;

public class SearchInSortedMatrixI {
    public static void main(String[] args) {
        SearchInSortedMatrixI s = new SearchInSortedMatrixI();
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.toString(s.search(matrix, 3)));
        // [0, 2]
    }

    // Assumptions:
    // The given matrix is not null.
    // convert the 2D array to 1D array and do binary search
    // Time O(log(mn))
    // Space O(1)
    public int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int rows = matrix.length;
        int cols =  matrix[0].length;
        int left = 0;
        // convert the 2D array to 1D array with rows * cols elements
        int right = rows * cols -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // convert the position in 1D array back to row and col in 2D array
            int row = mid / cols;
            int col = mid % cols;
            if (matrix[row][col] == target) {
                return new int[] {row, col};
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[] {-1, -1};
    }
}
