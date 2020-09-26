package com.company;

public class BSTSearch {
    // Method 1: recursive
    // Time O(n) Better answser O(height)
    // Space O(h) worst case O(n)
    public TreeNode search(TreeNode root, int key) {
        // Write your solution here
        if (root == null || root.key == key) {
            return root;
        }
        return search(root.key > key ? root.left : root.right, key);
    }

    // Method 2: iteration
    // Time O(n) Better answser O(height)
    // Space O(h) worst case O(n)
    public TreeNode searchI(TreeNode root, int key) {
        // Write your solution here
        TreeNode cur = root;
        while (cur != null && cur.key != key) {
            cur = key < cur.key ? cur.left : cur.right;
        }
        return cur;
    }
}
