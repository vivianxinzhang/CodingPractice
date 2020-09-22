package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicatesLinkedList {
    public static void main(String[] args) {
        RemoveDuplicatesLinkedList s = new RemoveDuplicatesLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode three2 = new ListNode(3);
        one.next = two;
        two.next = three;
        three.next = three2;
        ListNode curr = s.removeDup(one);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

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
    public ListNode removeDup(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head, curr = head.next;
        while (prev != null && prev.next != null) {
            curr = prev.next;
            if (curr.value == prev.value) {
                while (curr != null && curr.value == prev.value) {
                    curr = curr.next;
                }
                prev.next = curr;
            }
            prev = curr;
        }
        return head;
    }

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
