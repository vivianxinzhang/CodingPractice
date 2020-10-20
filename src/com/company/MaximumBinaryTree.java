package com.company;
import java.util.*;

public class MaximumBinaryTree {
    public static void main(String[] args) {
        MaximumBinaryTree s = new MaximumBinaryTree();
        int[] nums = new int[] {2,1,6,3};
        TreeNode root = s.constructMaximumBinaryTree(nums);
        System.out.println(root.key);
    }

    // Time O(nlogn)
    // Space O(height) worst case O(n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int maxIdx = findMax(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = constructMaximumBinaryTree(nums, left, maxIdx - 1);
        root.right = constructMaximumBinaryTree(nums, maxIdx + 1, right);
        return root;
    }

    private int findMax(int[] nums, int left, int right) {
        int maxIdx = left;
        for (int i = left; i <= right; i++) {
            maxIdx = nums[i] > nums[maxIdx] ? i : maxIdx;
        }
        return maxIdx;
    }
}
