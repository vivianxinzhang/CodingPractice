package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class CousinsInBinaryTree {
    public static void main(String[] args) {
        CousinsInBinaryTree s = new CousinsInBinaryTree();
        TreeNode root = null;
        System.out.println(s.isCousin(root, 1, 2));     // false

        /**          6
         *        /    \
         *       3      5
         *     /  \    /  \
         *    7    8  1   13
         * */
        TreeNode six = new TreeNode(6);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        six.left = three;
        six.right = five;
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode thirteen = new TreeNode(13);
        three.left = seven;
        three.right = eight;
        five.left = one;
        five.right = thirteen;
        System.out.println(s.isCousin(six, 7, 1));     // true
        System.out.println(s.isCousin(six, 3, 5));     // false
        System.out.println(s.isCousin(six, 7, 5));     // false
    }

    // Two nodes are cousins of each other if they are at the same level and have different parents.
    // Assumptions:
    // 1. It is not guaranteed the two keys are all in the binary tree.
    // 2. There are no duplicate keys in the binary tree.
    // Time O(n): worst case all the nodes enqueue once and dequeue once
    // Space O(n): at most n/2 nodes in the queue when traverse the last level
    public boolean isCousin(TreeNode root, int a, int b) {
        if (root == null) {
            return false;
        }
        TreeNode parentA = null;
        TreeNode parentB = null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    if (cur.left.key == a) {
                        parentA = cur;
                    }
                    if (cur.left.key == b) {
                        parentB = cur;
                    }
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    if (cur.right.key == a) {
                        parentA = cur;
                    }
                    if (cur.right.key == b) {
                        parentB = cur;
                    }
                }
            }
            if (parentA != null && parentB != null) {
                return parentA != parentB;
            } else if (parentA != null || parentB != null) {
                return false;
            }
        }
        return false;
    }
}
