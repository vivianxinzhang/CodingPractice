package com.company;
import java.util.*;

public class CourseScheduleII {
    public static void main(String[] args) {
        CourseScheduleII s = new CourseScheduleII();
        System.out.println();
        int[][]  prerequisites = new int[][]{{1,0}};
        int[] result = s.findOrder(2, prerequisites);
        System.out.println(Arrays.toString(result));  // true 0 -> 1

        prerequisites = new int[][]{{1,0},{0,2}};
        result = s.findOrder(3, prerequisites);
        System.out.println(Arrays.toString(result));  // true 2 -> 0 -> 1

        prerequisites = new int[][]{{1,0},{0,1}};
        result = s.findOrder(2, prerequisites);
        System.out.println(Arrays.toString(result));  // false
    }

    // Method 1: topological sort
    // Time O(v + e)
    // Space O(v)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // the adjacency list representation of prerequisite.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            graph.get(pre).add(course);
        }
        return topologicalSort(graph);
    }

    // graph is the adjacency list representation of course prerequisites.
    private int[] topologicalSort(List<List<Integer>> graph) {
        int numCourses = graph.size();
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (int pre = 0; pre < numCourses; pre++) {
            for (int course : graph.get(pre)) {
                incomingEdges[course]++;
            }
        }

        // nodes with no incoming edges.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int x = 0; x < numCourses; x++) {
            if (incomingEdges[x] == 0) {
                queue.offer(x);
            }
        }

        int numExpanded = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            topologicalOrder[numExpanded++] = pre;
            for (int course : graph.get(pre)) {
                if (--incomingEdges[course] == 0) {
                    queue.offer(course);
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder : new int[]{};
    }
}
