package com.company;

public class DeleteBST {
    public static void main(String[] args) {
        DeleteBST s = new DeleteBST();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        three.left = one;
        one.right = two;
//        one.right = three;
//        two.right = five;
        System.out.println(s.deleteSmallest(three).key);
    }

    // Time O(h) worst O(n)
    // Space O(h) worst O(n)
    public TreeNode deleteTree(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else if (root.right.left == null) {
                TreeNode newRoot = root.right;
                newRoot.left = root.left;
                return newRoot;
            } else {
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }
        if (key < root.key) {
            root.left = deleteTree(root.left, key);
        } else if (key > root.key) {
            root.right = deleteTree(root.right, key);
        }
        return root;
    }

    private TreeNode deleteSmallest(TreeNode root) {
        TreeNode prev = null;
        while (root.left != null) {
            prev = root;
            root = root.left;
        }
        prev.left = root.right;
        return root;
    }

    public TreeNode deleteI(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key == root.key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else if (root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }
        if (key < root.key) {
            root.left = deleteI(root.left, key);
        } else if (key > root.key) {
            root.right = deleteI(root.right, key);
        }
        return root;
    }

    private TreeNode deleteSmallestI(TreeNode root) {
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }
}
