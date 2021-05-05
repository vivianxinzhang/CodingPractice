package com.company;

public class BinaryTreePathSumToTargetI {
    public static void main(String[] args) {
        BinaryTreePathSumToTargetI s = new BinaryTreePathSumToTargetI();
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
        System.out.println(s.exist(one, 5));    // false
        System.out.println(s.exist(one, 10));    // true
    }

    // Time O(n)
    // Space O(h) worst case O(n)
    public boolean exist(TreeNode root, int target) {
        return exist(root, 0, target);
    }

    private boolean exist(TreeNode root, int prefixSum, int target) {
        if (root == null) {
            return false;
        }
        prefixSum += root.key;
        if (root.left == null && root.right == null) {
            if (prefixSum == target) {
                return true;
            } else {
                return false;
            }
        }
        boolean leftRes = exist(root.left, prefixSum, target);
        boolean rightRes = exist(root.right, prefixSum, target);
        return leftRes || rightRes;
    }
}
