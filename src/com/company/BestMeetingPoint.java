package com.company;
import java.util.*;

public class BestMeetingPoint {
    public static void main(String[] args) {
        BestMeetingPoint s = new BestMeetingPoint();
        int[][] grid = new int[][]{
                    {1, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0}};
        System.out.println(s.minTotalDistance(grid));   // 6
        // The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
        System.out.println(s.minTotalDistanceI(grid));  // 6
    }

    // The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
    // Method 1: breadth first search
    // Time O(n^4)
    // Space O(mn)
    public int minTotalDistance(int[][] grid) {
        int min = Integer.MAX_VALUE;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return min;
        }
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int currMin = BFS(grid, i, j, m, n);
                min = Math.min(currMin, min);
            }
        }
        return min;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int BFS(int[][] grid, int i, int j, int m, int n) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        int totalDistance = 0;
        int step = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Pair cur = queue.poll();
                if (grid[cur.x][cur.y] == 1) {
                    totalDistance += step;
                }
                for (int[] dir : DIRS) {
                    int nextX = cur.x + dir[0];
                    int nextY = cur.y + dir[1];
                    if (valid(visited, nextX, nextY, m, n)) {
                        queue.offer(new Pair(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
            step++;
        }
        return totalDistance;
    }

    private boolean valid(boolean[][] visited, int nextX, int nextY, int m, int n) {
        return nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY];
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Method 1: brute force
    // Time O(n^4)
    // Space O(1)
    public int minTotalDistanceI(int[][] grid) {
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