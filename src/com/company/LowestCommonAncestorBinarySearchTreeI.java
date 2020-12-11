package com.company;

public class LowestCommonAncestorBinarySearchTreeI {
    public static void main(String[] args) {
        LowestCommonAncestorBinarySearchTreeI s = new LowestCommonAncestorBinarySearchTreeI();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode eleven = new TreeNode(11);
        /*
                      5
                    /    \
                   3      8
                 /   \     \
                1    4      11
        **/
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        System.out.println(s.lca(five, 1, 4).key);
        System.out.println(s.lcaI(five, 1, 4).key);
    }

    // Assumptions:
    // 1. There is no parent pointer for the nodes in the binary search tree
    // 2. There are no duplicate keys in the binary search tree
    // Time O(height) worst case O(n)
    // Space O(1)
    public TreeNode lca(TreeNode root, int p, int q) {
        int small = Math.min(p, q);
        int large = Math.max(p, q);
        while (root != null) {
            if (root.key < small) {
                root = root.right;
            } else if (root.key > large) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    // Return:
    // null - there is no one or two in the subtree
    // not null -
    // 1) if there is only one node of one/two in the subtree
    // just return the one/two itself
    // 2) if both one and two are in the subtree, return the LCA
    // a) one is two's ancestor, return one
    // b) two is one's ancestor, return two
    // c) otherwise, return the lowest node with one and two in the two different subtrees
    // Time O(n)
    // Space O(h) worst case O(n)
    public TreeNode lcaI(TreeNode root, int p, int q) {
        // Write your solution here
        if (root == null || root.key == p || root.key == q) {
            return root;
        }
        TreeNode leftLCA = lcaI(root.left, p, q);
        TreeNode rightLCA = lcaI(root.right, p, q);
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        return leftLCA == null ? rightLCA : leftLCA;
    }
}
