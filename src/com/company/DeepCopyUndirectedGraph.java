package com.company;
import java.util.*;

public class DeepCopyUndirectedGraph {
    // Time O(V+E)
    // Space O(V)
    public List<GraphNode> copy(List<GraphNode> graph) {
        List<GraphNode> newNodes = new ArrayList<>();
        Queue<GraphNode> q = new ArrayDeque<>();
        Map<GraphNode, GraphNode> oldToNew = new HashMap<>();
        for (GraphNode node : graph) {
            oldToNew.put(node, new GraphNode(node.key));
            q.offer(node);
            newNodes.add(oldToNew.get(node));
        }
        while (!q.isEmpty()) {
            GraphNode old = q.poll();
            for (GraphNode neighbor : old.neighbors) {
                if (oldToNew.get(neighbor) == null) {
                    oldToNew.put(neighbor, new GraphNode(neighbor.key));
                    q.offer(neighbor);
                }
                oldToNew.get(old).neighbors.add(oldToNew.get(neighbor));
            }
        }
        return newNodes;
    }
}
