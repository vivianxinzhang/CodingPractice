package com.company;
import java.util.*;
import java.util.concurrent.TransferQueue;


public class PreOrder {
    public static void main(String[] args) {
        PreOrder s = new PreOrder();
        Deque<Integer> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        root.left = three;
    }

    // preorder 注意root打印完后 stack要先压右后压左
    // the left subtree should be traversed before the right subtree,
    // since stack is LIFO, we should push right into the stack first,
    // so for the next step the top element of the stack is the left subtree;
    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> preOrderI(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            result.add(cur.key);
            if (root.right != null) {
                stack.offerFirst(cur.right);
            }
            if (root.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return result;
    }


    public List<Integer> preOrderII(TreeNode root) {
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
                result.add(curr.key);
                if (curr.left != null) {
                    stack.offerFirst(curr.left);
                } else if (curr.right != null) {
                    stack.offerFirst(curr.right);
                } else {
                    stack.pollFirst();
                }
            } else if (curr.left == prev) {
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