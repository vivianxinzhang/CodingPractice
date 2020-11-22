package com.company;

public class ClosestNumberInBinarySearchTree {
    // Time O(h) worst case O(n)
    // Space O(h) worst case O(n)
    public int closest(TreeNode root, int target) {
        int result = root.key;
        while (root != null) {
            if (root.key == target) {
                return root.key;
            } else {
                if (Math.abs(root.key - target) < Math.abs(result - target)) {
                    result = root.key;
                }
            }
            if (root.key > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return result;
    }
}
