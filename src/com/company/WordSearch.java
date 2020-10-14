package com.company;

public class WordSearch {
    public static void main(String[] args) {
        WordSearch s = new WordSearch();
        char[][] board = null;
//        System.out.println(s.isWord(board, "abc"));
//
//        board = new char[0][0];
//        System.out.println(s.isWord(board, "abc"));

        board = new char[][] {{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}};
        System.out.println(s.isWord(board, "abc"));
        System.out.println(s.isWord(board, "abcced"));
        System.out.println(s.isWord(board, "see"));
        System.out.println(s.isWord(board, "abcd"));
    }

    // Time O(mn * word.length^4)
    // Space O(mn * word.length)
    public boolean isWord(char[][] board, String word) {
        // Write your solution here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        if (index == word.length() - 1) {
            return true;
        }
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        if (row - 1 >= 0 && !visited[row - 1][col] && board[row - 1][col] == word.charAt(index + 1)) {
            visited[row - 1][col] = true;
             up = dfs(board, row - 1, col, word, index + 1, visited);
        }
        if (row + 1 < board.length && !visited[row + 1][col] && board[row + 1][col] == word.charAt(index + 1)){
            visited[row + 1][col] = true;
             down = dfs(board, row + 1, col, word, index + 1, visited);
        }
        if (col - 1 >= 0 && !visited[row][col - 1] && board[row][col - 1] == word.charAt(index + 1)) {
            visited[row][col - 1] = true;
             left = dfs(board, row, col - 1, word, index + 1, visited);
        }
        if (col + 1 < board[0].length && !visited[row][col + 1] && board[row][col + 1] == word.charAt(index + 1)) {
            visited[row][col + 1] = true;
             right = dfs(board, row, col + 1, word, index + 1, visited);
        }
        return up || down || left || right;
    }
}
