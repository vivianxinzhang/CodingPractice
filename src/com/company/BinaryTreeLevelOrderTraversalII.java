package com.company;
import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversalII s = new BinaryTreeLevelOrderTraversalII();
        TreeNode zero = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        zero.left = one;
        zero.right = two;
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        one.left = three;
        one.right = four;
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        two.left = five;
        two.right = six;
        /**
         *           0
         *        /     \
         *      1        2
         *     / \      /  \
         *    3  4     5   6
         * */
        System.out.println(s.levelOrderBottom(zero));   // 4
    }

    // Time O(n)
    // Space O(n)
    public List<Integer> levelOrderBottom(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> currLayer = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                currLayer.add(tmp.key);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            res = currLayer;
        }
        return res;
    }
}
