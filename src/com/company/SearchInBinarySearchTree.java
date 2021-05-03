package com.company;

public class SearchInBinarySearchTree {
    // Recursion:

    // Iteration:
    // Assumptions: There are no duplicate keys in the binary search tree
    // Time O(h)
    // Space O(h)
    public TreeNode search(TreeNode root, int key) {
        while (root != null && root.key != key) {
            if (key < root.key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }
}
