package com.company;
import java.util.*;


public class PreOrder {
    public static void main(String[] args) {
        PreOrder s = new PreOrder();
        Deque<Integer> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        root.left = three;
        System.out.println(s.preOrder(root));
    }

    public List<Integer> preOrder(TreeNode root) {
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
                stack.offerFirst(root.right);
            }
            if (root.left != null) {
                stack.offerFirst(root.left);
            }
        }
        return result;
    }
    // the left subtree should be traversed before the right subtree,
    // since stack is LIFO, we should push right into the stack first,
    // so for the next step the top element of the stack is the left subtree;
    // Time O(n)
    // Space O(h) worst O(n)
    public List<Integer> preOrderI(TreeNode root) {
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
