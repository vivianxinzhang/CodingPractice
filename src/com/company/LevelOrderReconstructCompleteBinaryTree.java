package com.company;

public class LevelOrderReconstructCompleteBinaryTree {
    public static void main(String[] args) {
        System.out.println("main");
    }
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode construct(int[] level) {
        // Write your solution here
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
