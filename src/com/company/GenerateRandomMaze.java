package com.company;
import java.util.*;

/**
  Randomly generate a maze of size N * N (where N = 2K + 1)
  whose corridor and wall’s width are both 1 cell.
  For each pair of cells on the corridor, there must exist one and only one path between them.
  (Randomly means that the solution is generated randomly, and whenever the program is executed,
  the solution can be different.).
  The wall is denoted by 1 in the matrix and corridor is denoted by 0.
*/
public class GenerateRandomMaze {
    public static void main(String[] args) {
        GenerateRandomMaze s = new GenerateRandomMaze();
        int[][] res = s.maze(3);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        res = s.maze(5);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Assumptions:
    // 1. n = 2 * k + 1, where k >= 0
    // 2. the top left corner must be corridor
    // 3. there should be as many corridor cells as possible
    // 4. for each pair of cells on the corridor, there must exist one and only one path between them
    // Time O(4^mn)
    // Space O(mn)
    public int[][] maze(int n) {
        int[][] maze = new int[n][n];
        // initialize the matrix as only (0, 0) is corridor,
        // other cells are all walls at the beginning.
        // later we are trying to break the walls to form corridors
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] =0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }
        generate(maze, 0, 0);
        return maze;
    }

    private void generate(int[][] maze, int x, int y) {
        // get a random shuffle of all the possible directions,
        // and follow the shuffled order to do DFS & backtrack
        Dir[] dirs = Dir.values();
        shuffle(dirs);
        for (Dir dir : dirs) {
            // advance by two steps
            // For each pair of cells on the corridor, there must exist one and only one path between them.
            int nextX = dir.moveX(x, 2);
            int nextY = dir.moveY(y, 2);
            if (isValidWall(maze, nextX, nextY)) {
                // only if the cell is a wall(meaning we have not visited before),
                // we break the walls through to make it corridors
                maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
                maze[nextX][nextY] = 0;
                generate(maze, nextX, nextY);
            }
        }
    }

    // get a random order of the directions
    private void shuffle(Dir[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            // randomize generate an index between [i, n - 1]
            int randomIdx = i + (int) (Math.random() * (dirs.length - i));
            Dir tmp = dirs[i];
            dirs[i] = dirs[randomIdx];
            dirs[randomIdx] = tmp;
        }
    }

    // check if the position (x, y) is within the maze and it is a wall
    private boolean isValidWall(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }

    // this is an example of using enum in Java,
    // basically enum is good and recommended way for representing a set of predefined constants
    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), WEST(0, -1), EAST(0, 1);

        int deltaX;
        int deltaY;

        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // move certain times of deltaX
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }

        // move certain times of deltaY
        public int moveY(int y, int times) {
            return y + times * deltaY;
        }
    }

    // [0, 1, 0, 0, 0],
    // [0, 1, 1, 1, 0],
    // [0, 0, 0, 1, 0],
    // [1, 1, 0, 1, 0],
    // [1, 1, 0, 0, 0]]
    // Assumptions:
    // 1. N = 2K + 1 and K >= 0
    // 2. the top left corner must be corridor
    // 3. there should be as many corridor cells as possible
    // 4. for each pair of cells on the corridor, there must exist one and only one path between them
    // levels: n^2/4  branch factor: 4
    // Time O(4^((n^2)/4))
    // Space O(n^2/4)
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] mazeI(int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                }
            }
        }
        helper(result, 0, 0);
        return result;
    }

    private void helper(int[][] result, int i, int j) {
        shuffle(DIRS);
        for (int[] dir : DIRS) {
            int nextX = i + 2 * dir[0];
            int nextY = j + 2 * dir[1];
            if (validWall(result, nextX, nextY)) {
                result[i + dir[0]][j + dir[1]] = 0;
                result[nextX][nextY] = 0;
                helper(result, nextX, nextY);
            }
        }
    }

    private boolean validWall(int[][] result, int i, int j) {
        return i >= 0 && i < result.length && j >= 0 && j < result.length && result[i][j] == 1;
    }

    private void shuffle(int[][] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            // random pick index bettwen i and n - 1
            int index = (int)(Math.random() * (dirs.length - i)) + i;
            int[] tmp = dirs[i];
            dirs[i] = dirs[index];
            dirs[index] = tmp;
        }
    }
}
