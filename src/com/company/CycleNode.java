package com.company;

public class CycleNode {
    public ListNode cycleNode(ListNode head) {
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
