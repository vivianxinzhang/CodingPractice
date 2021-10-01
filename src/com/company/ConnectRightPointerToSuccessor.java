package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConnectRightPointerToSuccessor {
    public static void main(String[] args) {
        ConnectRightPointerToSuccessor s = new ConnectRightPointerToSuccessor();

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        two.left = one;
        two.right = three;
        two.left = one;
        /**     2
         *    /   \
         *   1     3
         * */
        s.connect(two);
        System.out.println(one.right.key);
    }

    // Method 1: recursion
    // Time O(n)
    // Space O(height) worst case O(n)
    public void connect(TreeNode root) {
        TreeNode[] pre = new TreeNode[] {null};
        inOrder(root, pre);
    }

    private void inOrder(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return;
        }
        inOrder(root.left, pre);
        if (pre[0] != null && pre[0].right == null) {
            pre[0].right = root;
        }
        pre[0] = root;
        inOrder(root.right, pre);
    }

    // Method 1: stack
    // Time O(n)
    // Space O(height) worst case O(n)
    public void connectI(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            // Connect the node whose right child is NULL to the successor node
            if (pre != null && pre.right == null) {
                pre.right = cur;
            }
            pushLeft(cur.right, stack);
            pre = cur;
        }
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }
    }
}
