package com.company;
import java.util.*;

public class BestMeetingPoint {
    public static void main(String[] args) {
        BestMeetingPoint s = new BestMeetingPoint();
        int[][] grid = new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println(s.minTotalDistance(grid));
        System.out.println(s.minTotalDistanceI(grid));
    }

    // Method 1: breadth first search
    // Time O(n^4)
    // Space O(mn)
    public int minTotalDistance(int[][] grid) {
        // Write your solution here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int currMin = BFS(grid, i, j);
                min = Math.min(currMin, min);
            }
        }
        return min;
    }

    private int BFS(int[][] grid, int i, int j) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        int distance = 0;
        int step = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Pair curr = queue.poll();
                if (grid[curr.row][curr.col] == 1) {
                    distance += step;
                }
                if (curr.row - 1 >= 0 && !visited[curr.row - 1][curr.col]) {
                    queue.offer(new Pair(curr.row - 1, curr.col));
                    visited[curr.row - 1][curr.col] = true;
                }
                if (curr.row + 1 < grid.length && !visited[curr.row + 1][curr.col]) {
                    queue.offer(new Pair(curr.row + 1, curr.col));
                    visited[curr.row + 1][curr.col] = true;
                }
                if (curr.col - 1 >= 0 && !visited[curr.row][curr.col - 1]) {
                    queue.offer(new Pair(curr.row, curr.col - 1));
                    visited[curr.row][curr.col - 1] = true;
                }
                if (curr.col + 1 < grid[0].length && !visited[curr.row][curr.col + 1]) {
                    queue.offer(new Pair(curr.row, curr.col + 1));
                    visited[curr.row][curr.col + 1] = true;
                }
            }
            step++;
        }
        return distance;
    }

    class Pair {
        int col;
        int row;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Method 1: brute force
    // Time O(n^4)
    // Space O(1)
    public int minTotalDistanceI(int[][] grid) {
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