package com.company;
import java.util.Arrays;

public class GameOfLife {
    public static void main(String[] args) {
        GameOfLife s = new GameOfLife();
        int[][] board = new int[][] {{1, 1}, {1, 0}};
        print(board);
        s.gameOfLife(board);
        System.out.println();
        print(board);
    }

    private static void print(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // follow up: in place - bit operation
    // use the second bit to store state for next round
    public void gameOfLife(int[][] board) {
        int[][] numOfNeis = computeNeibor(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int neighbors = numOfNeis[i][j];
                if (board[i][j] == 1) {
                    if (neighbors == 2 || neighbors == 3) {
                        board[i][j] |= 0b10;    // lives on to the next generation.
                    }
                } else {
                    if (neighbors == 3) {
                        board[i][j] |= 0b10;    // change to 1 next round
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>>= 1;
            }
        }
    }

    // 1. Any live cell with fewer than two live neighbors dies as if caused by under-population.
    // 2. Any live cell with two or three live neighbors lives on to the next generation.
    // 3. Any live cell with more than three live neighbors dies, as if by over-population.
    // 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    // Time O()
    // Space O()
    private static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public void gameOfLifeI(int[][] board) {
        int[][] numOfNeis = computeNeibor(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int neighbors = numOfNeis[i][j];
                if (board[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (neighbors == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    private int[][] computeNeibor(int[][] board) {
        int[][] numOfNeis = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                for (int[] dir : DIRS) {
                    count += getNei(i + dir[0], j + dir[1], board);
                }
                numOfNeis[i][j] = count;
            }
        }
        return numOfNeis;
    }

    private int getNei(int i, int j, int[][] board) {
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
            return board[i][j];
        }
        return 0;
    }
}
