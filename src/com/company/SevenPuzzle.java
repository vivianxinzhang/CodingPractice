package com.company;
import java.util.*;

public class SevenPuzzle {
    public static void main(String[] args) {
        SevenPuzzle s = new SevenPuzzle();

        int[] values = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(s.numOfSteps(values));   // -1

        values = new int[] {7, 3, 1, 5, 4, 6, 2, 0};
        System.out.println(s.numOfSteps(values));   // -1

        values = new int[] {4, 1, 2, 3, 0, 5, 6, 7};
        /*
              4  1  2  3      <=>       0  1  2  3
              0  5  6  7     1 step     4  5  6  7
         */
        System.out.println(s.numOfSteps(values));   // 1

        values = new int[] {1, 5, 2, 3, 4, 0, 6, 7};
        /*
              1  5  2  3      <=>      1  0  2  3     <=>    0  1  2  3
              4  0  6  7     1 step    4  5  6  7   1 step   4  5  6  7
         */
        System.out.println(s.numOfSteps(values));   // 2
    }

    // 什么是点？ 每一个棋盘
    //	         有多少种排列方式？8！每个格子选一个树
    // 什么是边？每一个棋盘 和 它移动一步能变成的棋盘之间有边
    // 看起来是个有向图  其实是个无向图  可以变过去 也可以变回来
    // Follow up: What if there are thousands of queries? How to minimize the processing time for each query?
    // 从终点出发 做一次 BFS1 就可以了
    // Method 2: breadth first search from ending point
    // and use a hashmap to memorize all possible generated boards and its steps
    // Time O(n!)       V = O(n!)  E = O(n! * 4/2)  n is values.length
    // Space O(n!)      each node has at most 4 edges, undirected /2
    final static int[][] DIRS = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numOfStepsI(int[] values) {
        Queue<Board> queue = new ArrayDeque<>();
        Map<Board, Integer> boardStep = new HashMap<>();

        Board start = new Board(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
        queue.offer(start);
        boardStep.put(start, 0);

        while (!queue.isEmpty()) {
            Board board = queue.poll();
            int step = boardStep.get(board);

            int[] zeroPos = board.findZero();
            int zeroI = zeroPos[0];
            int zeroJ = zeroPos[1];

            for (int[] dir : DIRS) {
                int i = zeroI + dir[0];
                int j = zeroJ + dir[1];
                if (!board.outOfBound(i, j)) {
                    board.swap(zeroI, zeroJ, i, j);
                    if (!boardStep.containsKey(board)) {
                        Board newBoard = board.clone();
                        queue.offer(newBoard);
                        boardStep.put(newBoard, step + 1);
                    }
                    board.swap(zeroI, zeroJ, i, j);
                }
            }
        }
        return boardStep.getOrDefault(new Board(values), -1);
    }

    // Method 1:
    // breadth first search from start to end
    // Time O(n!)       V = O(n!)  E = O(n! * 4/2)  n is values.length
    // Space O(n!)      each node has at most 4 edges, undirected /2
    public int numOfSteps(int[] values) {
        Queue<Board> queue = new ArrayDeque<>();
        Set<Board> set = new HashSet<>();

        Board start = new Board(values);
        Board end = new Board(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
        queue.offer(start);
        int numOfSteps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Board board = queue.poll();
                if (board.equals(end)) {
                    return numOfSteps;
                }

                int[] zeroPos = board.findZero();   // O(8)
                int zeroI = zeroPos[0];
                int zeroJ = zeroPos[1];

                for (int[] dir : DIRS) {
                    int i = zeroI + dir[0];
                    int j = zeroJ + dir[1];
                    if (!board.outOfBound(i, j)) {
                        board.swap(zeroI, zeroJ, i, j);
                        if (!set.contains(board)) {
                            Board newBoard = board.clone();     // O(8)
                            queue.offer(newBoard);
                            set.add(newBoard);
                        }
                        board.swap(zeroI, zeroJ, i, j);
                    }
                }
            }
            numOfSteps++;
        }
        return -1;
    }

    static class Board {
        public final static int R = 2;
        public final static int C = 4;

        public Board() {
        }

        /* initialize board
            0  1  2  3
            4  5  6  7
        */
        public Board(int[] values) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    board[i][j] = values[i * C + j];
                }
            }
        }

        // swap adjacent points on the board
        public void swap(int i1, int j1, int i2, int j2) {
            int temp = board[i1][j1];
            board[i1][j1] = board[i2][j2];
            board[i2][j2] = temp;
        }

        // zero can swap with it's neighbors on the board
        // need to find zero before the next move
        public int[] findZero() {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) {
                        return new int[] {i, j};
                    }
                }
            }
            return null;
        }

        public boolean outOfBound(int i, int j) {
            return i < 0 || i >= R || j < 0 || j >= C;
        }

        /*  each cell has a different weight
            10000000   1000000   100000   10000
            1000       100       10       1
        */
        @Override
        public int hashCode() {
            int code = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    code = code * 10 + board[i][j];
                }
            }
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) {
                return false;
            }
            Board b = (Board) o;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j]  != b.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        protected Board clone() {
            Board c = new Board();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    c.board[i][j] = board[i][j];
                }
            }
            return c;
        }

        private int[][] board = new int[R][C];
    }
}

