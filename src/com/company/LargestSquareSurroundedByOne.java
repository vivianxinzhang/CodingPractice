package com.company;

public class LargestSquareSurroundedByOne {
    public static void main(String[] args) {
        LargestSquareSurroundedByOne s = new LargestSquareSurroundedByOne();
        int[][] matrix = new int[][] {{1, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        System.out.println(s.largestSquareSurroundedByOne(matrix));  // 1
    }

    // Assumptions:
    // 1. The given matrix is guaranteed to be of size M * N, where M, N >= 0
    // 2. the elements in the matrix are either 0 or 1
    // Time O(mn)
    // Space O(mn)
    // return the length of the largest square
    public int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        int[][] up = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    left[i][j] = getLeft(left, i, j - 1) + 1;
                    up[i][j] = getUp(up, i - 1, j) + 1;
                    // ()                (i-maxLength+1,j)
                    // (i,j-maxLength+1)   (i,j)
                    for (int maxLength = Math.min(left[i][j], up[i][j]); maxLength >= 1; maxLength--) {
                        if (up[i][j - maxLength + 1] >= maxLength && left[i - maxLength + 1][j] >= maxLength) {
                            max = Math.max(max, maxLength);
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }

    private int getUp(int[][] up, int i, int j) {
        return i < 0 ? 0 : up[i][j];
    }

    private int getLeft(int[][] left, int i, int j) {
        return j < 0 ? 0 : left[i][j];
    }
}
