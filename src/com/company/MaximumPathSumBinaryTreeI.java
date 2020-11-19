package com.company;

public class MaximumPathSumBinaryTreeI {
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
        int leftRes = helper(root.left, max);
        int rightRes = helper(root.right, max);
        // find the maximum path sum passing through root
        int currSum = leftRes + rightRes + root.key;
        // can only update globalMax result[0] when both children exist
        if (root.left != null && root.right != null) {
            if (currSum > max[0]) {
                max[0] = currSum;
            }
        }
        // return the maximum root to leaf path sum
        if (root.left == null || root.right == null) {
            return currSum;
        }
        return Math.max(leftRes, rightRes) + root.key;
    }

    private int helperI(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        // find maximum root to left sums in left and right subtrees
        // and store them in leftRes and rightRes
        int leftRes = helperI(root.left, max);
        int rightRes = helperI(root.right, max);
        // can only update globalMax result[0] when both children exist
        if (root.left != null && root.right != null) {
            // find the maximum path sum passing through root
            int currSum = leftRes + rightRes + root.key;
            if (currSum > max[0]) {
                max[0] = currSum;
            }
            return Math.max(leftRes, rightRes) + root.key;
        }
        // return the maximum root to leaf path sum
        return root.left == null ? rightRes + root.key : leftRes + root.key;
    }
}
