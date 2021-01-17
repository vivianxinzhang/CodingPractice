package com.company;
import java.util.*;

public class Bipartite {
    public static void main(String[] args) {
        Bipartite s = new Bipartite();
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(2);
        one.neighbors.add(two);
        two.neighbors.add(one);
        //  1 -- 2
        List<GraphNode> graph = new ArrayList<>();
        graph.add(one);
        System.out.println(s.isBipartite(graph));   // true
        GraphNode three = new GraphNode(3);
        //  1 -- 2
        //   \  /
        //     3    not Bipartite
    }

    // Best First Search
    // 1. 一般用queue来做 建好queue之后记得把第一个点放进去
    // 2. 每一轮expand一个点generate 被expand的这个点的neighbor
    // Time O(V + E)
    // Space O(V)
    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        if (graph == null) {
            return true;
        }
        HashMap<GraphNode, Integer> visited= new HashMap<>();
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        // if this node has been traversed, no need to do BFS again
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        visited.put(node, 1);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();  // expand curr
            int currGroup = visited.get(curr);
            int neiGroup = currGroup == 1 ? 0 : 1;
            for (GraphNode nei : curr.neighbors) {  // generate curr.neighbors
                if (visited.get(nei) == null) {
                    queue.offer(nei);
                    visited.put(nei, neiGroup);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }
}
