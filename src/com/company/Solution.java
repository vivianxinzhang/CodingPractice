package com.company;
import java.util.*;

public class Solution {
    public List<Integer> preOrder(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            result.add(curr.key);
            if (curr.right != null) {
                stack.offerFirst(curr.right);
            }
            if (curr.left != null) {
                stack.offerFirst(curr.left);
            }
        }
        return result;
    }
}