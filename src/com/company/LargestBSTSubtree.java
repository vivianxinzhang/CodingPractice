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
        int[] max = new int[] {1};
        largestBSTSubtree(root, max);
        return max[0];
    }

    // if not BST return -1, if is BST return size
    // update max if current tree is BST
    private Value largestBSTSubtree(TreeNode root, int[] max) {
        if (root == null) {
            return new Value(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        } else if (root.left == null && root.right == null) {
            return new Value(1, true, root.key, root.key);
        }
        Value leftRes = largestBSTSubtree(root.left, max);
        Value rightRes = largestBSTSubtree(root.right, max);
        if (leftRes.isBST && rightRes.isBST && root.key > leftRes.max && root.key < rightRes.min) {
            max[0] = Math.max(leftRes.size + rightRes.size + 1, max[0]);
            return new Value(leftRes.size + rightRes.size + 1, true, leftRes.min, rightRes.max);
        } else {
            return new Value(-1, false, -1, -1);
        }
    }

    class Value {
        int size = 0;
        boolean isBST = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        public Value(int size, boolean isBST, int min, int max) {
            this.size = size;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
