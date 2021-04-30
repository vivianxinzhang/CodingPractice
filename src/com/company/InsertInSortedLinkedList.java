package com.company;

public class InsertInSortedLinkedList {
    public ListNode insert(ListNode head, int value) {
        if (head == null || value <= head.value) {
            ListNode newHead = new ListNode(value);
            newHead.next = head;
            return newHead;
        }
        ListNode pre = head;
        while (pre.next != null && pre.next.value < value) {
            pre = pre.next;
        }
        ListNode node = new ListNode(value);
        node.next = pre.next;
        pre.next = node;
        return head;
    }
}
