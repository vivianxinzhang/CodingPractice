package com.company;

public class MaximumPathSumBinaryTreeI {
    // path from leaf node to leaf node
    // Time O(n) need to traverse all nodes in the tree, each node takes O(1) time
    // Space O(h) worst case O(n)
    public int maxPathSum(TreeNode root) {
        int[] max = new int[] {Integer.MIN_VALUE};
        // use helper function to update globalMax
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        // find maximum root to left sums in left and right subtrees
        // and store them in leftRes and rightRes
        int leftPathSum = helper(root.left, max);
        int rightPathSum = helper(root.right, max);
        // can only update globalMax result[0] when both children exist
        if (root.left != null && root.right != null) {
            // find the maximum path sum passing through root
            max[0] = Math.max(max[0], leftPathSum + rightPathSum + root.key);
            return Math.max(leftPathSum, rightPathSum) + root.key;
        }
        // return the maximum root to leaf path sum
        return root.left == null ? leftPathSum + root.key : rightPathSum + root.key;
    }
}
