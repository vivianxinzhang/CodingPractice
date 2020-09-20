package com.company;

public class ReorderList {
    public ListNode reorder(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode one = head, two = middle.next;
        middle.next = null;
        ListNode reverseTwo = reverse(two);
        return merge(one, reverseTwo);
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode curr1 = one, curr2 = two;
        while (curr1 != null && curr2 != null) {
            curr.next = curr1;
            curr1 = curr1.next;
            curr = curr.next;
            curr.next = curr2;
            curr2 = curr2.next;
            curr = curr.next;
        }
        if (curr1 != null) {
            curr.next = curr1;
        }
        if (curr2 != null) {
            curr.next = curr2;
        }
        return dummy.next;
    }
}
