package com.company;

public class LargestNumberSmallerInBinarySearchTree {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder rec = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = rec.reconstruct(level);

        LargestNumberSmallerInBinarySearchTree s = new LargestNumberSmallerInBinarySearchTree();
        System.out.println(s.largestSmaller(root, 10));
    }

    // Assumptions: the given binary search tree is not null
    // Time O(h) worst case O(n)
    // Space O(1)
    public int largestSmaller(TreeNode root, int target) {
        int result = Integer.MIN_VALUE;
        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else {
                // the candidates are all the nodes on the path of
                // searching for target, which is smaller than target
                // and notice that, the later searched node has a larger
                // value than the earlier searched ones
                result = root.key;
                root = root.right;
            }
        }
        return result;
    }
}