package com.company;

public class PaintHouse {
    public static void main(String[] args) {
        PaintHouse s = new PaintHouse();

        int[][] costs = new int[][] {};
        System.out.println(s.minCost(costs));     // 0
        costs = new int[][] {{7, 6, 2}};
        System.out.println(s.minCost(costs));     // 15
        costs = new int[][] {{8,9,9}, {13,11,7}, {9,5,10}, {9,12,19}};
        System.out.println(s.minCost(costs));     // 104
    }

    // Note:
    // All costs are positive integers.
    // Time O(n)
    // Space O(n)
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[] red = new int[n];
        int[] green = new int[n];
        int[] blue = new int[n];
        red[0] = costs[0][0];
        green[0] = costs[0][1];
        blue[0] = costs[0][2];
        for (int i = 1; i < n; i++) {
            red[i] = costs[i][0] + Math.min(green[i - 1], blue[i - 1]);
            green[i] = costs[i][1] + Math.min(red[i - 1], blue[i - 1]);
            blue[i] = costs[i][2] + Math.min(red[i - 1], green[i - 1]);
        }
        return red[n - 1] + green[n - 1] + blue[n - 1];
    }
}
