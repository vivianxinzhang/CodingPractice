package com.company;

public class CheckBST {
    // Time O(n)
    // Space O(h) worst case O(n)
    public boolean isBST(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        return isBST(Integer.MIN_VALUE, root, Integer.MAX_VALUE);
    }

    private boolean isBST(int min, TreeNode root, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return isBST(min, root.left, root.key) && isBST(root.key, root.right, max);
    }
}
