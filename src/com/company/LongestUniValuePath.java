package com.company;

public class LongestUniValuePath {
    public static void main(String[] args) {
        LongestUniValuePath s = new LongestUniValuePath();
        TreeNode one = new TreeNode(1);
        System.out.println(s.longestUnivaluePath(one));     // 1

        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        System.out.println(s.longestUnivaluePath(one));     // 1

        TreeNode two1 = new TreeNode(2);
        TreeNode two2 = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        /**
         *           1
         *        /    \
         *       2      3
         *     /   \      \
         *    2    2       3
         * */
        two.left = two1;
        two.right = two2;
        three.right = three1;
        System.out.println(s.longestUnivaluePath(one));     // 2
    }

    // Time O(n)
    // Space O(h)
    public int longestUnivaluePath(TreeNode root) {
        int[] max = new int[1];
        postOrder(root, max);
        return max[0];
    }

    private int postOrder(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftRes = postOrder(root.left, max);
        int rightRes = postOrder(root.right, max);
        int curMax = 1;
        int leftMax = 1;
        int rightMax = 1;
        if (root.left != null && root.key == root.left.key) {
            curMax += leftRes;
            leftMax += leftRes;
        }
        if (root.right != null && root.key == root.right.key) {
            curMax += rightRes;
            rightMax += rightRes;
        }
        max[0] = Math.max(max[0], curMax);
        return Math.max(leftMax, rightMax);
    }
}
