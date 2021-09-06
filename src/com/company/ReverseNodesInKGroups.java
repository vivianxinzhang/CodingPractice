package com.company;

public class ReverseNodesInKGroups {
    public static void main(String[] args) {
        ReverseNodesInKGroups s = new ReverseNodesInKGroups();

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
        // Input List:  1 -> 2 -> 3 -> 4 -> 5
        ListNode curr = s.reverseKGroup(one, 2);
        Printer.printLinkedList(curr);
        // Output List: 2 -> 1 -> 4 -> 3 -> 5
    }

    // Method 1: iteration
    // Time O(n)
    // Space O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        while (head != null) {
            ListNode pre = head;
            for (int i = 0; i < k - 1; i++) {
                pre = pre.next;
                if (pre == null) {
                    return dummy.next;
                }
            }
            ListNode next = pre.next;
            pre.next = null;
            reverse(head);
            tail.next = pre;
            head.next = next;
            tail = head;
            head = next;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // Method 1: recursion
    // Time O(n)
    // Space O(n/k)
    public ListNode reverseKGroupI(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // find tail of the current k elements
        // move k - 1 steps from head
        ListNode tail = head;
        for (int i = 1; i < k; i++) {
            tail = tail.next;
            if (tail == null) {
                return head;
            }
        }
        ListNode subHead = reverseKGroup(tail.next, k);
        tail.next = null;
        ListNode newHead = reverse(head);
        head.next = subHead;
        return newHead;
    }
}
