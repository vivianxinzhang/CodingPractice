package com.company;

import java.util.*;

public class TopViewOfBinaryTree {
    public static void main(String[] args) {
        TopViewOfBinaryTree s = new TopViewOfBinaryTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        two.right = four;
        three.right = five;
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        four.left = six;
        four.right = seven;
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        seven.right = eight;
        eight.right = nine;
        /**
         *               1
         *             /   \
         *            2     3
         *             \     \
         *               4    5
         *             /  \
         *            6    7
         *                  \
         *                    8
         *                     \
         *                       9
         * */
        System.out.println(s.topView(one));
        // [2, 1, 3, 5, 9]
    }

    // Method 1: bfs
    // need to record col index for each TreeNode and collect res of the first node of each col
    // Time O(n)
    // Space O(n)
    public List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<TreeNode, Integer> colMap = new HashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        result.add(root.key);
        colMap.put(root, 0);
        int leftMostCol = 0;
        int rightMostCol = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int currCol = colMap.get(cur);
            if (currCol < leftMostCol) {
                leftMostCol = currCol;
                result.add(0, cur.key);
            }
            if (currCol > rightMostCol) {
                rightMostCol = currCol;
                result.add(cur.key);
            }
            if (cur.left != null) {
                queue.offer(cur.left);
                colMap.put(cur.left, currCol - 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                colMap.put(cur.right, currCol + 1);
            }
        }
        return result;
    }
}
