package com.company;
import java.util.*;

public class ReconstructBinarySearchTreeWithLevelOrder {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder s = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{};
        System.out.println(s.reconstruct(level));   // null

        level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = s.reconstruct(level);
        System.out.println(s.reconstructI(level));
        /**
         *           5
         *        /    \
         *      3        8
         *    /   \        \
         *  1      4        11
         * */

        level = new int[]{8, 4, 10, 2, 6, 9, 15, 3, 12, 16};
        root = s.reconstruct(level);
        System.out.println(s.reconstruct(level));
        /**
         *           8
         *        /    \
         *      4        10
         *    /   \     /  \
         *  2      6   9    15
         *   \             /   \
         *    3           12   16
         * */
    }

    // Assumptions:
    // 1. The given sequence is not null
    // 2. There are no duplicate keys in the binary search tree
    // Time average O(nlogn) worst case O(n^2)
    // Space average O(logn) worst case O(n)
    public TreeNode reconstruct(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        List<Integer> levelList = getLevelList(level);
        return helper(levelList);
    }

    private TreeNode helper(List<Integer> levelList) {
        if (levelList.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(levelList.get(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : levelList) {
            if (num < root.key) {
                left.add(num);
            } else if (num > root.key) {
                right.add(num);
            }
        }
        root.left = helper(left);
        root.right = helper(right);
        return root;
    }

    private List<Integer> getLevelList(int[] level) {
        List<Integer> levelList = new ArrayList<>();
        for (int num : level) {
            levelList.add(num);
        }
        return levelList;
    }

    // Time O(n)
    // Space O(n)
    public TreeNode reconstructI(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(level[0]);
        Deque<BSTNode> queue = new ArrayDeque<>();
        queue.offer(new BSTNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        int i = 1;
        while (!queue.isEmpty() && i < level.length) {
            BSTNode parent = queue.poll();
            if (level[i] > parent.min && level[i] < parent.node.key) {
                TreeNode leftChild = new TreeNode(level[i]);
                parent.node.left = leftChild;
                queue.offer(new BSTNode(leftChild, parent.min, parent.node.key));
                i++;
            }
            if (i < level.length && level[i] > parent.node.key && level[i] < parent.max) {
                TreeNode rightChild = new TreeNode(level[i]);
                parent.node.right = rightChild;
                queue.offer(new BSTNode(rightChild, parent.node.key, parent.max));
                i++;
            }
        }
        return root;
    }

    class BSTNode {
        TreeNode node;
        int min;
        int max;

        public BSTNode(TreeNode node, int min, int max) {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }
}
