package com.company;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseI {
    public static void main(String[] args) {
        SpiralOrderTraverseI s = new SpiralOrderTraverseI();

        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(s.spiral(matrix));
        // [1, 2, 4, 3]

        matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(s.spiral(matrix));
        // [1, 2, 3, 6, 9, 8, 7, 4, 5]

        matrix = new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        System.out.println(s.spiral(matrix));
        // [1, 2, 3, 4, 5, 10, 9, 8, 7, 6]
    }

    // Assumptions:
    // The 2D array is not null and has size of N * N where N >= 0
    // Method 2: Iteration
    // Time O(n^2) = O(4n + 4(n - 2) + 4(n - 4) + ... + 0) = O(n/2 * (4n + 0)/2)
    // Space O(1)
    public List<Integer> spiral(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top < bottom && left < right) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                res.add(matrix[bottom][i]);
            }
            for (int i = bottom; i > top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        if (top == bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
        } else if (left == right) {
            for (int i = top; i < bottom; i++) {
                res.add(matrix[right][i]);
            }
        }
        return res;
    }

    // Method 1: Recursion
    // Time O(n^2) = O(4n + 4(n - 2) + 4(n - 4) + ... + 0) = O(n/2 * (4n + 0)/2)
    // Space O(n)
    public List<Integer> spiralI(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        helper(matrix, 0, matrix.length - 1, result);
        return result;
    }

    private void helper(int[][] matrix, int start, int end, List<Integer> result) {
        if (start > end) {
            return;
        }
        if (start == end) {
            result.add(matrix[start][start]);
            return;
        }
        // top row
        // row: start
        // col: [start ... end-1]
        for (int i = start; i < end; i++) {
            result.add(matrix[start][i]);
        }
        // right col
        // row: [start ... end-1]
        // col: end
        for (int i = start; i < end; i++) {
            result.add(matrix[i][end]);
        }
        // bottom row
        // row: end
        // col: [end ... start+1]
        for (int i = end; i > start; i--) {
            result.add(matrix[end][i]);
        }
        // left col
        // row: [end ... start + 1]
        // col: start
        for (int i = end; i > start; i--) {
            result.add(matrix[i][start]);
        }
        start++;
        end--;
        helper(matrix, start, end, result);
    }
}
