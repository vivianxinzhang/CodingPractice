package com.company;
import java.util.*;

public class DisjointWhiteObjects {
    public static void main(String[] args) {
        System.out.println("main");
        DisjointWhiteObjects s = new DisjointWhiteObjects();

        int[][] matrix = new int[][]{{0, 0, 0, 1},{1, 0, 1, 1},{1, 1, 0, 0},{0, 1, 0, 0}};
        System.out.println(s.whiteObjects(matrix));
    }

    // Time O(mn)
    // Space O(mn)
    public int whiteObjects(int[][] matrix) {
        // Write your solution here
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    BFS(matrix, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void BFS(int[][] matrix, int i, int j, boolean[][] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (curr.row - 1 >= 0 && matrix[curr.row - 1][curr.col] == 0 && !visited[curr.row - 1][curr.col]) {
                queue.offer(new Pair(curr.row - 1, curr.col));
                visited[curr.row - 1][curr.col] = true;
            }
            if (curr.row + 1 < matrix.length && matrix[curr.row + 1][curr.col] == 0 && !visited[curr.row + 1][curr.col]) {
                queue.offer(new Pair(curr.row + 1, curr.col));
                visited[curr.row + 1][curr.col] = true;
            }
            if (curr.col - 1 >= 0 && matrix[curr.row][curr.col - 1] == 0 && !visited[curr.row][curr.col - 1]) {
                queue.offer(new Pair(curr.row, curr.col - 1));
                visited[curr.row][curr.col - 1] = true;
            }
            if (curr.col + 1 < matrix[0].length && matrix[curr.row][curr.col + 1] == 0 && !visited[curr.row][curr.col + 1]) {
                queue.offer(new Pair(curr.row, curr.col + 1));
                visited[curr.row][curr.col + 1] = true;
            }
        }
    }

    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
