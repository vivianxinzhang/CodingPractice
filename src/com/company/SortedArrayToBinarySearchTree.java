package com.company;

public class SortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        SortedArrayToBinarySearchTree s = new SortedArrayToBinarySearchTree();
        int[] nums = new int[] {1, 3, 4, 5, 8, 11};
        TreeNode root = s.sortedArrayToBST(nums);
        System.out.println(root.key);
    }

    // Time O(n)
    // Space O(1)
    public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] num, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(num[left]);
        }
        int mid = (right - left + 1) % 2 == 0 ? left + (right - left) / 2 + 1 : left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, left, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, right);
        return root;
    }
}
