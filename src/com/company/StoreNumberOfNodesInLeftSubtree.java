package com.company;

public class StoreNumberOfNodesInLeftSubtree {
    public static void main(String[] args) {
        StoreNumberOfNodesInLeftSubtree s = new StoreNumberOfNodesInLeftSubtree();
        System.out.println();
    }
    // helper function return the total # of nodes in  subtree
    // parent need # of nodes in left subtree

    // return the total # of nodes in subtree to parent
    // Time O(n)
    // Space O(h) worst case O(n)
    public void numNodesLeft(TreeNodeLeft root) {
        helper(root);
    }

    // return the # of nodes in the subtree
    private int helper(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        // leftNum is the # of nodes in subtree of root.left
        int leftRes = helper(root.left);
        // leftNum is the # of nodes in subtree of root.right
        int rightRes = helper(root.right);
        root.numNodesLeft = leftRes;
        // return the total # of nodes in the subtree of root
        return leftRes + rightRes + 1;
    }
}

class TreeNodeLeft {
    public int key;
    public TreeNodeLeft left;
    public TreeNodeLeft right;
    // store the # of nodes in left subtree
    public int numNodesLeft;

    public TreeNodeLeft(int key) {
        this.key = key;
    }
}