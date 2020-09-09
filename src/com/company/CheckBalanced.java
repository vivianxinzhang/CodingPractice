package com.company;

public class CheckBalanced {
    // Method 2:
    // Time O(nlogn)
    // Space O(h) worst case O(n)
    public boolean isBalanced(TreeNode root) {
        // Write your solution here
        return unbalancedOrHeight(root) >= 0;
    }

    private int unbalancedOrHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = unbalancedOrHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = unbalancedOrHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method 1:
    // Time O(nlogn)
    // Space O(h) worst case O(n)
    public boolean isBalanced1(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1){
            return false;
        }
        return isBalanced1(root.left) && isBalanced1(root.right);
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
