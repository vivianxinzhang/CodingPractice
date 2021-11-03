package com.company;
import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule s = new CourseSchedule();

        int[][] prerequisites = new int[][]{{1, 0}, {0, 2}};
        System.out.println(s.canFinish(3, prerequisites));  // true 2 -> 0 -> 1

        prerequisites = new int[][]{{1, 0}};
        System.out.println(s.canFinish(2, prerequisites));  // true 0 -> 1

        prerequisites = new int[][]{{1, 0}, {2, 0}};
        System.out.println(s.canFinish(3, prerequisites));  // true 0 -> 1, 2

        prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(s.canFinish(2, prerequisites));  // false
    }

    // Note:
    // 1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    // 2. You may assume that there are no duplicate edges in the input prerequisites.
    // Method 2: topological sort
    // Time O(v + e)
    // Space O(v)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        return topologicalSort(numCourses, graph);
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] row : prerequisites) {
            int course = row[0];
            int pre = row[1];
            graph.get(pre).add(course);
        }
        return graph;
    }

    private boolean topologicalSort(int numCourses, List<List<Integer>> graph) {
        int[] inDegree = new int[numCourses];
        for (List<Integer> courses : graph) {
            for (int course : courses) {
                inDegree[course]++;
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] array = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            array[index++] = pre;
            List<Integer> courses = graph.get(pre);
            for (int course : courses) {
                inDegree[course]--;
                if (inDegree[course] == 0) {
                    queue.offer(course);
                }
            }
        }
        return index == numCourses;
    }

    // Method 1:
    // DFS mark nodes with different states: visiting 1 and visited 2 to detect cycle
    // Time O(n)
    // Space O(n)
    public boolean canFinishI(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
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
