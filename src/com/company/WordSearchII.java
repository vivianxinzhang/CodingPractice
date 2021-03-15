package com.company;
import java.util.*;

public class WordSearchII {
    public static void main(String[] args) {
        WordSearchII s = new WordSearchII();
        char[][] board = new char[][] {{'o'}};
        String[] words = new String[] {"o"};
        System.out.println(s.findWords(board, words));  // ["o"]

        board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        words = new String[] {"oath","pea","eat","rain"};
        System.out.println(s.findWords(board, words));  // [oath, eat]
        System.out.println(s.findWordsI(board, words)); // [oath, eat]
    }

    // Method 2: using trie to do branch trimming in DFS
    // at each level put current letter in the path prefix and then try to visit four direction in the next level
    // DFS has at most m*n levels, branching factor is at most 4 (or 3), trimming depending on the Trie structure
    // Time: O(mn * 3^mn * L)   m - rows  n - cols  word.length is L
    // Space: Space O(mn)
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        // corner case check
        if (board == null || board.length == 0 || board[0].length == 0 ||
                words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        TrieNode root = buildDict(words);
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        StringBuilder sb = new StringBuilder();
        // starting from
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                helper(board, i, j, root, sb, res, visited);
            }
        }
        return new ArrayList<>(res);
    }

    private void helper(char[][] board, int row, int col, TrieNode root, StringBuilder sb, Set<String> res, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return;
        }
        char ch = board[row][col];
        if (root.children[ch - 'a'] == null) {
            return;
        }
        // add current char to path
        sb.append(ch);
        root = root.children[ch - 'a'];
        visited[row][col] = true;
        // call next level dfs in four directions
        for (int[] dir : DIRS) {
            int neiRow = dir[0] + row;
            int neiCol = dir[1] + col;
            helper(board, neiRow, neiCol, root, sb, res, visited);
        }
        // recover previous state
        visited[row][col] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private TrieNode buildDict(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                TrieNode next = cur.children[word.charAt(i) - 'a'];
                if (next == null) {
                    next = new TrieNode();
                    cur.children[word.charAt(i) - 'a'] = next;
                }
                cur = next;
            }
            cur.isWord = true;
        }
        return root;
    }

    // Method 1: DFS without using Trie
    // at each level put current letter in the path prefix and then try to visit four direction in the next level
    // DFS has at most m*n levels, branching factor is at most 4 (or 3)
    // Time: O(mn * 4^mn * L)   m - rows  n - cols  word.length is L
    // Space: Space O(mn)
    public List<String> findWordsI(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 ||
                words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        Set<String> wordSet = buildSet(words);
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, wordSet, sb, res, visited);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int row, int col, Set<String> wordSet, StringBuilder sb, Set<String> res, boolean[][] visited) {
        // check if path prefix is a word in the set
        // if empty string "" is in the wordSet, also can collect result here
        String word = sb.toString();
        if (wordSet.contains(word)) {
            res.add(word);
        }
        // base case
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return;
        }
        // add board[row][col] to current path prefix
        char ch = board[row][col];
        sb.append(ch);
        visited[row][col] = true;
        // go in to four directions
        for (int[] dir : DIRS) {
            int neiRow = dir[0] + row;
            int neiCol = dir[1] + col;
            dfs(board, neiRow, neiCol, wordSet, sb, res, visited);
        }
        // recover to previous state
        visited[row][col] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private Set<String> buildSet(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            wordSet.add(word);
        }
        return wordSet;
    }
}
