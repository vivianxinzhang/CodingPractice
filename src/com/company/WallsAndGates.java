package com.company;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class WallsAndGates {
    public static void main(String[] args) {
        WallsAndGates s = new WallsAndGates();
        int[][] rooms = {{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        s.wallsAndGates(rooms);
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] wallsAndGates(int[][] rooms) {
        // Write your solution here
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                boolean[][] visited = new boolean[rooms.length][rooms[0].length];
                if (rooms[i][j] == 0) {
                    BFS(rooms, i, j, visited);
                }
            }
        }
        return rooms;
    }

    private void BFS(int[][] rooms, int i, int j, boolean[][] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Pair tmp = queue.poll();
                if (tmp.row - 1 >= 0 && rooms[tmp.row - 1][tmp.col] > 0 && !visited[tmp.row - 1][tmp.col]) {
                    queue.offer(new Pair(tmp.row - 1, tmp.col));
                    rooms[tmp.row - 1][tmp.col] = Math.min(rooms[tmp.row - 1][tmp.col], step);
                    visited[tmp.row - 1][tmp.col] = true;
                }
                if (tmp.row + 1 < rooms.length && rooms[tmp.row + 1][tmp.col] > 0 && !visited[tmp.row + 1][tmp.col]) {
                    queue.offer(new Pair(tmp.row + 1, tmp.col));
                    rooms[tmp.row + 1][tmp.col] = Math.min(rooms[tmp.row + 1][tmp.col], step);
                    visited[tmp.row + 1][tmp.col] = true;
                }
                if (tmp.col - 1 >= 0 && rooms[tmp.row][tmp.col - 1] > 0 && !visited[tmp.row][tmp.col - 1]) {
                    queue.offer(new Pair(tmp.row, tmp.col - 1));
                    rooms[tmp.row][tmp.col - 1] = Math.min(rooms[tmp.row][tmp.col - 1], step);
                    visited[tmp.row][tmp.col - 1] = true;
                }
                if (tmp.col + 1 < rooms[0].length && rooms[tmp.row][tmp.col + 1] > 0 && !visited[tmp.row][tmp.col + 1]) {
                    queue.offer(new Pair(tmp.row, tmp.col + 1));
                    rooms[tmp.row][tmp.col + 1] = Math.min(rooms[tmp.row][tmp.col + 1], step);
                    visited[tmp.row][tmp.col + 1] = true;
                }
            }
            step++;
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
