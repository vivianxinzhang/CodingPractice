package com.company;

public class LowestCommonAncestorI {
    // Assumptions: root is not null, one and two guaranteed to be in the tree and not null
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
    // Space O(n)
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        } else if (root == one || root == two) {
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
