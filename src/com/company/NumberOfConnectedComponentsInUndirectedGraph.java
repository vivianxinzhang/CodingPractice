package com.company;
import java.util.*;

public class NumberOfConnectedComponentsInUndirectedGraph {
    public static void main(String[] args) {
        System.out.println("main");
        NumberOfConnectedComponentsInUndirectedGraph s = new NumberOfConnectedComponentsInUndirectedGraph();
        int[][] edges = new int[][]{{0,1},{1,2},{3,4},{2,4},{1,4},{0,4}};
        System.out.println(s.countComponents(7, edges));

        edges = new int[][]{{2,4},{1,5},{2,3},{2,6},{0,2},{5,6},{1,2},{1,3},{0,5},{3,5},{1,4},{0,6}};
        System.out.println(s.countComponents(7, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println(s.countComponents(5, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(s.countComponents(5, edges));
    }

    // Time O(n^2)
    // Space O(n)
    private int count;
    public int countComponents(int n, int[][] edges) {
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
