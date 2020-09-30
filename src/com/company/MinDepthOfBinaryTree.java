package com.company;
import java.util.*;

public class MinDepthOfBinaryTree {
    public static void main(String[] args) {
        MinDepthOfBinaryTree s = new MinDepthOfBinaryTree();
        System.out.println(s.minDepthI(null));
    }

    // Iteration
    // Time O(n)
    // Space O(h) worst case O(n)
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    // Breadth First Search
    // Time O(n)
    // Space O(n)
    public int minDepthI(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {
                    return depth;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return 0;
    }
}
