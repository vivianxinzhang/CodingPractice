package com.company;
import java.util.*;

public class MinimumHeightTrees {
    public static void main(String[] args) {
        MinimumHeightTrees s = new MinimumHeightTrees();
        int[][] edges = new int[][] {{1, 0}, {1, 2}, {1, 3}};
        List<List<Integer>> neighbors = s.getNeightbors(4, edges);
        System.out.println(s.findMinHeightTrees(4, edges));     // 1

        edges = new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(s.findMinHeightTrees(6, edges));     // 3, 4

        edges = new int[][] {};
        System.out.println(s.findMinHeightTrees(1, edges));     // 0

        edges = new int[][] {{0, 1}};
        System.out.println(s.findMinHeightTrees(2, edges));     // 0, 1
    }
    // Note:
    // (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path.
    //  In other words, any connected graph without simple cycles is a tree.”
    // (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
    // Time O(v + e)
    // Space O(v + e)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        // base cases
        if (n == 1) {
            result.add(0);
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] pair : edges) {
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
            inDegree[pair[1]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                list.add(cur);
                for (int nei : map.get(cur)) {
                    inDegree[nei]--;
                    if (inDegree[nei] == 1) {
                        queue.offer(nei);
                    }
                }
            }
            result = list;
        }
        return result;
    }

    // Method 2:
    // Time O(n)
    // Space O(n)
    public List<Integer> findMinHeightTreesII(int n, int[][] edges) {
        // base cases
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }
        // Build the graph with the adjacency list
        List<Set<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbors.add(new HashSet<Integer>());
        }
        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }
        // Initialize the first layer of leaves
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (neighbors.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        // Trim the leaves until reaching the centroids
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            // remove the current leaves along with the edges
            for (Integer leaf : leaves) {
                for (Integer neighbor : neighbors.get(leaf)) {
                    neighbors.get(neighbor).remove(leaf);
                    // for nodes with only 1 edge are leaf nodes
                    if (neighbors.get(neighbor).size() == 1) {
                        newLeaves.add(neighbor);
                    }
                }
            }
            // prepare for the next round
            leaves = newLeaves;
        }
        // The remaining nodes are the centroids of the graph
        return leaves;
    }

    // Method 1: brute force
    // Time O(n(n + edges.length)) -> O(V(V + E))
    // Space O(n) -> O(V)
    public List<Integer> findMinHeightTreesI(int n, int[][] edges) {
        List<List<Integer>> neighbors = getNeightbors(n, edges);
        List<Integer> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int height = findHeight(i, neighbors);
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
    private int findHeight(int root, List<List<Integer>> neighbors) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean[] visited = new boolean[neighbors.size()];
        visited[root] = true;
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
