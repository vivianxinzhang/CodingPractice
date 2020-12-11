package com.company;
import java.util.*;

public class GetKeysInBinaryTreeLayerByLayerZigzagOrder {
    public static void main(String[] args) {
        GetKeysInBinaryTreeLayerByLayerZigzagOrder s = new GetKeysInBinaryTreeLayerByLayerZigzagOrder();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode eleven = new TreeNode(11);
        /*
                      5
                    /    \
                   3      8
                 /   \     \
                1   4      11
        **/
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        System.out.println(s.zigZag(five));   // [5, 3, 8, 11, 4, 1]
    }

    // Time O(n)
    // Space O(n)
    public List<Integer> zigZag(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);
        int layer = 0;
        // layer = 0 means even layer, layer = 1 means odd layer
        while (!deque.isEmpty()) {
            // The size of current level should be extracted at the first place,
            // because the size of the deque is changing all the time.
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (layer == 0) {
                    // at even layer, from right to left.
                    TreeNode tmp = deque.pollLast();
                    result.add(tmp.key);
                    if (tmp.right != null) {
                        deque.offerFirst(tmp.right);
                    }
                    if (tmp.left != null) {
                        deque.offerFirst(tmp.left);
                    }
                } else {
                    // at odd layer, from left to right.
                    TreeNode tmp = deque.pollFirst();
                    result.add(tmp.key);
                    if (tmp.left != null) {
                        deque.offerLast(tmp.left);
                    }
                    if (tmp.right != null) {
                        deque.offerLast(tmp.right);
                    }
                }
            }
            layer = 1 - layer;
        }
        return result;
    }
}
