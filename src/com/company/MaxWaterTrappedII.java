package com.company;

import java.util.*;

public class MaxWaterTrappedII {
    public static void main(String[] args) {
        MaxWaterTrappedII s = new MaxWaterTrappedII();

        int[][] matrix = new int[][]{
                {3, 5, 6, 4},
                {2, 2, 3, 5},
                {5, 2, 2, 4},
                {7, 6, 6, 5}};
        System.out.println(s.maxTrapped(matrix));   // 0

        matrix = new int[][]{
                {2, 3, 4, 2},
                {3, 1, 2, 3},
                {4, 3, 5, 4}};
        System.out.println(s.maxTrapped(matrix));   // 3

        matrix = new int[][]{
                {1,9,2,5,8,4},
                {2,4,5,1,3,2},
                {7,1,3,5,6,6},
                {8,5,9,3,3,4},
                {5,2,1,7,5,7}};
        System.out.println(s.maxTrapped(matrix));   // 8

        matrix = new int[][]{
                {1, 9, 2, 5, 8, 4},
                {2, 4, 5, 1, 3, 2},
                {7, 1, 3, 5, 6, 6},
                {8, 5, 9, 3, 3, 4},
                {5, 2, 1, 7, 5, 7}};
        System.out.println(s.maxTrapped(matrix));   // 8
    }

    // Assumptions:
    // The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
    // Method: Best First Search
    // data structure:
    // priority queue: storing bars with their indexes and height
    // priority_queue的物理意义是当前边界下的蓄水池的海平面(边界高度)，其值决定了边界向内bfs探索过程中的蓄水量。
    // class bar: storing the indexes, its height and bounding bar height
    // total: recording the total trapped water
    // a m x n boolean matrix: recording checked bar
    // initialization: traverse the outtest circle of the matirx, recording in priority queue, total is 0, marking corresponding bar as true in the boolean matrix
    // for each step:
    // poll pq top, hereinafter cur
    // if the neighbors of cur are not checked, generating its neighbors and marking them as checked.
    // case 1: if neighbor's height lower than or equal current bounding bar height, total add the volumn of trapped water on such neighbor, offer neighbor into pq with cur bounding bar height
    // case 2: if neighbor's height larger than cur bounding bar height, offer neighbor into pq with neighbor's height as bounding bar height
    // termination: pq is empty
    // deduplicate: a m x n boolean matrix recording checked bars
    // Time: O(mn*logmn)
    // Space: O(mn)
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maxTrapped(int[][] matrix) {
        if (matrix == null || matrix.length < 3 || matrix[0].length < 3) {
            return 0;
        }
        // Best-First-Search, minHeap maintains all the border cells
        // of the "closed area" and we always find the one with lowest
        // height to see if any of its neighbors can trap any water
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                if (o1.waterLevel == o2.waterLevel) {
                    return 0;
                }
                return o1.waterLevel < o2.waterLevel ? -1 : 1;
            }
        });
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        // put all the border cells of the matrix at the beginning
        processBorder(matrix, minHeap, visited, m, n);
        int maxWater = 0;
        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            for (int[] dir : DIRS) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];
                if (valid(visited, nextX, nextY, m, n)) {
                    // adjust the neighbor cell's height to the current water level if necessary,
                    // mark the neighbor cell as visited, and put the neighbor cell into the min heap
                    int neiWaterLevel = Math.max(cur.waterLevel, matrix[nextX][nextY]);
                    // how much water can be trapped at the neighbor cell
                    // the maximum water level currently is controlled by the cur cell
                    Cell nei = new Cell(nextX, nextY, neiWaterLevel);
                    minHeap.offer(nei);
                    visited[nextX][nextY] = true;
                    maxWater += nei.waterLevel - matrix[nextX][nextY];
                }
            }
        }
        return maxWater;
    }

    private boolean valid(boolean[][] visited, int nextX, int nextY, int m, int n) {
        if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]) {
            return false;
        }
        return true;
    }

    // put all the border cells into the min heap at the very beginning,
    // these are the start points of the whole BFS process
    private void processBorder(int[][] matrix, PriorityQueue<Cell> minHeap, boolean[][] visited, int m, int n) {
        // top, bottom rows
        for (int col = 0; col < n; col++) {
            minHeap.offer(new Cell(0, col, matrix[0][col]));
            visited[0][col] = true;
            minHeap.offer(new Cell(m - 1, col, matrix[m - 1][col]));
            visited[m - 1][col] = true;
        }
        // left, right cols (excluding top and bottom cells)
        for (int row = 1; row < m; row++) {
            minHeap.offer(new Cell(row, 0, matrix[row][0]));
            visited[row][0] = true;
            minHeap.offer(new Cell(row, n - 1, matrix[row][n - 1]));
            visited[row][n - 1] = true;
        }
    }

    class Cell {
        int x;
        int y;
        int waterLevel;

        public Cell(int x, int y, int waterLevel) {
            this.x = x;
            this.y = y;
            this.waterLevel = waterLevel;
        }
    }
}
