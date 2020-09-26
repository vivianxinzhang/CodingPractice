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

    // the left subtree should be traversed before the right subtree,
    // since stack is LIFO, we should push right into the stack first,
    // so for the next step the top element of the stack is the left subtree;
    // Time O(n)
    // Space O(h) worst O(n)
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
                stack.offerFirst(cur.right);
            }
            if (root.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return result;
    }
}