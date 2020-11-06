package com.company;
import java.util.*;

public class ReconstructBinarySearchTreeWithLevelOrder {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder s = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{};
        System.out.println(s.reconstruct(level));

        level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = s.reconstruct(level);
        System.out.println(s.reconstruct(level));

        level = new int[]{8, 4, 10, 2, 6, 9, 15, 3, 12, 16};
        root = s.reconstruct(level);
        System.out.println(s.reconstruct(level));
    }

    // Time O(n)
    // Space O(n)
    public TreeNode reconstruct(int[] level) {
        // Write your solution here
        if (level == null || level.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(level[0]);
        Queue<BSTNode> queue = new ArrayDeque<>();
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
