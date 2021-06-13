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
    }

    // Assumptions:
    // The 2D array is not null and has size of N * N where N >= 0
    // Method 2: Iteration
    // Time O(n^2) = O(4n + 4(n - 2) + 4(n - 4) + ... + 0) = O(n/2 * (4n + 0)/2)
    // Space O(1)
    public List<Integer> spiral(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            for (int i = start; i < end; i++) {
                res.add(matrix[start][i]);
            }
            for (int i = start; i < end; i++) {
                res.add(matrix[i][end]);
            }
            for (int i = end; i > start; i--) {
                res.add(matrix[end][i]);
            }
            for (int i = end; i > start; i--) {
                res.add(matrix[i][start]);
            }
            start++;
            end--;
        }
        if (start == end) {
            res.add(matrix[start][start]);
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
