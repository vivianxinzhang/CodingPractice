package com.company;
import java.util.*;

public class LowestCommonAncestorVI {
    public static void main(String[] args) {
        LowestCommonAncestorVI s = new LowestCommonAncestorVI();
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
        List<KnaryTreeNode> nodes = new ArrayList<>();
        nodes.add(one);
        nodes.add(four);
        nodes.add(eleven);
        System.out.println(s.lowestCommonAncestor(five, nodes).key);    // 5

    }
    static class KnaryTreeNode {
        int key;
        List<KnaryTreeNode> children;
        public KnaryTreeNode(int key) {
            this.key = key;
            this.children = new ArrayList<>();
        }
    }

    // Assumptions: the list of nodes is not null or not empty,
    // all the nodes in the list are guaranteed to be in the tree
    // Time O(n)
    // Space O(height) worst case O(n)
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return helper(root, set);
    }

    private KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = helper(child, set);
            if (node == null) {
                continue;
            }
            if (found == null) {
                found = node;
            } else {
                return root;
            }
        }
        return found;
    }
}


