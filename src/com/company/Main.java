package com.company;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = {1, 2, 2, 4, 7};
        ListNode zero = null;
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        int[] indices = new int[] {0, 1};
//        while (curr != null) {
//            System.out.println(curr.value);
//            curr = curr.next;
//        }
    }
}
