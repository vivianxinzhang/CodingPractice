package com.company;

public class MinimumPathSum {
    public static void main(String[] args) {
        MinimumPathSum s = new MinimumPathSum();
        int[][] grid = {{5, 1, 2, 4}, {4, 1, 0, 1}, {0, 3, 7, 6}};
        System.out.println(s.miniSum(grid));
    }

    // Time O(mn)
    // Space O(mn)
    public int miniSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] M = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    M[i][j] = grid[i][j];
                } else if (i == 0) {
                    M[i][j] = M[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    M[i][j] = M[i - 1][j] + grid[i][j];
                } else {
                    int up = M[i - 1][j];
                    int left = M[i][j - 1];
                    M[i][j] = Math.min(up, left) + grid[i][j];
                }
            }
        }
        return M[grid.length - 1][grid[0].length - 1];
    }
}
