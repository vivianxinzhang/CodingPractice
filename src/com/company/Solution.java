package com.company;
import java.util.*;

public class Solution {
    public List<Integer> getRange(TreeNode root, int min, int max) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        getRange(root, min, max, result);
        return result;
    }

    private void getRange(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.key > min) {
            getRange(root.left, min, root.key, result);
        }
        if (root.key >= min && root.key <= max) {
            result.add(root.key);
        }
        if (root.key < max) {
            getRange(root.right, root.key, max, result);
        }
    }
}