package com.company;
import java.util.*;

public class PostOrder {
    // Method 1: post-order is the reverse order of pre-order with traversing
    // right subtree before traversing left subtree
    public List<Integer> postOrderI(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            // conduct the result for the special pre-order traversal
            result.add(curr.key);
            // in pre-order the right subtree will be traversed before the
            // left subtree so pushing left child first
            if (curr.left != null) {
                stack.offerFirst(curr.left);
            }
            if (curr.right != null) {
                stack.offerFirst(curr.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
