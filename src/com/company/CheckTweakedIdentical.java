package com.company;

public class CheckTweakedIdentical {
    public static void main(String[] args) {
        CheckBalancedTree s = new CheckBalancedTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        System.out.println(s.isBalanced(one));
    }

    // Time O(4^n)
    // Space O(height) worst O(n)
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here
        if (one == null && two == null) {
            return true;
        } else if (one != null || two != null) {
            return false;
        } else if (one.key != two.key) {
            return false;
        }
        return isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)
                || isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right);
    }
}