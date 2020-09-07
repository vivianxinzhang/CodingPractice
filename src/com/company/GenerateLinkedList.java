package com.company;

public class GenerateLinkedList {
    public static ListNode generate(int n) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < n; i += 2) {
            ListNode next = new ListNode(i);
            curr.next = next;
            curr = curr.next;
        }
        return head;
    }
}
