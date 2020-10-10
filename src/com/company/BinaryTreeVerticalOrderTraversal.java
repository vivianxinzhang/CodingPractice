package com.company;
import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    // Time O(n)
    // Space O(n)
    public List<Integer> verticalOrder(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<TreeNode, Integer> seen = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        seen.put(root, 0);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = seen.get(curr);
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(curr.key);
            if (curr.left != null) {
                queue.offer(curr.left);
                seen.put(curr.left, col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                seen.put(curr.right, col + 1);
            }
        }
        result = getResult(map);
        return result;
    }

    private List<Integer> getResult(Map<Integer, List<Integer>> map) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            minHeap.offer(key);
        }
        while (!minHeap.isEmpty()) {
            Integer curCol = minHeap.poll();
            result.addAll(map.get(curCol));
        }
        return result;
    }
}
