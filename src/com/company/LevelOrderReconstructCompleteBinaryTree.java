package com.company;

import java.util.*;

public class LevelOrderReconstructCompleteBinaryTree {
    public static void main(String[] args) {
        LevelOrderReconstructCompleteBinaryTree s = new LevelOrderReconstructCompleteBinaryTree();
        int[] level = new int[]{};
        TreeNode root = s.construct(level);

        level = new int[]{1, 2};
        TreeNode root1 = s.construct(level);

        level = new int[]{1, 2, 3, 4, 5};
        TreeNode root2 = s.construct(level);
    }

    // Method 1: bfs
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode construct(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(level[0]);
        int index = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (index < level.length) {
            TreeNode cur = queue.poll();
            cur.left = new TreeNode(level[index++]);
            queue.offer(cur.left);
            if (index < level.length) {
                cur.right = new TreeNode(level[index++]);
                queue.offer(cur.right);
            }
        }
        return root;
    }

    // Method 1: recursion
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode constructI(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        return construct(level, 0);
    }

    private TreeNode construct(int[] level, int index) {
        if (index >= level.length) {
            return null;
        }
        TreeNode root = new TreeNode(level[index]);
        root.left = construct(level, 2 * index + 1);
        root.right = construct(level, 2 * index + 2);
        return root;
    }
}
