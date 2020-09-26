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
        // 1. determine if the left subtree should be traversed, only when
        // root.key > min, we should traverse the left subtree
        if (root.key > min) {
            getRange(root.left, result, min, max);
        }
        // 2. determine if root should be traversed
        if (root.key >= min && root.key <= max) {
            result.add(root.key);
        }
        // 3. determine if the right subtree should be traversed, only when
        // root.key < max, we should traverse the right subtree
        if (root.key < max) {
            getRange(root.right, result, min, max);
        }
    }
}
