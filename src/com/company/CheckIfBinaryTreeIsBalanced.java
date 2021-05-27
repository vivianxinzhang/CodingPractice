package com.company;

public class CheckIfBinaryTreeIsBalanced {
    public static void main(String[] args) {
        CheckIfBinaryTreeIsBalanced s = new CheckIfBinaryTreeIsBalanced();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        System.out.println();
    }

    // Method 2:
    // Time O(n)
    // Space O(height) worst O(n) average O(logn)
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return unbalandedOrHeight(root) != 0;
    }

    private int unbalandedOrHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRes = unbalandedOrHeight(root.left);
        int rightRes = unbalandedOrHeight(root.right);
        if (leftRes == -1 || rightRes == -1 || Math.abs(leftRes - rightRes) > 1) {
            return -1;
        }
        return Math.max(leftRes, rightRes) + 1;
    }

    // Method 1:
    // 时间复杂度分析：
    // Case 1:
    // 糖葫芦情况: best case time → O(n) space O(n)
    // 对root node调用 一侧高度为0 另一侧高度为n-1 高度差 > 1 返回false
    // 只有一层 没有执行到 isBalanced(root.left) && isBalanced(root.right)
    // Case 2: balanced
    // Height = log(n) levels
    // Each level call getHeight: Time = O(n)
    //                   1
    //                /     \
    //               1        1
    //             /  \     /   \
    //            1    1   1     1
    //            ...
    // Thus, total time = n + n/2 * 2 + n/4 * 4 + ... = O(nlogn)
    // Time O(nlogn)
    // Space O(h) worst case O(n)
    public boolean isBalancedI(TreeNode root) {
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
        if (root == null) {
            return 0;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
