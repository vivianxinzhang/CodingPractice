package com.company;

public class MaximumDifferenceBetweenNodeAndAncestor {
    public static void main(String[] args) {
        MaximumDifferenceBetweenNodeAndAncestor s = new MaximumDifferenceBetweenNodeAndAncestor();
        ReconstructBinarySearchTreeWithLevelOrder reconstruct = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = reconstruct.reconstruct(level);
        System.out.println(s.maxAncestorDiff(root));
    }

    // Time O(n)
    // Space O(h) worst case O(n)
    public int maxAncestorDiff(TreeNode root) {
        int[] maxDiff = new int[] {Integer.MIN_VALUE};
        if (root == null) {
            return maxDiff[0];
        }
        helper(root, maxDiff);
        return maxDiff[0];
    }

    private Pair helper(TreeNode root, int[] maxDiff) {
        if (root == null) {
            return null;
        }
        Pair leftRes = helper(root.left, maxDiff);
        Pair rightRes = helper(root.right, maxDiff);
        int currMin;
        int currMax;
        int currMaxdiff;
        if (leftRes == null && rightRes == null) {
            return new Pair(root.key, root.key);
        } else if (leftRes != null && rightRes != null) {
            currMin = Math.min(root.key, Math.min(leftRes.min, rightRes.min));
            currMax = Math.max(root.key, Math.max(leftRes.max, rightRes.max));
            currMaxdiff = Math.max(Math.max(Math.abs(root.key - leftRes.min), Math.abs(root.key - rightRes.min)), Math.max(Math.abs(root.key - leftRes.max), Math.abs(root.key - rightRes.max)));
        } else {
            Pair candidate = leftRes == null ? rightRes : leftRes;
            currMin = Math.min(candidate.min, root.key);
            currMax = Math.max(candidate.max, root.key);
            currMaxdiff = Math.max(Math.abs(root.key - candidate.min), Math.abs(root.key - candidate.max));
        }
        if (currMaxdiff > maxDiff[0]) {
            maxDiff[0] = currMaxdiff;
        }
        return new Pair(currMin, currMax);
    }

    class Pair {
        int min;
        int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
