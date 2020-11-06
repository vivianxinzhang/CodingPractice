package com.company;

public class ReconstructBSTWithPreorder {
    public static void main(String[] args) {
        ReconstructBSTWithPreorder s = new ReconstructBSTWithPreorder();
        int[] pre = new int[] {};
        System.out.println(s.reconstruct(pre));

        pre = new int[] {5, 3, 1, 4, 8, 11};
        TreeNode root = s.reconstruct(pre);
        System.out.println(s.reconstruct(pre));
    }

    // Time O(nlogn)
    // Space O(height)
    public TreeNode reconstruct(int[] pre) {
        // Write your solution here
        if (pre == null || pre.length == 0) {
            return null;
        }
        return reconstruct(pre, 0, pre.length - 1);
    }

    private TreeNode reconstruct(int[] pre, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(pre[start]);
        int smallestLargerIdx = start + 1;
        while (smallestLargerIdx <= end && pre[smallestLargerIdx] < pre[start]) {
            smallestLargerIdx++;
        }
        root.left = reconstruct(pre, start + 1, smallestLargerIdx - 1);
        root.right = reconstruct(pre, smallestLargerIdx, end);
        return root;
    }
}
