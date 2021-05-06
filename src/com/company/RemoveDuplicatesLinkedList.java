package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicatesLinkedList {
    public static void main(String[] args) {
        RemoveDuplicatesLinkedList s = new RemoveDuplicatesLinkedList();
        ListNode one = new ListNode(1);
        ListNode one1 = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode three2 = new ListNode(3);
        one.next = one1;
        one1.next = two;
        two.next = three;
        three.next = three2;
        // Input List:  1 -> 1 -> 2 -> 3 -> 3
        // Output List: 2
        ListNode curr = s.removeDup(one);
        Printer.printLinkedList(curr);
    }

    // Time O(n)
    // Space O(1)
    public ListNode removeDupII(ListNode head) {
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
                prev = prev.next;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    // Time O(n)
    // Space O(1)
    public ListNode removeDupI(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            if (next.value == head.value) {
                while (next != null && next.value == head.value) {
                    next = next.next;
                }
                pre.next = next;
            } else {
                pre = head;
            }
            head = next;
        }
        return dummy.next;
    }
}
