package com.company;

public class BinaryTreeDiameter {
    // The diameter is defined as the longest distance from one leaf node to another leaf node.
    // Any path can be written as two arrows (in different directions) from some node,
    // where an arrow is a path that starts at some node and only travels down to child nodes.
    // If we knew the maximum length arrows L, R for each child,
    // then the best path touches L + R + 1 nodes.
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
        // The diameter is defined as the longest distance from one leaf node to another leaf node.
        // The distance is the number of nodes on the path.
        if (root.left != null && root.right != null) {
            int currDiameter = leftHeight + rightHeight + 1;
            maximum[0] = Math.max(maximum[0], currDiameter);
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
