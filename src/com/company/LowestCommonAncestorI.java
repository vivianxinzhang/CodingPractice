package com.company;

public class LowestCommonAncestorI {
    public static void main(String[] args) {
        LowestCommonAncestorI s = new LowestCommonAncestorI();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        five.left = three;
        five.right = eight;
        TreeNode one = new TreeNode(1);
        TreeNode seven = new TreeNode(7);
        TreeNode eleven = new TreeNode(11);
        three.left = one;
        three.right = seven;
        eight.right = eleven;
        /*               5
                      /     \
                    3        8
                 /     \      \
               1        7      11
        * */
        System.out.println(s.lowestCommonAncestor(five, one, eleven).key);    // 5
    }

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
