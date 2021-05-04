package com.company;

public class LongestAscendingPathBinaryTree {
    public static void main(String[] args) {
        LongestAscendingPathBinaryTree s = new LongestAscendingPathBinaryTree();
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode zero = new TreeNode(0);
        TreeNode eleven = new TreeNode(11);
        five.left = three;
        five.right = two;
        three.left = one;
        three.right = zero;
        two.right = eleven;
        /**
         *          5
         *        /   \
         *       3     2
         *     /   \    \
         *    1    0    11
         * */
        System.out.println(s.longest(five));    // 2
        System.out.println(s.longestI(five));    // 2
        // [2, 11]
    }

    // Method 2: top down
    // Time O(n)
    // Space O(height) worst case O(n)
    public int longest(TreeNode root) {
        int[] globalMax = new int[] {0};
        preOrder(root, null, 0, globalMax);
        return globalMax[0];
    }

    private void preOrder(TreeNode root, Integer pre, int currMax, int[] globalMax) {
        if (root == null) {
            return;
        }
        if (pre == null || root.key <= pre) {
            currMax = 1;
        } else {
            currMax++;
        }
        globalMax[0] = Math.max(globalMax[0], currMax);
        preOrder(root.left, root.key, currMax, globalMax);
        preOrder(root.right, root.key, currMax, globalMax);
    }

    // Method 1: bottom up
    // Time O(n)
    // Space O(height) worst case O(n)
    public int longestI(TreeNode root) {
        int[] max = new int[1];
        longestPathFromRoot(root, max);
        return max[0];
    }

    // return length of longest ascending path starting from root
    private int longestPathFromRoot(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftRes = longestPathFromRoot(root.left, max);
        int rightRes = longestPathFromRoot(root.right, max);
        int currMax = 1;
        if (root.left != null && root.key < root.left.key) {
            currMax = Math.max(currMax, 1 + leftRes);
        }
        if (root.right != null && root.key < root.right.key) {
            currMax = Math.max(currMax, 1 + rightRes);
        }
        max[0] = Math.max(max[0], currMax);
        return currMax;
    }
}
