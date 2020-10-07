package com.company;

public class BinaryTreePathSumToTarget {
    // Time O(n)
    // Space O(height) worst case O(n)
    public boolean exist(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.key == target) {
            return true;
        }
        int subSum = target - root.key;
        if (root.left != null && exist(root.left, subSum)) {
            return true;
        } else if (root.right != null && exist(root.right, subSum)) {
            return true;
        }
        return false;
    }
}
