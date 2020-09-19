package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreOrder {
    // the left subtree should be traversed before the right subtree,
    // since stack is LIFO, we should push right into the stack first,
    // so for the next step the top element of the stack is the left subtree;
    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            result.add(curr.key);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return result;
    }
}
