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

    // Recursion
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

    // Time O(n)
    // Space O(n)
    public int maxWidthOfBinaryTreeI(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) {
                    start = map.get(curr);
                }
                if (i == size - 1) {
                    end = map.get(curr);
                }
                if (curr.left != null) {
                    map.put(curr.left, map.get(curr) * 2);
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    map.put(curr.right, map.get(curr) * 2 + 1);
                    queue.offer(curr.right);
                }
            }
            int currWidth = end - start + 1;
            maxWidth = Math.max(maxWidth, currWidth);
        }
        return maxWidth;
    }
}
