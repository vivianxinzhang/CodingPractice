package com.company;

public class LowestCommonAncestorI {
    // Assumptions:
    // 1. There is no parent pointer for the nodes in the binary tree
    // 2. The given two nodes are guaranteed to be in the binary tree
    // 3. root is not null
    // return:
    // null - there is no one or two in the subtree
    // not null -
    // 1) if there is only one node of one/two in the subtree
    //    just return the one/two itself
    // 2) if both one and two are in the subtree, return the LCA
    //    a) one is two's ancestor, return one
    //    b) two is one's ancestor, return two
    //    c) otherwise, return the lowest node with one and two
    //      in the two different subtrees
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, one, two);
        TreeNode right = lowestCommonAncestor(root.right, one, two);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
