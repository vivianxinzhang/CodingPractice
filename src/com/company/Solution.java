package com.company;
import java.util.*;

public class Solution {
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode invertTree(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }
}