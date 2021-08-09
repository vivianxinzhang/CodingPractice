package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostOrderTraversalOfBinaryTree {
    // stack maintain the nodes that will be visited in the future
    // top of stack is the next node that we may visit
    // Time O(n)
    // Space O(height) worst case O(n)
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            if (pre == null || cur == pre.left || cur == pre.right) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    stack.pollFirst();
                }
            } else if (pre == cur.right || (pre == cur.left && cur.right == null)) {
                res.add(cur.key);
                stack.pollFirst();
            } else {
                stack.offerFirst(cur.right);
            }
            pre = cur;
        }
        return res;
    }
}
