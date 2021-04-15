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
    // Space O(h) worst case O(n)
    public List<Integer> diagonalSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, 0, list);
        return list;
    }

    private void dfs(TreeNode root, int diagonalIndex, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (diagonalIndex > list.size() - 1) {
            list.add(root.key);
        }
        list.set(diagonalIndex, list.get(diagonalIndex) + root.key);
        dfs(root.left, diagonalIndex + 1, list);
        dfs(root.right, diagonalIndex, list);
    }
}
