package com.company;
import java.util.*;

public class GetRange {
    // Time worst case O(n) Better answer O(height + # of nodes in the range of [k1, k2])
    // Space O(height) worst case O(n)
    // if min < root.key, need to traverse left subtree
    // if min <= root.key <= max, add root value to the result
    // if max > root.key, need to traverse right subtree
    // Time O(n)
    // Space O(height)
    public List<Integer> getRange(TreeNode root, int min, int max) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        getRange(root, result, min, max);
        return result;
    }

    private void getRange(TreeNode root, List<Integer> result, int min, int max) {
        if (root == null) {
            return;
        }
        getRange(root.left, result, min, max);
        if (root.key >= min && root.key <= max) {
            result.add(root.key);
        }
        getRange(root.right, result, min, max);
    }
}
