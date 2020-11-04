package com.company;
import java.util.*;

public class MinimumHeightTrees {
    public static void main(String[] args) {
        MinimumHeightTrees s = new MinimumHeightTrees();
        int[][] edges = new int[][] {{1, 0}, {1, 2}, {1, 3}};
        List<List<Integer>> neighbors = s.getNeightbors(4, edges);
        System.out.println(s.findHeight(0, 4, neighbors));
        System.out.println(s.findHeight(1, 4, neighbors));
        System.out.println(s.findHeight(2, 4, neighbors));
        System.out.println(s.findHeight(3, 4, neighbors));
        System.out.println(s.findMinHeightTrees(4, edges));


        edges = new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(s.findMinHeightTrees(6, edges));

        edges = new int[][] {};
        System.out.println(s.findMinHeightTrees(1, edges));

        edges = new int[][] {{0, 1}};
        System.out.println(s.findMinHeightTrees(2, edges));
    }

    // Time O(n(n + edges.length)) -> O(V(V + E))
    // Space O(n) -> O(V)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> neighbors = getNeightbors(n, edges);
        List<Integer> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int height = findHeight(i, n, neighbors);
            if (height == minHeight) {
                result.add(i);
            } else if (height < minHeight) {
                minHeight = height;
                result = new ArrayList<>();
                result.add(i);
            }
        }
        return result;
    }

    // Time O(n + edges.length) -> O(V + E)
    // Space O(n) -> O(V)
    private int findHeight(int i, int n, List<List<Integer>> neighbors) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(i);
        boolean[] visited = new boolean[n];
        visited[i] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int curr = queue.poll();
                for (int nei : neighbors.get(curr)) {
                    if (!visited[nei]) {
                        queue.offer(nei);
                        visited[nei] = true;
                    }
                }
            }
            level++;
        }
        return level - 1;
    }

    // Time O(n + edges.length) -> O(V + E)
    // Space O(n) -> O(V)
    public List<List<Integer>> getNeightbors(int n, int[][] edges) {
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0]).add(edges[i][1]);
            neighbors.get(edges[i][1]).add(edges[i][0]);
        }
        return neighbors;
    }

}
