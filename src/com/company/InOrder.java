package com.company;
import java.util.*;

public class InOrder {
    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> inOrder(TreeNode root) {
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

    public List<Integer> inOrderII(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.key);
            curr = curr.right;
        }
        return result;
    }

    // inorder 注意要一路先走到左边
    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> inOrderI(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        pushLeft(root, stack);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            result.add(curr.key);
            curr = curr.right;
            pushLeft(curr, stack);
        }
        return result;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }
    }

    public List<Integer> inOrderIII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peekFirst();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.offerFirst(curr.left);
                } else if (curr.right != null) {
                    result.add(curr.key);
                    stack.offerFirst(curr.right);
                } else {
                    result.add(curr.key);
                    stack.pollFirst();
                }
            } else if (curr.left == prev) {
                result.add(curr.key);
                if (curr.right != null) {
                    stack.offerFirst(curr.right);
                } else {
                    stack.pollFirst();
                }
            } else { // curr.right = prev
                stack.pollFirst();
            }
            prev = curr;
        }
        return result;
    }
}
