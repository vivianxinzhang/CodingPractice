package com.company;
import java.util.*;


public class MaxWidthOfBinaryTree {
    public static void main(String[] args) {
        MaxWidthOfBinaryTree s = new MaxWidthOfBinaryTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(22);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.right = five;
        System.out.println(s.maxWidthOfBinaryTree(one));
    }

    // Time O(n)
    // Space O(height) worst case O(n)
    public int maxWidthOfBinaryTree(TreeNode root) {
        List<Integer> startID = new ArrayList<>();
        return dfs(root, 0, 0, startID);
    }

    private int dfs(TreeNode root, int level, int id, List<Integer> startID) {
        if (root == null) {
            return 0;
        }
        // Record the id of the left most node when first time at each level
        // of the tree during an pre-order run.
        if (startID.size() == level) {
            startID.add(id);
        }
        int currWidth = id - startID.get(level) + 1;
        int leftWidth = dfs(root.left, level + 1, 2 * id, startID);
        int rightWidth = dfs(root.right, level + 1, 2 * id + 1, startID);
        return Math.max(currWidth, Math.max(leftWidth, rightWidth));
    }
}
