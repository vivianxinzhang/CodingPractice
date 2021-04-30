package com.company;
import java.util.*;

public class NumberOfConnectedComponentsInUndirectedGraph {
    public static void main(String[] args) {
        NumberOfConnectedComponentsInUndirectedGraph s = new NumberOfConnectedComponentsInUndirectedGraph();
        int[][] edges = new int[][]{{0,1},{1,2},{3,4},{2,4},{1,4},{0,4}};
        System.out.println(s.countComponents(7, edges));    // 3
        System.out.println(s.countComponentsI(7, edges));    // 3

        edges = new int[][]{{2,4},{1,5},{2,3},{2,6},{0,2},{5,6},{1,2},{1,3},{0,5},{3,5},{1,4},{0,6}};
        System.out.println(s.countComponents(7, edges));    // 1
        System.out.println(s.countComponentsI(7, edges));    // 1

        edges = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println(s.countComponents(5, edges));    // 2
        System.out.println(s.countComponentsI(5, edges));    // 2

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(s.countComponents(5, edges));    // 1
        System.out.println(s.countComponentsI(5, edges));    // 1
    }

    // Note:
    // You can assume that no duplicate edges will appear in edges.
    // Since all edges are undirected, [0, 1] is the same as [1, 0] and
    // thus will not appear together in edges
    // Method 3: BFS
    // Time O(v+e)
    // Time O(v+e)
    public int countComponents(int n, int[][] connections) {
        List<List<Integer>> adjList = buildGraph(n, connections);
        boolean[] visited = new boolean[n];
        int countConnectedComponent = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(visited, i, adjList);
                // Detects a connected component
                countConnectedComponent++;
            }
        }

        return countConnectedComponent;
    }

    private void bfs(boolean[] visited, int i, List<List<Integer>> adjList) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;
            for (int nei : adjList.get(cur)) {
                if (!visited[nei]) {
                    queue.offer(nei);
                }
            }
        }
    }

    // Minimum edges to make n nodes connected is n - 1.
    // Count of existing connected components - 1 is minimum edges to add make the whole graph connected.
    // To find all the components of a graph, we simply loop through its vertices,
    // and do DFS search on unvisited ones. A search that begins at v will find the
    // entire component containing v (and no more) before returning.
    // Method 2: DFS
    // Time O(v+e)
    // Time O(v+e)
    public int countComponentsII(int n, int[][] connections) {
        List<List<Integer>> adjList = buildGraph(n, connections);
        boolean[] visited = new boolean[n];
        int countConnectedComponent = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, i, adjList);
                // Detects a connected component
                countConnectedComponent++;
            }
        }

        return countConnectedComponent;
    }

    private  List<List<Integer>> buildGraph(int n, int[][] connections) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] connection: connections) {
            adjList.get(connection[0]).add(connection[1]);
            adjList.get(connection[1]).add(connection[0]);
        }
        return adjList;
    }

    private void dfs(boolean[] visited, int from, List<List<Integer>> adjList) {
        visited[from] = true;
        for (int to : adjList.get(from)) {
            if (!visited[to]) {
                dfs(visited, to, adjList);
            }
        }
    }

    // Method 1: Union find
    // Time O(n^2)
    // Space O(n)
    private int count;
    public int countComponentsI(int n, int[][] edges) {
        this.count = n;
        int[] root = new int[n];
        for (int i = 0; i < n; ++i) root[i] = i;
        for (int[] edge : edges) {
            union(root, edge[0], edge[1]);
        }

        return this.count;
    }

    private int find(int[] root, int i) {
        if (root[i] == i) return i;
        return find(root, root[i]);
    }

    private void union(int[] root, int src, int dst) {
        int srcRoot = find(root, src);
        int dstRoot = find(root, dst);
        if (srcRoot != dstRoot) {
            root[srcRoot] = dstRoot;
            --this.count;
        }
    }
}
