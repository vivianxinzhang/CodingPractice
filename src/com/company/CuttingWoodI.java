package com.company;

public class CuttingWoodI {
    public static void main(String[] args) {
        CuttingWoodI s = new CuttingWoodI();

        int[] cuts = new int[] {2, 4, 7};
        System.out.println(s.minCost(cuts, 10));    // 20

        cuts = new int[] {2, 4, 5, 8};
        System.out.println(s.minCost(cuts, 10));    // 23

        cuts = new int[] {3, 16, 19, 28, 37, 44, 47, 48, 51, 52, 62};
        System.out.println(s.minCost(cuts, 67));    // 229
    }

    // Base case: (size = 1) the shortest wood piece that cannot be cut any further.
    // Induction rule (summary):
    // M[i][j] represents the minimum cost of cutting the wood between index i and inde j in the input array A.
    // So, the final solution to return is the value of M[0][4].
    // 从中心开花，[index = 0, 1, 2, 3, ... n-1], for each M[i, j], we usually need to try out all possible k that (i < k < j),
    // M[i, j] = min (A[j] + A[i] + M[i][k] + M[k][j]) for all possible k.
    // In which order should we compute M[i][j]?
    // Observation: In order to fill in a value at M[i][j] we must know the value of its left, and its value below it,
    // so we fill in the table from bottom up and from left to right.
    // Assumption:
    // cuts is not null, length >= 0, all cuts are valid numbers
    // Time= O(n^2 * n) = O(n^3)  n is cuts.length
    // Space = O(n^2)
    public int minCost(int[] cuts, int length) {
        // First we need to pad the original array at leftmost and rightmost position
        int[] helper = new int[cuts.length + 2];
        // [0, 2, 4, 7, 10]
        helper[0] = 0;
        for (int i = 0; i < cuts.length; i++) {
            helper[i + 1] = cuts[i];
        }
        helper[helper.length - 1] = length;
        // minCost[j][i]: the min cost of cutting the partition (j, i).
        int[][] minCost = new int[helper.length][helper.length];
        // 枚举所有的终点
        for (int i = 1; i < helper.length; i++) {   // left -> right
            // 枚举所有的起点
            for (int j = i - 1; j >= 0; j--) {      // bottom -> up
                // base case
                if (j + 1 == i) {
                    minCost[j][i] = 0;
                } else {
                    minCost[j][i] = Integer.MAX_VALUE;
                    // 枚举所有的 rightmost cut
                    for (int k = j + 1; k <= i - 1; k++) {
                        minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
                    }
                    minCost[j][i] += helper[i] - helper[j];
                }
            }
        }
        return minCost[0][helper.length - 1];
    }
}
