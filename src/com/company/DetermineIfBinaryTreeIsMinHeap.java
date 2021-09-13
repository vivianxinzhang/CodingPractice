package com.company;

import java.util.*;

public class DetermineIfBinaryTreeIsMinHeap {
    // Time O(n)
    // Space O(n)
    public boolean isMinHeap(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                if (seenNull || cur.left.key < cur.key) {
                    return false;
                }
            } else {
                seenNull = true;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                if (seenNull || cur.right.key < cur.key) {
                    return false;
                }
            } else {
                seenNull = true;
            }
        }
        return true;
    }
}
