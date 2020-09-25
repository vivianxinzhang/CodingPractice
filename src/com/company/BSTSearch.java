package com.company;

public class BSTSearch {
    // Method 1: recursive
    // Time O(n) Better answser O(height)
    // Space O(h) worst case O(n)
    public TreeNode search(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            return root;
        }
        return root.key > key ? search(root.left, key) : search(root.right, key);
    }

    // Method 2: iteration
    // Time O(n)
    // Space O(h) worst case O(n)
    public TreeNode searchI(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.key == key) {
                return root;
            } else if (root.key > key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }
}
