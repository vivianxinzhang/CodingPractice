package com.company;

import java.util.*;

public class CountPathsGoingOutOfMatrix {
    public static void main(String[] args) {
        CountPathsGoingOutOfMatrix s = new CountPathsGoingOutOfMatrix();

        System.out.println(s.countPaths(2, 2, 1, 0, 0));    // 2
        System.out.println(s.countPaths(2, 2, 2, 0, 0));    // 6
        System.out.println(s.countPaths(6, 2, 5, 1, 0));    // 116

        System.out.println(s.countPathsI(2, 2, 1, 0, 0));    // 2
        System.out.println(s.countPathsI(2, 2, 2, 0, 0));    // 6
        System.out.println(s.countPathsI(6, 2, 5, 1, 0));    // 116

        System.out.println(s.countPathsII(2, 2, 1, 0, 0));    // 2
        System.out.println(s.countPathsII(2, 2, 2, 0, 0));    // 6
        System.out.println(s.countPathsII(6, 2, 5, 1, 0));    // 116
    }

    // Assumptions:
    // 1. 1 <= m, n <= 50
    // 2. 0 <= i < m, 0 <= j < n
    // 3. 0 <= num <= 50
    // Method 4: dp
    // Observation:
    // Number of paths start from (i, j) to out of boundary <==>
    // Number of paths from out of boundary to (i, j)
    // dp[N][i][j] := number of paths start from out of boundary to (i,j) by moving N steps
    // dp[*][y][x] = 1, if (x, y) are out of boundary
    // dp[s][i][j] = dp[s-1][i+1][j]
    //             + dp[s-1][i-1][j]
    //             + dp[s-1][i][j-1]
    //             + dp[s-1][i][j+1]
    // return dp[N][i][j]
    // Time O(mn*num)
    // Space O(mn*num) -> optimize O(mn)
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int countPaths(int m, int n, int num, int i, int j) {
        int mod = 1000000007;
        int[][][] M = new int[num + 1][m][n];
        for (int s = 1; s <= num; s++) {
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    for (int[] dir : DIRS) {
                        int preRow = row + dir[0];
                        int preCol = col + dir[1];
                        if (preRow < 0 || preRow >= m || preCol < 0 || preCol >= n) {
                            M[s][row][col] += 1;
                        } else {
                            M[s][row][col] = (M[s][row][col] + M[s - 1][preRow][preCol]) % mod;
                        }
                    }
                }
            }
        }
        return M[num][i][j];
    }

    // Method 3: dfs
    // Time O(4*num)
    // Space O(num)
    public int countPathsIII(int m, int n, int num, int i, int j) {
        int[] res = new int[]{0};
        dfs(m, n, num, i, j, res);
        return res[0];
    }

    private void dfs(int m, int n, int num, int i, int j, int[] res) {
        if (outOfBound(i, j, m, n)) {
            res[0]++;
            return;
        }
        if (num == 0) {
            return;
        }
        for (int[] dir : DIRS) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
            dfs(m, n, num - 1, nextX, nextY, res);
        }
    }

    private boolean outOfBound(int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return true;
        }
        return false;
    }

    // Method 2: recursion with memo
    // Time  O(4^n)
    // Space O(n)
    int M = 1000000007;
    public int countPathsII(int m, int n, int num, int i, int j) {
        int[][][] memo = new int[m][n][num + 1];
        for (int[][] board : memo) {
            for (int[] row : board) {
                Arrays.fill(row, -1);
            }
        }
        return findPaths(m, n, num, i, j, memo);
    }

    private int findPaths(int m, int n, int num, int i, int j, int[][][] memo) {
        if (i == m || j == n || i < 0 || j < 0) {
            return 1;
        }
        if (num == 0) {
            return 0;
        }
        if (memo[i][j][num] >= 0) {
            return memo[i][j][num];
        }
        memo[i][j][num] = ((findPaths(m, n, num - 1, i - 1, j, memo)
                        + findPaths(m, n, num - 1, i + 1, j, memo)) % M
                        + (findPaths(m, n, num - 1, i, j - 1, memo)
                        + findPaths(m, n, num - 1, i, j + 1, memo)) % M) % M;
        return memo[i][j][num];
    }

    // Method 1: recursion
    // Time  O(4^n)
    // Space O(n)
    public int countPathsI(int m, int n, int num, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0) {
            return 1;
        }
        if (num == 0) {
            return 0;
        }
        return countPathsI(m, n, num - 1, i - 1, j)
                + countPathsI(m, n, num - 1, i + 1, j)
                + countPathsI(m, n, num - 1, i, j - 1)
                + countPathsI(m, n, num - 1, i, j + 1);
    }
}
