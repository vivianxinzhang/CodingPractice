package com.company;
import java.util.*;

public class NumberofIslands {
    public static void main(String[] args) {
        System.out.println("main");
        NumberofIslands s = new NumberofIslands();

        char[][] matrix = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(s.numIslands(matrix));
        System.out.println(s.numIslandsI(matrix));
    }

    // BFS
    // Time O(mn)
    // Space O(min(m,n))
    public int numIslands(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1' && !visited[i][j]) {
                    BFS(matrix, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void BFS(char[][] matrix, int i, int j, boolean[][] visited) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (curr.row - 1 >= 0 && matrix[curr.row - 1][curr.col] == '1' && !visited[curr.row - 1][curr.col]) {
                queue.offer(new Pair(curr.row - 1, curr.col));
                visited[curr.row - 1][curr.col] = true;
            }
            if (curr.row + 1 < matrix.length && matrix[curr.row + 1][curr.col] == '1' && !visited[curr.row + 1][curr.col]) {
                queue.offer(new Pair(curr.row + 1, curr.col));
                visited[curr.row + 1][curr.col] = true;
            }
            if (curr.col - 1 >= 0 && matrix[curr.row][curr.col - 1] == '1' && !visited[curr.row][curr.col - 1]) {
                queue.offer(new Pair(curr.row, curr.col - 1));
                visited[curr.row][curr.col - 1] = true;
            }
            if (curr.col + 1 < matrix[0].length && matrix[curr.row][curr.col + 1] == '1' && !visited[curr.row][curr.col + 1]) {
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

    // DFS
    public int numIslandsI(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        return count;
    }
    private void dfs(char[][] grid,int i, int j){
        if(i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j]=='1'){
            grid[i][j]='0';
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }
}
