package com.company;

public class SortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        SortedArrayToBinarySearchTree s = new SortedArrayToBinarySearchTree();
        int[] nums = new int[] {1, 3, 4, 5, 8, 11};
        TreeNode root = s.sortedArrayToBST(nums);
        System.out.println(root.key);
    }

    // Time O(n)
    // Space O(h) worst case O(n)
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        return helper(num, 0, num.length - 1);
    }

    private TreeNode helper(int[] num, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, left, mid - 1);
        root.right = helper(num, mid + 1, right);
        return root;
    }
}
