package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class SecondMinimumValueInBinaryTree {
    public static void main(String[] args) {
        SecondMinimumValueInBinaryTree s = new SecondMinimumValueInBinaryTree();
        TreeNode one = new TreeNode(1);
        TreeNode one1 = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        one.left = one1;
        one.right = three;
        TreeNode five = new TreeNode(5);
        TreeNode three1 = new TreeNode(3);
        three.left = five;
        three.right = three1;
        /**
         *      1
         *    /   \
         *   1     3
         *       /   \
         *      5    3
         * */
        System.out.println(s.secondMinimumBT(one));
        // 3

        TreeNode ten0 = new TreeNode(10);
        TreeNode ten1 = new TreeNode(10);
        TreeNode ten2 = new TreeNode(10);
        ten0.left = ten1;
        ten0.right = ten2;
        /**
         *      10
         *    /   \
         *   10   10
         * */
        System.out.println(s.secondMinimumBT(ten0));
        // -1
    }

    // Method 1: find smallest larger than root.key
    // Time O(n)
    // Space O(h)
    public int secondMinimumBTI(TreeNode root) {
        int[] result = new int[] {Integer.MAX_VALUE};
        helper(root, root.key, result);
        return result[0] == Integer.MAX_VALUE ? -1 : result[0];
    }

    private void helper(TreeNode root, int target, int[] result) {
        if (root == null) {
            return;
        }
        if (root.key > target && root.key < result[0]) {
            result[0] = root.key;
        }
        helper(root.left, target, result);
        helper(root.right, target, result);
    }

    // Method 1: find smallest larger than root.key
    // Time O(n)
    // Space O(n)
    public int secondMinimumBT(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if (cur.key > root.key && cur.key < res) {
                res = cur.key;
            }
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
