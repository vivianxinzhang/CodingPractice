package com.company;

public class ReverseLinkedList {
    public ListNode reverse(ListNode head) {
        // the main logic can handle corner case
        // so we do not need to add corner case check here
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public ListNode reverseI(ListNode head) {
        // be careful about the base case,
        // need to make sure the later head.next.next != null
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseI(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
