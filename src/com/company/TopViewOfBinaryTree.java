package com.company;

import java.util.*;

public class TopViewOfBinaryTree {
    public static void main(String[] args) {
        TopViewOfBinaryTree s = new TopViewOfBinaryTree();
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode seven = new TreeNode(7);
        TreeNode nine = new TreeNode(9);
        four.left = two;
        two.left = one;
        two.right = three;
        three.left = seven;
        three.right = nine;
        System.out.println(s.topView(four));
    }

    // Time O(n)
    // Space O(n)
    public List<Integer> topView(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        result.add(root.key);
        map.put(root, 0);
        int leftMostCol = 0;
        int rightMostCol = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int currCol = map.get(curr);
            if (currCol < leftMostCol) {
                leftMostCol = currCol;
                result.add(0, curr.key);
            }
            if (currCol > rightMostCol) {
                rightMostCol = currCol;
                result.add(curr.key);
            }
            if (curr.left != null) {
                queue.offer(curr.left);
                map.put(curr.left, currCol - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                map.put(curr.right, currCol + 1);
            }
        }
        return result;
    }
}
