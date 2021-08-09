package com.company;

public class MergeStones {
    public static void main(String[] args) {
        MergeStones s = new MergeStones();

        int[] stones = new int[] {1};
        System.out.println(s.minCost(stones));      // 0

        stones = new int[] {3, 1, 7};
        System.out.println(s.minCost(stones));      // 15

        stones = new int[] {4, 1, 3, 2};
        System.out.println(s.minCost(stones));      // 20
    }

    // Assumptions:
    // stones is not null and is length of at least 1
    // minCost[i][j]: the min cost of merging the partition [i, j]
    // M[i, j] = min (sum(i ... j) + M[i][k-1] + M[k][j]) for all possible k.
    // i+1 < k <= j                    left      bottom
    // when fill M[i][j] need to know its left and bottom
    // fill M from left to right, bottom to top
    // Time= O(n^2 * n) = O(n^3)
    // Space = O(n^2)
    public int minCost(int[] stones) {
        int n = stones.length;
        int[][] M = new int[n][n];
        for (int right = 0; right < n; right++) {
            for (int left = right; left >= 0; left--) {
                if (left == right) {
                    M[left][right] = 0;
                } else {
                    M[left][right] = Integer.MAX_VALUE;
                    for (int i = left + 1; i <= right; i++) {
                        M[left][right] = Math.min(M[left][right],M[left][i - 1] + rangeSum(stones, left, right) + M[i][right]);
                    }
                }
            }
        }
        return M[0][n - 1];
    }

    private int rangeSum(int[] stones, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += stones[i];
        }
        return sum;
    }
}
