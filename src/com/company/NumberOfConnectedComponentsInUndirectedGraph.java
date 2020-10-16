package com.company;
import java.util.*;

public class NumberOfConnectedComponentsInUndirectedGraph {
    public static void main(String[] args) {
        System.out.println("main");
        NumberOfConnectedComponentsInUndirectedGraph s = new NumberOfConnectedComponentsInUndirectedGraph();
        int[][] edges = new int[][]{{2,4},{1,5},{2,3},{2,6},{0,2},{5,6},{1,2},{1,3},{0,5},{3,5},{1,4},{0,6}};
        System.out.println(s.countComponents(7, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println(s.countComponents(5, edges));

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(s.countComponents(5, edges));
    }

    // Time O(mn + n)
    // Space O(n)
    public int countComponents(int n, int[][] edges) {
        // Write your solution here
        int num = 0;
        Map<Integer, Integer> groups = new HashMap<>();
        boolean[] visited = new boolean[n];
        for (int[] pair : edges) {
            Integer groupNum0 = groups.get(pair[0]);
            Integer groupNum1 = groups.get(pair[1]);
            if (groupNum0 == null && groupNum1 == null) {
                groups.put(pair[0], num);
                groups.put(pair[1], num);
                visited[pair[0]] = true;
                visited[pair[1]] = true;
                num++;
            } else if (groupNum0 == null) {
                groups.put(pair[0], groupNum1);
                visited[pair[0]] = true;
            } else if (groupNum1 == null) {
                groups.put(pair[1], groupNum0);
                visited[pair[1]] = true;
            }
        }
        for (int i = 0; i <= n - 1; i++) {
            if (visited[i] != true) {
                num++;
            }
        }
        return num;
    }
}
