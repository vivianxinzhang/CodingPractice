package DailyChallenge;

import java.util.*;

public class PathWithMinimumEffort {
    public static void main(String[] args) {
        PathWithMinimumEffort s = new PathWithMinimumEffort();
        int[][] matrix = new int[][]{
                {1, 1},
                {1, 1},
        };

        System.out.println(s.minimumEffortPathI(matrix));    // 2
        System.out.println(s.minimumEffortPath(matrix));    // 2
                matrix = new int[][]{
                {1, 2, 3},
                {3, 8, 2},
                {5, 3, 5}
        };
        System.out.println(s.minimumEffortPathI(matrix));    // 2
        System.out.println(s.minimumEffortPath(matrix));    // 2

        matrix = new int[][]{{3}};
        System.out.println(s.minimumEffortPath(matrix));    // 0
        System.out.println(s.minimumEffortPathI(matrix));    // 0
    }

    // Method 3: Dijkstra - best first search
    // Time O(mnlog(mn))
    // Space O(mn)
    private static final int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    public int minimumEffortPathIII(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[] {0, 0, 0});
        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int i = p[0], j = p[1];
            if(i == m - 1 && j == n - 1) break;
            for(int[] d: dir) {
                int x = i + d[0], y = j + d[1];
                if(x < 0 || x >= m || y < 0 || y >= n) continue;
                int alt = Math.max(p[2], Math.abs(heights[i][j] - heights[x][y]));
                if(alt < dist[x][y]) {
                    pq.add(new int[] {x, y, dist[x][y] = alt});
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    // Recursion + memo:
    public int minimumEffortPathI(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // subproblem
        // (0, 0) -> (m-2, n-1)
        // (0, 0) -> (m-1, n-2)
        int[][] dp = new int[heights.length][heights[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(m - 1, n - 1, heights, dp);
    }

    private int helper(int endI, int endJ, int[][] heights, int[][] dp) {
        // base case
        if (endI == 0 && endJ == 0) {
            dp[0][0] = 0;
        }
        if (dp[endI][endJ] != -1) {
            return dp[endI][endJ];
        }
        // subproblems: 1. from top 2. from left
        // recursion rule
        int result = Integer.MAX_VALUE;
        for (int[] dir : DIRS) {
            int prevEndI = endI + dir[0];
            int prevEndJ = endJ + dir[1];
            if (withinRange(endI, endJ, dir, heights)) {
                int prevResult = helper(prevEndI, prevEndJ, heights, dp);
                int currEffort = Math.abs(heights[prevEndI][prevEndJ] - heights[endI][endJ]);
                currEffort = Math.max(prevResult, currEffort);
                result = Math.min(currEffort, result);
            }
        }
        dp[endI][endJ] = result;
        return result;
    }


    // Method 1: DFS
    // each level try to visit a cell, at most mn levels, branching factor 4
    // Time O(4^mn)
    // Space O(mn)
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int[] min = new int[]{Integer.MAX_VALUE};
        int[] maxDiff = new int[]{ 0 };
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        dfs(0, 0, heights, visited, maxDiff, min);
        return min[0];
    }

    private void dfs(int x, int y, int[][] heights, boolean[][] visited, int[] maxDiff, int[] min) {
        if (x == visited.length - 1 && y == visited[0].length - 1) {
            min[0] = Math.min(min[0], maxDiff[0]);
            return;
        }
        for (int[] dir : DIRS) {
            if (withinRange(x, y, dir, heights) && !visited[x + dir[0]][y + dir[1]]) {
                int preMaxDiff = maxDiff[0];
                visited[x + dir[0]][y + dir[1]] = true;
                maxDiff[0] = Math.max(maxDiff[0], Math.abs(heights[x][y] - heights[x + dir[0]][y + dir[1]]));
                dfs(x + dir[0], y + dir[1], heights, visited, maxDiff, min);
                visited[x + dir[0]][y + dir[1]] = false;
                maxDiff[0] = preMaxDiff;
            }
        }
    }

    private boolean withinRange(int x, int y, int[] dir, int[][] heights) {
        int nexX = x + dir[0];
        int newY = y + dir[1];
        return nexX >= 0 && nexX < heights.length && newY >= 0 && newY < heights[0].length;
    }
}
