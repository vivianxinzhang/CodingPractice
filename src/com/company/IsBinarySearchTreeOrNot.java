package com.company;

public class IsBinarySearchTreeOrNot {
    public static void main(String[] args) {
        IsBinarySearchTreeOrNot s = new IsBinarySearchTreeOrNot();
        System.out.println(3 < Integer.MIN_VALUE);
    }


    // Assumptions:
    // assume the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
    // Time O(n)
    // Space O(height) average O(logn) worst O(n)
    public boolean isBST(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
    }

    // If Integer.MIN_VALUE or Integer.MAX_VALUE are also possible to be key values
    // Time O(n)
    // Space O(height) average O(logn) worst O(n)
    public boolean isBSTI(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        return isBSTI(root, null, null);
    }

    private boolean isBSTI(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.key <= min || max != null && root.key >= max) {
            return false;
        }
        return isBSTI(root.left, min, root.key) && isBSTI(root.right, root.key, max);
    }
}
