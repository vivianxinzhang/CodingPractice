package com.company;

public class ReverseBinaryTreeUpsideDown {
    public static void main(String[] args) {
        ReverseBinaryTreeUpsideDown s = new ReverseBinaryTreeUpsideDown();

        TreeNode root = s.reverse(null);

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = five;
        /**         1
         *        /   \
         *       2     5
         * */
        root = s.reverse(one);
        /**         2
         *        /   \
         *       1     5
         * */
    }

    // Method 1: Recursion
    // Time O(n)
    // Space O(n)
    public TreeNode reverse(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = reverse(root.left);
        TreeNode leftChild = root.left;
        leftChild.left = root;
        leftChild.right = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // Method 2: iterative
    // Time O(n)
    // Space O(1)
    public TreeNode reverseI(TreeNode root) {
        TreeNode prev = null;
        TreeNode prevRight = null;
        while (root != null) {
            TreeNode next = root.left;
            TreeNode right = root.right;
            root.right = prevRight;
            root.left = prev;
            prevRight = right;
            prev = root;
            root = next;
        }
        return prev;
    }
}
