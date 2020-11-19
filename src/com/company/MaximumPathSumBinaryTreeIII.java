package com.company;

public class MaximumPathSumBinaryTreeIII {
    // both the starting and ending node of the subpath should be
    // on the same path from root to one of the leaf nodes,
    // and the subpath is allowed to contain only one node
    // Time O(n) need to traverse all nodes in the tree, each node takes O(1) time
    // Space O(h) worst case O(n)
    public int maxPathSum(TreeNode root) {
        // Write your solution here
        // Assumptions: root is not null
        int[] max = new int[] {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];  // global max
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, max);
        int right = helper(root.right, max);
        int res = Math.max(Math.max(left, right), 0) + root.key;
        max[0] = Math.max(max[0], res);
        return res;
    }
}
