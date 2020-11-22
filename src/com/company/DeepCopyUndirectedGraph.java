package com.company;
import java.util.*;

public class DeepCopyUndirectedGraph {
    // Method 2: Recursion(DFS)
    // Recursive manner. Use map to store whether a node has been copied before
    // For every single recursion function call, we make a copy of the input node,
    // and leave all other copies of the successors to the recursion functions.
    public List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        Map<GraphNode, GraphNode> lookup = new HashMap<>();
        for (GraphNode node : graph) {
            if (!lookup.containsKey(node)) {
                lookup.put(node, new GraphNode(node.key));
                DFS(node, lookup);
            }
        }
        return new ArrayList<GraphNode>(lookup.values());

    }

    private void DFS(GraphNode node, Map<GraphNode, GraphNode> lookup) {
        GraphNode copyNode = new GraphNode(node.key);
        for (GraphNode neighbor : node.neighbors) {
            if (!lookup.containsKey(neighbor)) {
                DFS(neighbor, lookup);
            }
            copyNode.neighbors.add(lookup.get(neighbor));
        }
    }

    // Method 2: Recursion(DFS)
    // Recursive manner. Use map to store whether a node has been copied before
    // For every single recursion function call, we make a copy of the input node,
    // and leave all other copies of the successors to the recursion functions.
    // Time O(V+E)
    // Space O(V)
    public Node cloneGraph(Node input, Map<Node, Node> lookup) {
        if (input == null) {
            return null;
        }
        if (lookup.containsKey(input)) {
            return lookup.get(input);
        }
        Node copyNode = new Node(input.value);
        lookup.put(input, copyNode);
        for (Node neighbor : input.neighbors) {
            copyNode.neighbors.add(cloneGraph(neighbor, lookup));
        }
        return copyNode;
    }

    private class Node {
        int value;
        List<Node> neighbors;

        public Node(int value) {
            this.value = value;
        }
    }

    // Method 1: Breadth First Search
    // Time O(V+E)
    // Space O(V)
    public List<GraphNode> copyII(List<GraphNode> graph) {
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

    // Time O(v + e)
    // Space O(v)
    public List<GraphNode> copyI(List<GraphNode> graph) {
        List<GraphNode> newGraph = new ArrayList<>();
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            GraphNode copyNode = getOrCreate(node, map);
            BFS(node, map);
            newGraph.add(copyNode);
        }
        return newGraph;
    }

    private void BFS(GraphNode node, Map<GraphNode, GraphNode> map) {
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            GraphNode copyCurr = getOrCreate(curr, map);
            for (GraphNode neighbor : curr.neighbors) {
                GraphNode copyNeighbor = getOrCreate(neighbor, map);
                copyCurr.neighbors.add(copyNeighbor);
            }
        }
    }

    private GraphNode getOrCreate(GraphNode curr, Map<GraphNode, GraphNode> map) {
        if (!map.containsKey(curr)) {
            map.put(curr, new GraphNode(curr.key));
        }
        return map.get(curr);
    }
}