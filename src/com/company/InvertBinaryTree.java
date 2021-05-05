package com.company;

import java.util.Arrays;

public class InvertBinaryTree {
    public static void main(String[] args) {
        InvertBinaryTree s = new InvertBinaryTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        two.left = four;
        two.right = five;
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        three.left = six;
        three.right = seven;
        /**
         *           1
         *        /    \
         *      2       3
         *    /   \    /  \
         *   4    5   6    7
         * */
        TreeNode root = s.invertTree(one);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
