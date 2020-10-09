package com.company;

public class ConvertBinaryTreeToDoublyLinkedList {
    public static void main(String[] args) {
        ConvertBinaryTreeToDoublyLinkedList s = new ConvertBinaryTreeToDoublyLinkedList();
//        TreeNode ten = new TreeNode(10);
//        TreeNode five = new TreeNode(5);
//        TreeNode fifteen = new TreeNode(15);
//        TreeNode two = new TreeNode(2);
//        ten.left = five;
//        ten.right = fifteen;
//        five.left = two;
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        TreeNode result = s.toDoubleLinkedList(one);
        TreeNode curr = result;
        while (curr != null) {
            System.out.println(curr.key);
            curr = curr.left;
        }
        curr = result;
        while (curr != null) {
            System.out.println(curr.key);
            curr = curr.right;
        }
    }
    // Time O(nlogn) best case O(n)
    // Space O(height) average O(logn) worst case O(n)
    public TreeNode toDoubleLinkedList(TreeNode root) {
        // Write your solution here.
        if (root == null) {
            return null;
        }
        TreeNode newRoot = helper(root);
        return findStart(newRoot);
    }

    public TreeNode helper(TreeNode root) {
        // Write your solution here.
        if (root == null) {
            return null;
        }
        TreeNode leftRoot = helper(root.left);
        TreeNode rightRoot = helper(root.right);
        TreeNode rightMostNode = findEnd(leftRoot);
        TreeNode leftMostNode = findStart(rightRoot);
        root.left = rightMostNode;
        if (rightMostNode != null) {
            rightMostNode.right = root;
        }
        root.right = leftMostNode;
        if (leftMostNode != null) {
            leftMostNode.left = root;
        }
        return root;
    }

    private TreeNode findStart(TreeNode root) {
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    private TreeNode findEnd(TreeNode root) {
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root;
    }

}
