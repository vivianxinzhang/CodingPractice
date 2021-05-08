package com.company;

public class CheckIfLinkedListHasACycle {
    // Assumption:
    // You can assume there is no duplicate value appear in the linked list.
    // Time O(n)
    // Space O(1)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
