package com.company;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
