package com.company;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorIII {
    public static void main(String[] args) {
        LowestCommonAncestorIII s = new LowestCommonAncestorIII();

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
        System.out.println(s.lowestCommonAncestor(five, one, three).key);     // 3
        System.out.println(s.lowestCommonAncestor(five, one, eight).key);     // 5
        System.out.println(s.lowestCommonAncestor(five, one, eleven).key);    // 5

        TreeNode notInTheTree = new TreeNode(100);
        System.out.println(s.lowestCommonAncestor(five, one, notInTheTree));  // null
    }

    // one and two are not necessarily on the tree
    // if return c: then we know that
    //              BOTH    a and b 不直接隶属
    //              AND     both a and b MUST be in the tree
    // if return a or b: (let's say we return a) then
    //              Either  a 是 b 的直接隶属上级
    //              OR      b is not in the tree -- HOW DO WE KNOW?
    //                      we can run an additional findNode(root = a, b) or LCA(root = a, b, b) to check
    // Time O(n)
    // Space O(n)
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        TreeNode result = LCA(root, one, two);
        if (result == one) {
            if (LCA(one, two, two) == null) {
                return null;
            }
        } else if (result == two) {
            if (LCA(two, one, one) == null) {
                return null;
            }
        }
        return result;
    }

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
    private TreeNode LCA(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        } else if (root == one || root == two) {
            return root;
        }
        TreeNode left = LCA(root.left, one, two);
        TreeNode right = LCA(root.right, one, two);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
