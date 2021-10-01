package com.company;

import java.util.*;

public class BorderViewOfBinaryTree {
    public static void main(String[] args) {
        BorderViewOfBinaryTree s = new BorderViewOfBinaryTree();

//        TreeNode two = new TreeNode(2);
//        TreeNode one = new TreeNode(1);
//        TreeNode three = new TreeNode(3);
//        TreeNode four = new TreeNode(4);
//        two.left = one;
//        two.right = three;
//        one.right = four;
//        /**
//         *               2
//         *             /   \
//         *          1       3
//         *           \
//         *            4
//         * */
//        System.out.println(s.borderView(two));
//        // [2, 1, 4, 3]

        TreeNode one1 = new TreeNode(1);
        TreeNode two1 = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        TreeNode four1 = new TreeNode(4);
        TreeNode five1 = new TreeNode(5);
        TreeNode six1 = new TreeNode(6);
        one1.right = two1;
        two1.left = three1;
        two1.right = four1;
        three1.left = five1;
        three1.right = six1;
        /**
         *               1
         *                 \
         *                  2
         *                /   \
         *               3     4
         *            /    \
         *           5     6
         * */
        System.out.println(s.borderView(one1));
        // [1, 5, 6, 4, 2]
        // [1, 2, 3, 5, 6, 4, 2]
    }

    // Time O(n)
    // Space O(h)
    public List<Integer> borderView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.key);
        if (root.left == null && root.right == null) {
            return result;
        }

        List<Integer> leftView = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();
        List<Integer> leafView = new ArrayList<>();

        getLeftView(root.left, leftView);
        getRightView(root.right, rightView);
        Collections.reverse(rightView);
        getLeafNodes(root, leafView);

        result.addAll(leftView);
        result.addAll(leafView);
        result.addAll(rightView);

        return result;
    }

    private void getLeftView(TreeNode root, List<Integer> leftView) {
        if (root == null) {
            return;
        }
        if (root.left != null || root.right != null) {
            leftView.add(root.key);
        }
        if (root.left != null) {
            getLeftView(root.left, leftView);
        } else {
            getLeftView(root.right, leftView);
        }
    }

    private void getRightView(TreeNode root, List<Integer> rightView) {
        if (root == null) {
            return;
        }
        if (root.left != null || root.right != null) {
            rightView.add(root.key);
        }
        if (root.right != null) {
            getRightView(root.right, rightView);
        } else {
            getRightView(root.left, rightView);
        }
    }

    private void getLeafNodes(TreeNode root, List<Integer> leafView) {
        if (root == null) {
            return;
        }
        getLeafNodes(root.left, leafView);
        if (root.left == null && root.right == null) {
            leafView.add(root.key);
        }
        getLeafNodes(root.right, leafView);
    }

//    // Time O(n)
//    // Space O(height) worst case O(n)
//    public List<Integer> borderView(TreeNode root) {
//        // Write your solution here
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        result.add(root.key);
//        if (root.left == null && root.right == null) {
//            return result;
//        }
//
//        List<Integer> leftView = new ArrayList<>();
//        List<Integer> rightView = new ArrayList<>();
//        List<Integer> leafView = new ArrayList<>();
//
//        getLeftView(root.left, leftView);
//        getRightView(root.right, rightView);
//        Collections.reverse(rightView);
//        getLeafNodes(root, leafView);
//
//        result.addAll(leftView);
//        result.addAll(leafView);
//        result.addAll(rightView);
//
//        return result;
//    }
//
//    private void getLeftView(TreeNode root, List<Integer> leftView) {
//        if (root == null) {
//            return;
//        }
//        if (root.left != null || root.right != null) {
//            leftView.add(root.key);
//        }
//        if (root.left != null) {
//            getLeftView(root.left, leftView);
//        } else {
//            getLeftView(root.right, leftView);
//        }
//    }
//
//    private void getRightView(TreeNode root, List<Integer> rightView) {
//        if (root == null) {
//            return;
//        }
//        if (root.left != null || root.right != null) {
//            rightView.add(root.key);
//        }
//        if (root.right != null) {
//            getRightView(root.right, rightView);
//        } else {
//            getRightView(root.left, rightView);
//        }
//    }
//
//    private void getLeafNodes(TreeNode root, List<Integer> leafView) {
//        if (root == null) {
//            return;
//        }
//        getLeafNodes(root.left, leafView);
//        if (root.left == null && root.right == null) {
//            leafView.add(root.key);
//        }
//        getLeafNodes(root.right, leafView);
//    }
}
