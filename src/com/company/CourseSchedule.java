package com.company;
import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule s = new CourseSchedule();
        int[][] prerequisites = new int[][]{{1,0},{0,2}};
        System.out.println(s.canFinish(3, prerequisites));  // true 2 -> 0 -> 1

        prerequisites = new int[][]{{1,0}};
        System.out.println(s.canFinish(2, prerequisites));  // true 0 -> 1

        prerequisites = new int[][]{{1,0},{0,1}};
        System.out.println(s.canFinish(2, prerequisites));  // false
    }

    // Method 1: DFS mark nodes with different states: visiting and visited
    // Time O(n)
    // Space O(n)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            graph.get(course).add(pre);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int cur, List<List<Integer>> graph, int[] visited) {
        // node is being visiting (find cycle)
        if (visited[cur] == 1) {
            return false;
        }
        // node has been visited
        if (visited[cur] == 2) {
            return true;
        }
        for (Integer nei : graph.get(cur)) {
            visited[cur] = 1;   // mark as visiting
            if (!dfs(nei, graph, visited)) {
                return false;
            }
            visited[cur] = 2;   // mark as visited
        }
        return true;
    }
}
