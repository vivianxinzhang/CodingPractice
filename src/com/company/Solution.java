package com.company;
import java.util.*;

public class Solution {

    public int depthSum(String nestlists) {
        // Write your solution here
        Deque<Character> stack = new ArrayDeque<>();
        int result = 0;
        int level = 0;
        int i = 0;
        while (i < nestlists.length()) {
            if (nestlists.charAt(i) == '[') {
                level++;
                i++;
            } else if (nestlists.charAt(i) == ']') {
                level--;
                i++;
            } else if (nestlists.charAt(i) == ',') {
                i++;
                continue;
            } else {
                boolean isNegative = false;
                if (nestlists.charAt(i) == '-') {
                    isNegative = true;
                    i++;
                }
                int number = 0;
                while (nestlists.charAt(i) >= '0' && nestlists.charAt(i) <= '9') {
                    number *= 10;
                    number += nestlists.charAt(i) - '0';
                    i++;
                }
                result += isNegative ?  -number * level : number * level;
            }
        }
        return result;
    }

    public boolean isFull(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return false;
        }
        return allNodesHaveTwoOrZeroChildren(root);
    }

    private boolean allNodesHaveTwoOrZeroChildren(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }
        return allNodesHaveTwoOrZeroChildren(root.left) && allNodesHaveTwoOrZeroChildren(root.right);
    }
}
