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
        one.next = two;
        ListNode three = new ListNode(3);
        two.next = three;
        ListNode four = new ListNode(4);
        three.next = four;
        ListNode five = new ListNode(5);
        four.next = five;
//        ListNode curr = s.removeNthFromEnd(one, 5);
//        while (curr != null) {
//            System.out.println(curr.value);
//            curr = curr.next;
//        }
    }
}