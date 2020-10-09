package com.company;

public class LargestBSTSubtree {
    public static void main(String[] args) {
        LargestBSTSubtree s = new LargestBSTSubtree();
        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        TreeNode one = new TreeNode(1);
        TreeNode eight = new TreeNode(8);
        TreeNode seven = new TreeNode(7);
        ten.left = five;
        ten.right = fifteen;
        five.left = one;
        five.right = eight;
        fifteen.right = seven;
        System.out.println(s.largestBSTSubtree(ten));
    }

    // Time O(n)
    // Space O(height)
    public int largestBSTSubtree(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }
        int[] max = new int[1];
        largestBSTSubtree(root, max, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return max[0];
    }

    // if not BST return -1, if is BST return size
    // update max if current tree is BST
    private int largestBSTSubtree(TreeNode root, int[] max, int minValue, int maxValue) {
        if (root == null) {
            return 0;
        }
        int leftRes = largestBSTSubtree(root.left, max, minValue, root.key);
        int rightRes = largestBSTSubtree(root.right, max, root.key, maxValue);
        if (root.key < minValue || root.key > maxValue) {
            return -1;
        }
        if (leftRes == -1 || rightRes == -1) {
            return -1;
        } else {
            max[0] = Math.max(leftRes + rightRes + 1, max[0]);
            return leftRes + rightRes + 1;
        }
    }
}
