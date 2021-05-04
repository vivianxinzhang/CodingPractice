package com.company;
import java.util.*;

public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        MaximumWidthOfBinaryTree s = new MaximumWidthOfBinaryTree();
        TreeNode zero = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        zero.left = one;
        zero.right = two;
        TreeNode three = new TreeNode(3);
        one.left = three;
        TreeNode six = new TreeNode(6);
        two.right = six;
        /**
         *           0               h0: 1-1+1 = 1
         *        /     \
         *      1        2           h1: 2-1+1 = 2
         *     / \      /  \
         *    3 null  null  6        h2: 6-3+1 = 4
         * */
        System.out.println(s.maxWidthOfBinaryTree(zero));   // 4

        TreeNode ten0 = new TreeNode(10);
        TreeNode ten1 = new TreeNode(10);
        TreeNode ten2 = new TreeNode(10);
        ten0.left = ten1;
        ten0.right = ten2;
        /**
         *      10
         *    /   \
         *   10   10
         * */
        System.out.println(s.maxWidthOfBinaryTree(ten0));   // 2
    }

    // We can assign an id to each node.
    // root index is i, left child index = 2*i + 1, right child index = 2*i + 2
    // Similar to a heap (complete binary tree)
    // width = max_h -min_h + 1
    // Method 2: dfs
    // Time O(n)
    // Space O(h)
    public int maxWidthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxWidth = new int[] {Integer.MIN_VALUE};
        List<Integer> firstIdx = new ArrayList<>();
        dfs(root, 0, 0, firstIdx, maxWidth);
        return maxWidth[0];
    }

    private void dfs(TreeNode root, int level, int index, List<Integer> firstIdx, int[] maxWidth) {
        if (root == null) {
            return;
        }
        if (level == firstIdx.size()) {
            firstIdx.add(index);
        }
        maxWidth[0] = Math.max(maxWidth[0], index - firstIdx.get(level) + 1);
        dfs(root.left, level + 1, index * 2 + 1, firstIdx, maxWidth);
        dfs(root.right, level + 1, index * 2 + 2, firstIdx, maxWidth);
    }

    // Method 1: bfs
    // Time O(n)
    // Space O(h)
    public int maxWidthOfBinaryTreeI(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int startIdx = 0;
            int endIdx = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) {
                    startIdx = map.get(curr);
                }
                if (i == size - 1) {
                    endIdx = map.get(curr);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                    map.put(curr.left, map.get(curr) * 2 + 1);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    map.put(curr.right, map.get(curr) * 2 + 2);
                }
            }
            int currWidth = endIdx - startIdx + 1;
            maxWidth = Math.max(maxWidth, currWidth);
        }
        return maxWidth;
    }
}
