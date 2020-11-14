package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][] {{}, {}, {}};
        System.out.println(s.spiral(matrix));

        matrix = new int[][] {{1}, {2}, {3}};
        System.out.println(s.spiral(matrix));

        matrix = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(s.spiral(matrix));
        System.out.println();
    }

    // Time O(n)
    // Space O(1)
    public TreeNode reverse(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = reverse(root.left);
        TreeNode leftChild = root.left;
        leftChild.left = root;
        leftChild.right = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}