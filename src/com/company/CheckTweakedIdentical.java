package com.company;

public class CheckTweakedIdentical {
    public static void main(String[] args) {
        CheckTweakedIdentical s = new CheckTweakedIdentical();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        System.out.println();
    }

    // Time O(n^2) <-- assume the input tree is balanced
    // Space O(height) average O(logn) worst O(n) <-- assume the input tree is balanced
    // Case 1: the input tree is not balanced -- two linked list
    //         n levels, branching factor is 1(--> 1 branch for each node)   Time O(n) Space O(n)
    // Case 2: if input tree is balanced, it has log_2(n) levels, recursion quadral tree also has log_2(n) levels
    //         the recursion tree(4 branches) is different from the input tree(2 branches)
    //         total number of nodes in the quadral tree = 1 + 4 + 4^2 + ... 4^(log_2(n))
    //                                                   = 4^(log_2(n)) = 2^(2log_2(n)) = 2^(log_2(n^2)) = O(n^2)
    // (if the input tree is not balanced, then time complexity <<< O(n^2))
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.key != two.key) {
            return false;
        }
        return isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)
                || isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right);
    }
}