package com.company;
import java.util.*;

public class SpiralTraverseII {
    public static void main(String[] args) {
        SpiralTraverseII s = new SpiralTraverseII();
        int[][] matrix = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(s.spiral(matrix));
    }

    // Time O(mn)
    // Space O(min(m, n))  Recursion 层数 n/2 or n/2 + 1 or m/2 or m/2 -1   每层 size -2 所以一共 n/2 层
    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        helper(matrix, 0, matrix[0].length - 1, 0, matrix.length - 1, result);
        return result;
    }

    private void helper(int[][] matrix, int left, int right, int top, int bottom, List<Integer> result) {
        // no element left
        if (left > right || top > bottom) {
            return;
        }
        // one row left
        if (top == bottom) {
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
                return;
            }
        }
        // one col left
        if (left == right) {
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][left]);
                return;
            }
        }
        // top row
        for (int i = left; i < right; i++) {
            result.add(matrix[top][i]);
        }
        // right col
        for (int i = top; i < bottom; i++) {
            result.add(matrix[i][right]);
        }
        // bottom row
        for (int i = right; i > left; i--) {
            result.add(matrix[bottom][i]);
        }
        // left col
        for (int i = bottom; i > top; i--) {
            result.add(matrix[i][left]);
        }
        left++;
        right--;
        top++;
        bottom--;
        helper(matrix, left, right, top, bottom, result);
    }
}
