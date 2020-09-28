package com.company;

public class SearchInSortedMatrixI {
    public int[] search(int[][] matrix, int target) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int rows = matrix.length;
        int cols =  matrix[0].length;
        int left = 0;
        int right = rows * cols -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
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
