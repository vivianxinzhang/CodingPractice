package com.company;

public class LongestAscendingPathBinaryTree {
    public static void main(String[] args) {
        LongestAscendingPathBinaryTree s = new LongestAscendingPathBinaryTree();
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode zero = new TreeNode(0);
        TreeNode eleven = new TreeNode(11);
        five.left = three;
        five.right = two;
        three.left = one;
        three.right = zero;
        two.right = eleven;
        System.out.println(s.longest(five));
    }

    // Time O(n)
    // Space O(height) worst case O(n)
    public int longest(TreeNode root) {
        // Write your solution here
        int[] max = new int[1];
        longestPathFromRoot(root, max);
        return max[0];
    }

    // return length of longest ascending path starting from root
    private int longestPathFromRoot(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = longestPathFromRoot(root.left, max);
        int right = longestPathFromRoot(root.right, max);
        if (left == 0 && right == 0) {
            max[0] = Math.max(1, max[0]);
            return 1;
        } else if (left == 0) {
            if (root.key < root.right.key) {
                max[0] = Math.max(right + 1, max[0]);
            }
            return root.key < root.right.key ? right + 1 : 1;
        } else if (right == 0) {
            if (root.key < root.left.key) {
                max[0] = Math.max(left + 1, max[0]);
            }
            return root.key < root.left.key ? left + 1 : 1;
        } else {
            int leftRes = 1;
            if (root.key < root.left.key) {
                leftRes = left + 1;
                max[0] = Math.max(left + 1, max[0]);
            }
            int rightRes = 1;
            if (root.key < root.right.key) {
                rightRes = right + 1;
                max[0] = Math.max(right + 1, max[0]);
            }
            return Math.max(leftRes, rightRes);
        }
    }
}
