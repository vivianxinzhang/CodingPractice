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

        GraphNode zero1 = new GraphNode(0);
        GraphNode one1 = new GraphNode(1);
        GraphNode two1 = new GraphNode(2);
        GraphNode three1 = new GraphNode(3);
        zero1.neighbors.add(one1);
        one1.neighbors.add(zero1);
        one1.neighbors.add(two1);
        one1.neighbors.add(three1);
        two1.neighbors.add(one1);
        two1.neighbors.add(three1);
        three1.neighbors.add(one1);
        three1.neighbors.add(two1);
        // 0->1; 1->0,2,3; 2->1,3; 3->1,2
        /**
         *      0   <--->    1
         *
         *      2
         *      3
         * */
        List<GraphNode> graph1 = new ArrayList<>();
        graph1.add(zero1);
        graph1.add(one1);
        graph1.add(two1);
        graph1.add(three1);
        System.out.println(s.isBipartite(graph1));   // false
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
        Map<GraphNode, Integer> visited= new HashMap<>();
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, Map<GraphNode, Integer> visited) {
        // if this node has been traversed, no need to do BFS again
        if (visited.containsKey(node)) {
            return true;
        }
        Deque<GraphNode> queue = new ArrayDeque<>();
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
