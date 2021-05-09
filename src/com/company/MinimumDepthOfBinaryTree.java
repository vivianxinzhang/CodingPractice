package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        MinimumDepthOfBinaryTree s = new MinimumDepthOfBinaryTree();
        TreeNode one = new TreeNode(1);
        System.out.println(s.minDepth(one));     // 1

        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.right = three;
        System.out.println(s.minDepth(one));     // 3
    }

    // Clarification:
    // 1. The root itself can be a valid path if there is only one node in the binary tree
    // 2. If root == null, return 0
    // Time O(n)
    // Space O(n)
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
        return -1;
    }
}