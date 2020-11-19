package com.company;

public class MaxPathSumFromLeafToRoot {
    // Method 1: Pass down the prefix sum
    // Time O(n) need to traverse all nodes in the tree, each node takes O(1) time
    // Space O(h) worst case O(n)
    public int maxPathSumLeafToRootI(TreeNode root) {
        // Assumptions: root != null
        return maxPathSum(root, 0);
    }

    private int maxPathSum(TreeNode root, int sum) {
        sum += root.key;
        if (root.left == null && root.right == null) {
            return sum;
        } else if (root.left == null) {
            return maxPathSum(root.right, sum);
        } else if (root.right == null) {
            return maxPathSum(root.left, sum);
        }
        return Math.max(maxPathSum(root.left, sum), maxPathSum(root.right, sum));
    }

    // Method 2: Bottom up return the max suffix sum
    // Time O(n) need to traverse all nodes in the tree, each node takes O(1) time
    // Space O(h) worst case O(n)
    public int maxPathSumLeafToRoot(TreeNode root) {
        // Assumptions: root != null
        if (root.left == null && root.right == null) {
            return root.key;
        }
        if (root.left == null) {
            return maxPathSumLeafToRoot(root.right) + root.key;
        }
        if (root.right == null) {
            return maxPathSumLeafToRoot(root.left) + root.key;
        }
        return root.key + Math.max(maxPathSumLeafToRoot(root.left), maxPathSumLeafToRoot(root.right));
    }
}
