package com.company;
import java.util.*;

// Time O(n)
// Space O(n)
public class Solution {
    public ListNode reverseInPairs(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode next = head.next.next;
            ListNode newHead = head.next;
            newHead.next = head;
            head.next = next;
            prev = head;
            head = next;
        }
        return dummy.next;
    }
}