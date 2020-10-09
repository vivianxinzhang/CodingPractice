package com.company;

public class BinaryTreeLongestConsecutiveSequence {
    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence s = new BinaryTreeLongestConsecutiveSequence();
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.right = three;
        three.left = two;
        three.right = four;
        four.right = five;
        System.out.println(s.longestConsecutive(one));
    }

    // Time O(n)
    // Space O(height)
    public int longestConsecutive(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        longestConsecutive(root, max);
        return max[0];
    }

    private int longestConsecutive(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = longestConsecutive(root.left, max);
        int right = longestConsecutive(root.right, max);
        if (left == 0 && right == 0) {
            max[0] = Math.max(1, max[0]);
            return 1;
        } else if (left == 0) {
            if (root.key == root.right.key - 1) {
                max[0] = Math.max(right + 1, max[0]);
            }
            return root.key == root.right.key - 1 ? right + 1 : 1;
        } else if (right == 0) {
            if (root.key == root.left.key - 1) {
                max[0] = Math.max(left + 1, max[0]);
            }
            return root.key == root.left.key - 1 ? left + 1 : 1;
        } else {
            int leftRes = 1;
            if (root.key == root.left.key - 1) {
                leftRes = left + 1;
                max[0] = Math.max(left + 1, max[0]);
            }
            int rightRes = 1;
            if (root.key == root.right.key - 1) {
                rightRes = right + 1;
                max[0] = Math.max(right + 1, max[0]);
            }
            return Math.max(leftRes, rightRes);
        }
    }
}
