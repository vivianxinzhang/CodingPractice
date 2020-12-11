package com.company;

public class MergeStones {
    public static void main(String[] args) {
        MergeStones s = new MergeStones();
        int[] array = new int[] {4, 3};
        System.out.println(s.minCost(array));   // 7

        array = new int[] {4, 3, 3, 4};
        System.out.println(s.minCost(array));   // 28
    }

    // minCost[j][i]: the min cost of merging the partition [j, i]
    // M[i, j] = min (sun(j ... i) + M[i][k] + M[k][j]) for all possible k.
    // Time= O(n^2 * n) = O(n^3)
    // Space = O(n^2)
    public int minCost(int[] stones) {
        // [4, 3, 3, 4];
        int[][] minCost = new int[stones.length][stones.length];
        // 枚举所有的终点
        for (int i = 1; i < stones.length; i++) {
            // 对固定的终点i, 枚举所有的起点
            for (int j = i; j >= 0; j--) {
                // base case
                if (j == i) {
                    minCost[j][i] = 0;
                } else {
                    minCost[j][i] = Integer.MAX_VALUE;
                    // 枚举所有的归并的分界点
                    // [j, k-1], [k, i]
                    for (int k = j + 1; k <= i; k++) {
                        minCost[j][i] = Math.min(minCost[j][i], minCost[j][k - 1] + minCost[k][i]);
                    }
                    minCost[j][i] += sum(stones, j, i);
                }
            }
        }
        return minCost[0][stones.length - 1];
    }

    private int sum(int[] stones, int j, int i) {
        int sum = 0;
        for (int k = j; k <= i; k++) {
            sum += stones[k];
        }
        return sum;
    }
}
