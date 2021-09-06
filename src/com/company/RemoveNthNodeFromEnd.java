package com.company;

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        RemoveNthNodeFromEnd s = new RemoveNthNodeFromEnd();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        Printer.printLinkedList(one);
        // input: 1 -> 2 -> 3 -> 4 -> 5
        ListNode res = s.removeNthFromEnd(one, 5);
        Printer.printLinkedList(res);
        // output: 2 -> 3 -> 4 -> 5
    }

    // Assumptions:
    // 1. If n is not valid, you do not need to do anything to the original list.
    // 2. Try to do this in one pass.
    // Examples
    // dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null, and n = 2.
    //                   slow
    //                             fast
    // n nodes between slow and fast points,
    // Step 1: move fast pointer n steps first
    // Step 2: move slow and fast together, when fast.next hits null, slow is the previous node of node that to be deleted
    // Time O(n)
    // Space O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                return head;
            }
        }
        ListNode slow = dummy;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // Examples
    // dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null, and n = 2.
    //                    s               f
    //   s                f
    // n nodes between slow and fast points, fast need to move n + 1 steps first
    // Step 1: move fast pointer n + 1 steps first
    // Step 2: move slow and fast together, when fast hits null, slow is the previous node of node that to be deleted
    // Time O(n)
    // Space O(1)
    public ListNode removeNthFromEndII(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return dummy.next;
            }
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // Another implementation:
    // Time O(n)
    // Space O(1)
    public ListNode removeNthFromEndI(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (n > 0 && head != null) {
            head = head.next;
            n--;
        }
        // if n > 0 here, the total number of nodes is smaller than n
        if (n == 0) {
            ListNode prev = dummy;
            while (head != null) {
                prev = prev.next;
                head = head.next;
            }
            // when head == null, prev.next is the node to remove
            prev.next = prev.next.next;
        }
        return dummy.next;
    }
}
