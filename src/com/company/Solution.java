package com.company;
import java.util.*;

// Time average O(nlogn) worst case O(n^2)
// Space average O(logn) worst case O(n)

// Method 2: iteration
// Time O(n) Better answser O(height)
// Space O(h) worst case O(n)
public class Solution {
    public TreeNode search(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.key == key) {
                return root;
            } else if (root.key > key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }
}


