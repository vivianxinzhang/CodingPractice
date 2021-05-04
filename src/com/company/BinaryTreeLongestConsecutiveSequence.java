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
        /**
         *      1
         *        \
         *         3
         *        /  \
         *       2    4
         *             \
         *              5
         * */
        System.out.println(s.longestConsecutive(one));  // 3
        // [3, 4, 5]
    }

    // Time O(n)
    // Space O(height) worst case O(n)
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[] {1};
        preOrder(root, null, 0, max);
        return max[0];
    }

    private void preOrder(TreeNode root, Integer pre, int curMax, int[] max) {
        if (root == null) {
            return;
        }
        if (pre == null || root.key != pre + 1) {
            curMax = 1;
        } else {
            curMax++;
        }
        max[0] = Math.max(max[0], curMax);
        preOrder(root.left, root.key, curMax, max);
        preOrder(root.right, root.key, curMax, max);
    }
}
