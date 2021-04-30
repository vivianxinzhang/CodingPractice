package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class NumberOfIslands {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        System.out.println("main");
        NumberOfIslands s = new NumberOfIslands();

        char[][] matrix = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        System.out.println(s.numIslands(matrix));   // 1
        System.out.println(s.numIslandsI(matrix));  // 1
    }

    // Method 2 DFS:
    // For every land, visit its neighbor land using DFS until there is
    // not connected land, mark every visited node as 0.
    // Time: O(mn)
    // Space: O(m+n)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, m, n);  // 一次dfs，找到一座小岛
                }
            }
        return count;
    }

    // 每次dfs会把一块陆地降下去, 每次下沉一片陆地
    private void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';     // visited  '1'陆地 -> '0'水
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i, j - 1, m, n);
    }

    // Method 1: BFS
    // Time: O(mn)
    // Space: O(m+n)
    public int numIslandsI(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    count++;
                    bfs(matrix, i, j, m, n);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] matrix, int i, int j, int m, int n) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            Pair tmp = queue.poll();
            matrix[tmp.row][tmp.col] = 0;
            for (int[] dir : DIRS) {
                int nextX = tmp.row + dir[0];
                int nextY = tmp.col + dir[1];
                if (isNeighborIsland(nextX, nextY, matrix, m, n)) {
                    queue.offer(new Pair(nextX, nextY));
                }
            }
        }
    }

    private boolean isNeighborIsland(int i, int j, char[][] matrix, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        return matrix[i][j] == '1';
    }

    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
