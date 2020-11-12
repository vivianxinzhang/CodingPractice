package com.company;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PreOrderIterator implements Iterator<TreeNode> {
    Deque<TreeNode> stack;
    public PreOrderIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        if (root != null) {
            stack.offerFirst(root);
        }
    }

    @Override
    public TreeNode next() {
        if (hasNext()) {
            TreeNode cur = stack.pollFirst();
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
            return cur;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
