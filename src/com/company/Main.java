package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Deque<Integer> stack = new ArrayDeque<>();
//        stack.offerFirst(3);
//        stack.offerFirst(5);
//        stack.offerFirst(1);
//        stack.offerFirst(2);
//        stack.offerFirst(8);
        TreeNode root = new TreeNode(1);
        TreeNode left= new TreeNode(11);
        TreeNode right = new TreeNode(12);
        root.left = left;
        root.right = right;
        int[] array = new int[] {1, 5};
        System.out.println(s.getRange(root, 3, 9));
    }
}