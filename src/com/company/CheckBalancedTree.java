package com.company;

public class CheckBalancedTree {
    public static void main(String[] args) {
        CheckBalancedTree s = new CheckBalancedTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        System.out.println(s.isBalanced(one));  // false
    }

    // Method 2:
    // Time O(n^2)
    // Space O(height) worst O(n) average O(logn)
    public boolean isBalanced(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        return unbalandedOrHeight(root) >= 0;
    }

    private int unbalandedOrHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftResult = unbalandedOrHeight(root.left);
        int rightResult = unbalandedOrHeight(root.right);
        if (leftResult == -1 || rightResult == -1 || Math.abs(leftResult - rightResult) > 1) {
            return -1;
        }
        return Math.max(leftResult, rightResult) + 1;
    }

    // Method 1:
    // Height = log(n) levels
    // Each level call getHeight: Time = O(n)
    // Thus, total time = n + n/2 * 2 + n/4 * 4 + ... = O(nlogn)
    // Time O(nlogn)
    // Space O(h) worst case O(n)
    public boolean isBalancedI(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1){
            return false;
        }
        return isBalancedI(root.left) && isBalancedI(root.right);
    }

    public int findHeight(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}