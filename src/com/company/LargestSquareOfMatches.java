package com.company;

public class LargestSquareOfMatches {
    public static void main(String[] args) {
        LargestSquareOfMatches s = new LargestSquareOfMatches();
        int[][] matrix = new int[][]{
                        {3, 1, 1, 3, 0, 1, 1, 0},
                        {2, 0, 0, 2, 0, 0, 0, 0},
                        {3, 1, 3, 0, 0, 0, 0, 0},
                        {2, 0, 2, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 0, 0, 0}};
        /*
                        -- --  --  --    -- --
                       |         |
                       |         |
                        -- --  --
                       |      |
                       |      |
                        -- --
         **/
        System.out.println(s.largestSquareOfMatches(matrix));
    }

    // Assumptions:
    // 1. matrix is not null, size of of M * N, where M, N >= 0
    // 2. the elements in the matrix are either 0 or 1
    // Time O(mn*min(m,n)
    // Space O(mn)
    public int largestSquareOfMatches(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        // the longest right/down arm length ending at each position in the matrix
        int[][] right = new int[m + 1][n + 1];
        int[][] down = new int[m + 1][n + 1];
        int globalMax = 0;
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (hasRight(matrix[i][j])) {
                    right[i][j] = right[i][j + 1] + 1;
                }
                if (hasDown(matrix[i][j])) {
                    down[i][j] = down[i + 1][j] + 1;
                }
                if (hasBoth(matrix[i][j])) {
                    // the maximum length of a surrounded by 1 matrix with top-left
                    // position at matrix[i][j] is determined by the min value of
                    // right[i][j] and down[i][j]
                    // and we check one by one all the possible lengths if it can
                    // provide the actual matrix
                    // we only need to check the longest bottom arm length of the bottom-left
                    // cell and the longest right arm length of the top-right cell
                    int maxLength = Math.min(right[i][j], down[i][j]);
                    for (int length = maxLength; length > 0; length--) {
                        // check down for right top corner and right for down bottom corner
                        // [i, j + length]                  [i + length, j]
                        if (down[i][j + length] >= length && right[i + length][j] >= length) {
                            globalMax = Math.max(globalMax, length);
                            break;
                        }
                    }
                }
            }
        }
        return globalMax;
    }

    // 0 - there is no match to its right or bottom.
    // 1 - there is a match to its right.
    // 2 - there is a match to its bottom.
    // 3 - there is a match to its right, and a match
    private boolean hasRight(int value) {
        return value == 1 || value == 3;
    }

    private boolean hasDown(int value) {
        return value == 2 || value == 3;
    }

    private boolean hasBoth(int value) {
        return value == 3;
    }
}
