package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConnectRightPointerToSuccessor {
    public static void main(String[] args) {
        ConnectRightPointerToSuccessor s = new ConnectRightPointerToSuccessor();
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        three.left = two;
        two.left = one;
        /**     3
         *    /   \
         *   2     1
         * */
        s.connect(one);
    }

    // Time O(n)
    // Space O(height) worst case O(n)
    public void connect(TreeNode root) {
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
