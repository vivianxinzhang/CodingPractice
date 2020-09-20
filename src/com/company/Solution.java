package com.company;
import java.util.*;

/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int num = 0;
        while (l1 != null && l2 != null) {
            num += l1.value;
            l1 = l1.next;
            num += l2.value;
            l2 = l2.next;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
        }
        while (l1 != null) {
            num += l1.value;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            num += l2.value;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
            l2 = l2.next;
        }
        while (num != 0) {
            curr.next = new ListNode(num % 10);
            num /= 10;
        }
        return dummy.next;
    }
}