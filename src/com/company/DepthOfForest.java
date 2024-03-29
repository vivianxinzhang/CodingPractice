package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DepthOfForest {
    public static void main(String[] args) {
        DepthOfForest s = new DepthOfForest();

        int[] array = null;
        System.out.println(s.depth(array));     // 0
        array = new int[0];
        System.out.println(s.depth(array));     // 0
        array = new int[] {2, 2, -1};
        System.out.println(s.depth(array));     // 2
        array = new int[] {2, 2, -1, 4, 5, 6, 3};
        System.out.println(s.depth(array));     // -1
        array = new int[] {2, 2, -1, 5, 5, -1, 3};
        System.out.println(s.depth(array));     // 3
    }

    // Assumptions:
    // The given array is not null or empty, all the elements in the array are in the range of [-1, N - 1] where N is the length of the array.
    // Corner Cases:
    // You should be able to identify that there could be a cycle in the forest, what if that is the case? Return -1

    // Method 3: union find

    // Method 2: dfs + memo

    // Method 1: dfs
    // Time O(n^2)
    // Space O(n)
    public int depth(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] max = new int[] {0};
        for (int i = 0; i < array.length; i++) {
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            if (!dfs(array, i, 1, visited, max)) {
                return -1;
            }
        }
        return max[0];
    }

    private boolean dfs(int[] array, int i, int depth, Set<Integer> visited,int[] max) {
        if (array[i] == -1) {
            max[0] = Math.max(max[0], depth);
            return true;
        }
        int pIdx = array[i];
        if (visited.contains(pIdx)) {
            return false;
        }
        return dfs(array, pIdx, depth + 1, visited, max);
    }
}
