package com.company;

public class SecondMinimumValueBST {
    public static void main(String[] args) {
        SecondMinimumValueBST s = new SecondMinimumValueBST();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.right = five;
        System.out.println(s.secondMinimumBT(one));
    }

    // Depth First Search
    // Time O(n)
    // Space O(h) worst case O(n)
    public int secondMinimumBT(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return -1;
        }
        return helper(root, root.key);
    }

    private int helper(TreeNode root, int min) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int currSecondMin = root.key > min ? root.key : Integer.MAX_VALUE;
        int leftSecondMin = helper(root.left, min);
        int rightSecondMin = helper(root.right, min);
        if (currSecondMin == Integer.MAX_VALUE && leftSecondMin == Integer.MAX_VALUE && rightSecondMin == Integer.MAX_VALUE) {
            return -1;
        } else {
            return Math.min(currSecondMin, Math.min(leftSecondMin, rightSecondMin));
        }
    }
}
