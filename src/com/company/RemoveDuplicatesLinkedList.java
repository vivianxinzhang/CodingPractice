package com.company;

public class RemoveDuplicatesLinkedList {
    // Remove Extra Duplicates from Sorted List
    public ListNode removeDupII(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null && curr.next != null) {
            if (curr.value == curr.next.value) {
                int dup = curr.value;
                while (curr != null && curr.value == dup) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                curr = curr.next;
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    // Remove Duplicates from Sorted List
    public ListNode removeDupI(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.value == prev.value) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}
