package com.company;

import java.util.*;

public class BinaryTreePaths {
    public static void main(String[] args) {
        BinaryTreePaths s = new BinaryTreePaths();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.right = five;
        /**
         *          1
         *        /   \
         *      2      3
         *       \
         *        5
         * */
        System.out.println(Arrays.toString(s.binaryTreePaths(one)));
        // [1->2->5, 1->3]
    }

    // Time O(n)
    // Space O(h) worst case O(n)
    public String[] binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb, res);
        return res.toArray(new String[0]);
    }

    private void preOrder(TreeNode root, StringBuilder sb, List<String> res) {
        int preLength = sb.length();
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            sb.append(root.key);
            res.add(sb.toString());
            sb.setLength(preLength);
            return;
        }
        sb.append(root.key);
        sb.append("->");
        preOrder(root.left, sb, res);
        preOrder(root.right, sb, res);
        sb.setLength(preLength);
    }


}
