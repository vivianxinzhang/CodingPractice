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
//        TreeNode  = new TreeNode(1);
//        TreeNode left= new TreeNode(3);
//        TreeNode right = new TreeNode(2);
//        TreeNode left= new TreeNode(5);
//        TreeNode right = new TreeNode(4);
//        TreeNode left= new TreeNode(7);
//        TreeNode right = new TreeNode(6);
//        root.left = left;
//        root.right = right;
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode two2 = new ListNode(2);
        ListNode one2 = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = two2;
//        two2.next = one2;
//        ListNode one = new ListNode(1);
        int[] array = new int[] {0, 2, 1, 4};
        ListNode curr = null;
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }
}