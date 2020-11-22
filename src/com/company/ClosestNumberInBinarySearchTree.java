package com.company;

public class ClosestNumberInBinarySearchTree {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder rec = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = rec.reconstruct(level);

        ClosestNumberInBinarySearchTree s = new ClosestNumberInBinarySearchTree();
        System.out.println(s.closest(root, 10));
    }

    // Assumptions: the given binary search tree is not null
    // Time O(h) worst case O(n)
    // Space O(1)
    public int closest(TreeNode root, int target) {
        int result = root.key;
        // The closest number has to be on the path of finding
        // the target value in the binary search tree
        while (root != null) {
            if (root.key == target) {
                return root.key;
            } else {
                if (Math.abs(root.key - target) < Math.abs(result - target)) {
                    result = root.key;
                }
            }
            if (root.key > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return result;
    }
}
