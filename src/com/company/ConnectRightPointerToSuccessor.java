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
        s.connect(one);
    }


    // Time O(n)
    // Space O(height) worst case O(n)
    public void connect(TreeNode root) {
        // Write your solution here.
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            if (prev != null && prev.right == null) {
                prev.right = curr;
            }
            prev = curr;
            curr = curr.right;
            pushLeft(curr, stack);
        }
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }
    }
}
