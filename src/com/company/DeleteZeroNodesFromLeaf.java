package com.company;

public class DeleteZeroNodesFromLeaf {
    public static void main(String[] args) {
        DeleteZeroNodesFromLeaf s = new DeleteZeroNodesFromLeaf();
        TreeNode zero1 = new TreeNode(0);
        TreeNode zero2 = new TreeNode(0);
        TreeNode zero3 = new TreeNode(0);
        TreeNode zero4 = new TreeNode(0);
        TreeNode three = new TreeNode(3);
        TreeNode seven = new TreeNode(7);
        zero1.left = zero2;
        zero1.right = three;
        zero2.left = zero3;
        zero3.left = zero4;
        three.right = seven;
        TreeNode root = s.deleteZero(zero1);
        System.out.println(root.key);
    }

    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode deleteZero(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        root.left = deleteZero(root.left);
        root.right = deleteZero(root.right);
        if (root.left == null && root.right == null && root.key == 0) {
            return null;
        }
        return root;
    }
}
