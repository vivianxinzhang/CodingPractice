package com.company;

public class BinaryTreeDiameter {
    // Time O(n)
    // Space O(h)
    public int diameter(TreeNode root) {
        // Write your solution here
        int[] maximum = new int[1];
        helper(root, maximum);
        return maximum[0];
    }

    private int helper(TreeNode root, int[] maximum) {
        if (root == null) {
            return 0;
        }
        int leftHeight = helper(root.left, maximum);
        int rightHeight = helper(root.right, maximum);
        if (root.left != null && root.right != null) {
            int currDiameter = leftHeight + rightHeight + 1;
            maximum[0] = Math.max(maximum[0], currDiameter);
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
