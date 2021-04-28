package com.company;

public class LongestAscendingPathInMatrix {
    public static void main(String[] args) {
        LongestAscendingPathInMatrix s = new LongestAscendingPathInMatrix();
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 2, 6},
                {7, 1, 9}
        };
        System.out.println(s.longest(matrix));  // 5
    }

    // Method 1: dfs
    // Time O(n^2 * 3^mn)
    // Space O(mn)
    public int longest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] max = new int[] {Integer.MIN_VALUE};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, i, j, 0, m, n, visited, max);
            }
        }
        return max[0];
    }

    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private void dfs(int[][] matrix, int i, int j, int count, int m, int n, boolean[][] visited, int[] max) {
        int pre = matrix[i][j];
        visited[i][j] = true;
        count++;
        max[0] = Math.max(max[0], count);
        for (int[] dir : DIRS) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
            if (valid(matrix, nextX, nextY, visited, pre, m, n)) {
                dfs(matrix, nextX, nextY, count, m, n, visited, max);
            }
        }
        count--;
        visited[i][j] = false;
    }

    private boolean valid(int[][] matrix, int i, int j, boolean[][] visited, int pre, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == true || matrix[i][j] <= pre) {
            return false;
        }
        return true;
    }
}
