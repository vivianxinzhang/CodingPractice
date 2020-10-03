package com.company;

public class CycleNode {
    public ListNode cycleNode(ListNode head) {
        // write your solution here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return findcycleNode(head, slow);
            }
        }
        return null;
    }

    private ListNode findcycleNode(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }

    public ListNode cycleNodeI(ListNode head) {
        // write your solution here
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow .next;
            fast = fast.next.next;
            if (fast == slow) {
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return slow;
            }
        }
        return null;
    }
}
