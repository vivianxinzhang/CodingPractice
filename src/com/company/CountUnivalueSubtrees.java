package com.company;

public class CountUnivalueSubtrees {
    // Time O(n)
    // Space O(h) worst case O(n)
    public int countUnivalSubtrees(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }
        int[] count = new int[1];
        isUnivalSubtree(root, count);
        return count[0];
    }

    // input: root of subtree
    // problem: calculate if current subtree is a uni-value subtree
    // increment count if current tree is a uni-value subtree
    // return: return if current subtree is a universal subtree
    private boolean isUnivalSubtree(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        // Step 1: get information from two subtrees
        boolean isLeftUnivalSubtree = isUnivalSubtree(root.left, count);
        boolean isRightUnivalSubtree = isUnivalSubtree(root.right, count);
        // Step 2: calculate current level
        // 1) left subtree / right subtree 是否是 uni-value subtree
        // 2) cur.key ?== cur.left.key && cur.key ?== cur.right.key
        if (isLeftUnivalSubtree && isRightUnivalSubtree
                && (root.left == null || root.key == root.left.key)
                && (root.right == null || root.key == root.right.key)) {
            count[0]++;
            // Step 3: return
            return true;
        }
        // Step 3: return
        return false;
    }
}
