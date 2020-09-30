package com.company;
import java.util.*;

// Time O(n)
// Space O(h)
public class Solution {
    public int minDepth(TreeNode root) {
        // Write your solution here
        return findMinDepth(root, 0);
    }

    private int findMinDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        return Math.min(findMinDepth(root.left, depth++), findMinDepth(root.right, depth++));
    }
}
