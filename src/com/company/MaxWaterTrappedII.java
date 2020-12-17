package com.company;

import java.util.*;

public class MaxWaterTrappedII {
    public static void main(String[] args) {
        MaxWaterTrappedII s = new MaxWaterTrappedII();
        int[][] matrix = new int[][]{
                {1, 9, 2, 5, 8, 4},
                {2, 4, 5, 1, 3, 2},
                {7, 1, 3, 5, 6, 6},
                {8, 5, 9, 3, 3, 4},
                {5, 2, 1, 7, 5, 7}};
        System.out.println(s.maxTrapped(matrix));   // 8

        matrix = new int[][]{
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
    }

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
    // Time: O(mnlogmn)
    // Space: O(mn)
    public int maxTrapped(int[][] matrix) {
        // Assumptions: matrix is not null, has size of M * N
        // M > 0 & N > 0, all the values are non-negative integers
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows < 3 || cols < 3) {
            return 0;
        }
        // Best-First-Search, minHeap maintains all the border cells
        // of the "closed area" and we always find the one with lowest
        // height to see if any of its neighbors can trap any water
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[rows][cols];
        // put all the border cells of the matrix at the beginning
        processBorder(matrix, visited, minHeap, rows, cols);
        int result = 0;
        while (!minHeap.isEmpty()) {
            Pair cur = minHeap.poll();
            // get all possible neighbor cells
            List<Pair> neighbors = allNeighbors(cur, matrix, visited);
            for (Pair nei : neighbors) {
                // if any neighbor has been visited before, we just ignore
                if (visited[nei.x][nei.y]) {
                    continue;
                }
                // adjust the neighbor cell's height to the current water level if necessary,
                // mark the neighbor cell as visited, and put the neighbor cell into the min heap
                visited[nei.x][nei.y] = true;
                // how much water can be trapped at the neighbor cell
                // the maximum water level currently is controlled by the cur cell
                result += Math.max(cur.height - nei.height, 0);
//                if (cur.height - nei.height > 0) {
//                    System.out.println("row " + nei.x + " col " + nei.y + " trapped water " + (cur.height - nei.height));
//                }
                nei.height = Math.max(cur.height, nei.height);
                minHeap.offer(nei);
            }
        }
        return result;
    }

    // put all the border cells into the min heap at the very beginning,
    // these are the start points of the whole BFS process
    private void processBorder(int[][] matrix, boolean[][] visited, PriorityQueue<Pair> minHeap, int rows, int cols) {
        for (int j = 0; j < cols; j++) {
            minHeap.offer(new Pair(0, j, matrix[0][j]));
            minHeap.offer(new Pair(rows - 1, j, matrix[rows - 1][j]));
            visited[0][j] = true;
            visited[rows - 1][j] = true;
        }

        for (int i = 1; i < rows - 1; i++) {
            minHeap.offer(new Pair(i, 0, matrix[i][0]));
            minHeap.offer(new Pair(i, cols - 1, matrix[i][cols - 1]));
            visited[i][0] = true;
            visited[i][cols - 1] = true;
        }
    }

    private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
        List<Pair> neis = new ArrayList<>();
        if (cur.x + 1 < matrix.length) {
            neis.add(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        }
        if (cur.x - 1 >= 0) {
            neis.add(new Pair(cur.x - 1, cur.y, matrix[cur.x - 1][cur.y]));
        }
        if (cur.y + 1 < matrix[0].length) {
            neis.add(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
        }
        if (cur.y - 1 >= 0) {
            neis.add(new Pair(cur.x, cur.y - 1, matrix[cur.x][cur.y - 1]));
        }
        return neis;
    }

    class Pair implements Comparable<Pair> {
        int x;  // row index
        int y;  // column index
        int height; // height of the cell in the original matrix

        Pair(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Pair another) {
            if (this.height == another.height) {
                return 0;
            }
            return this.height < another.height ? -1 : 1;
        }
    }
}
