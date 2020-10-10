package com.company;
import java.util.*;

public class DiagonalSumofBinaryTree {
    public static void main(String[] args) {
        DiagonalSumofBinaryTree s = new DiagonalSumofBinaryTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode one2 = new TreeNode(1);
        TreeNode one3 = new TreeNode(1);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        five.left = one2;
        six.right = one3;
        System.out.println(s.diagonalSum(one));
    }

    // Time O(n)
    // Space O(h)
    public List<Integer> diagonalSum(TreeNode root) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, result, 0);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result, int diaLevel) {
        if (root == null) {
            return;
        }
        if (diaLevel >= result.size()) {
            result.add(root.key);
        } else {
            result.set(diaLevel, result.get(diaLevel) + root.key);
        }
        if (root.left != null) {
            dfs(root.left, result, diaLevel + 1);
        }
        if (root.right != null) {
            dfs(root.right, result, diaLevel);
        }
    }
}
