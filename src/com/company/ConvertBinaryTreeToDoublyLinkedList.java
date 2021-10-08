package com.company;

public class ConvertBinaryTreeToDoublyLinkedList {
    public static void main(String[] args) {
        ConvertBinaryTreeToDoublyLinkedList s = new ConvertBinaryTreeToDoublyLinkedList();

        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        TreeNode two = new TreeNode(2);
        ten.left = five;
        ten.right = fifteen;
        five.left = two;
        /**          10
         *         /    \
         *        5      15
         *      /
         *     2
         * */
        TreeNode result = s.toDoubleLinkedList(ten);
        TreeNode left = result;
        while (left.left != null) {
            left = left.left;
        }
        while (left != null) {
            System.out.print(left.key);
            left = left.right;
            if (left != null) {
                System.out.print(" <-> ");
            }
        }
        // 2 <-> 5 <-> 10 <-> 15
    }

    // Method 2：
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode toDoubleLinkedList(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        TreeNode[] pre = new TreeNode[1];
        pre[0] = dummy;
        inOrder(root, pre);
        TreeNode res = dummy.right;
        dummy.right = null;
        if (res != null) {
            res.left = null;
        }
        return res;
    }

    private void inOrder(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return;
        }
        inOrder(root.left, pre);
        root.left = pre[0];
        pre[0].right = root;
        pre[0] = root;
        inOrder(root.right, pre);
    }

    // Method 1：
    // Time O(nlogn) best case O(n)
    // Space O(height) average O(logn) worst case O(n)
    public TreeNode toDoubleLinkedListI(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = helper(root);
        return findStart(newRoot);
    }

    public TreeNode helper(TreeNode root) {
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
