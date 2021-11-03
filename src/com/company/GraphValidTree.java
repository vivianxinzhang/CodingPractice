package com.company;

import java.util.*;

public class GraphValidTree {
    public static void main(String[] args) {
        GraphValidTree s = new GraphValidTree();

        int[][] edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        /**
         *          1 - 0 - 2
         *          |   |
         *          4   3
         * */
        System.out.println(s.validTree(5, edges));      // true
        edges = new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        /**
         *          0 - 1 - 2
         *              | \ |
         *              4   3
         * */
        System.out.println(s.validTree(5, edges));      // false
    }

    // DFS
    // Time O(v+e)
    // Space O(v)
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = buildGraph(n, edges);
        // all the vertices as not visited yet
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }
            boolean flag = hasCycleDFSUtil(i, visited, graph, null);
            if(flag){
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleDFSUtil(int i, Set<Integer> visited, List<List<Integer>> graph, Integer parent) {
        visited.add(i);
        List<Integer> neighbors = graph.get(i);
        for (int nei : neighbors) {
            if (nei == parent) {
                continue;
            }
            if (visited.contains(nei)) {
                return true;
            }
            boolean hasCycle = hasCycleDFSUtil(nei, visited, graph, i);
            if (hasCycle) {
                return true;
            }
        }
        return false;
    }

    // An undirected graph is a tree if it has the following properties:
    // 1. there is no cycle
    // 2. The graph is connected
    // Q1: How to detect cycle in an undirected graph?
    // We can either use DFS or BFS. For every visited vertex 'v', if there is
    // an adjacent vertex 'u' such that 'u' is already visited and 'u' is not
    // parent of v, then there is a cycle in the graph.
    // If we don't find such an adjacent vertex for any vertex, we say that
    // there is no cycle.
    // Q2: How to check for connectivity?
    // Since the given graph is undirected, we can start BFS or DFS from any vertex
    // and check if all vertices are reachable or not. If all vertices are reachable
    // then the graph is connected, otherwise not.
    // Time O(v+e)
    // Space O(v)
    public boolean validTreeI(int n, int[][] edges) {
        List<List<Integer>> graph = buildGraph(n, edges);
        // all the vertices as not visited yet
        boolean[] visited = new boolean[n];
        // The call to isCyclicUtil serves multiple purposes
        // It returns true if graph reachable from vertex 0 is cyclcic.
        // It also marks all vertices reachable
        // from 0.
        if (isCyclicUtil(0, visited, graph, -1)) {      // O(v+e)
            return false;
        }
        // If we find a vertex which is not reachable from 0
        // (not marked by isCyclicUtil(), then we return false
        for (int i = 0; i < n; i++) {   // O(v)
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isCyclicUtil(int v, boolean[] visited, List<List<Integer>> graph, int parent) {
        // Mark the current node as visited
        visited[v] = true;
        Iterator<Integer> it = graph.get(v).iterator();
        // Recur for all the vertices adjacent to this vertex
        int i;
        while (it.hasNext()) {
            i = it.next();
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, graph, v)) {
                    return true;
                }
            } else if (i != parent) {
                // If an adjacent is visited and not parent of current vertex, then there is a cycle.
                return true;
            }
        }
        return false;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pair : edges) {
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        return graph;
    }
}
