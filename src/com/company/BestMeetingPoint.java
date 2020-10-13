package com.company;

public class BestMeetingPoint {
    public static void main(String[] args) {
        BestMeetingPoint s = new BestMeetingPoint();
        int[][] grid = new int[][] {{0}};
        System.out.println(s.minTotalDistance(grid));
    }

    // Time O(n^4)
    // Space O(1)
    public int minTotalDistance(int[][] grid) {
        // Write your solution here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                    int currMin = minTotalDistance(grid, i, j);
                    min = Math.min(currMin, min);
            }
        }
        return min;
    }

    private int minTotalDistance(int[][] grid, int i, int j) {
        int sumDistance = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    int distance = Math.abs(row - i) + Math.abs(col - j);
                    sumDistance += distance;
                }
            }
        }
        return sumDistance;
    }
}