package com.company;
import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorV {
    public static void main(String[] args) {
        LowestCommonAncestorV s = new LowestCommonAncestorV();
        KnaryTreeNode five = new KnaryTreeNode(5);
        KnaryTreeNode three = new KnaryTreeNode(3);
        KnaryTreeNode eight = new KnaryTreeNode(8);
        KnaryTreeNode one = new KnaryTreeNode(1);
        KnaryTreeNode four = new KnaryTreeNode(4);
        KnaryTreeNode seven = new KnaryTreeNode(7);
        KnaryTreeNode eleven = new KnaryTreeNode(11);
        five.children.add(three);
        five.children.add(eight);
        three.children.add(one);
        three.children.add(four);
        three.children.add(seven);
        eight.children.add(eleven);
        /*               5
                      /     \
                    3        8
                 /  |  \      \
               1    4   7      11
        * */
        System.out.println(s.lowestCommonAncestor(five, one, seven).key);    // 5
        System.out.println(s.lowestCommonAncestorI(five, one, seven).key);    // 5
    }

    static class KnaryTreeNode {
        int key;
        List<KnaryTreeNode> children;

        public KnaryTreeNode(int key) {
            this.key = key;
            this.children = new ArrayList<>();
        }
    }

    // Assumptions: root is not null,
    // a and b are guaranteed to be in the tree and not null
    // LCA(root, a, b)   in the subtree of root, LCA of a and b
    // 在以 p 为根的子树上, a 和 b 的 LCA
    // returns LCA of all the target nodes that are in the subtree under “root”
    // Time O(n)
    // Space O(height) worst case O(n)
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        // recursive rule
        // r0, r1, r2, ... , r(k-1)
        // Case 1: if all of them are null, return null
        // Case 2: if only one of them is not null, return the non-null
        // Case 3: if more than one of them are not nul, return root
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {     // step 1
            KnaryTreeNode node = lowestCommonAncestor(child, a, b);
            if (node == null) {
                continue;
            }
            if (found == null) {       // step 2
                found = node;
            } else {
                return root;
            }
        }
        return found;   // step 3
    }

    // another implementation
    public KnaryTreeNode lowestCommonAncestorI(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        // recursive rule
        // r0, r1, r2, ... , r(k-1)
        // Case 1: if all of them are null, return null
        // Case 2: if only one of them is not null, return the non-null
        // Case 3: if more than one of them are not nul, return root
        KnaryTreeNode result = null;
        for (KnaryTreeNode child : root.children) {     // step 1
            KnaryTreeNode subResult = lowestCommonAncestor(child, a, b);
            if (subResult != null) {
                if (result != null) {    // step 2
                    return root;
                }
                result = subResult;
            }
        }
        return result;   // step 3
    }
}
