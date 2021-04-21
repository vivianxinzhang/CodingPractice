package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InOrderTraversalOfBinaryTree {
    // Corner Cases:
    // What if the given binary tree is null? Return an empty list in this case.
    // Time O(n)
    // Space O(height) worst case O(n)
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            res.add(cur.key);
            pushLeft(cur.right, stack);
        }
        return res;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }
    }

    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> inOrderI(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // always try to go to the left side to see if there is any node
            // should be traversed before the cur node, cur node is stored
            // on stack since it has not been traversed yet
            if (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            } else {
                // if can not go left, meaning all the nodes on teh left side of
                // the top node on stack has been traversed, the next traversing
                // node should be the top node on stack
                curr = stack.pollFirst();
                result.add(curr.key);
                // the next subtree we want to traverse is cur.right
                curr = curr.right;
            }
        }
        return result;
    }
}
