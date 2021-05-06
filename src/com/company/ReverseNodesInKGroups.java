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
        System.out.println();
        // Input List:  1 -> 2 -> 3 -> 4 -> 5
        // Output List: 2 -> 1 -> 4 -> 3 -> 5
        ListNode curr = s.reverseKGroup(one, 2);
        Printer.printLinkedList(curr);
    }

    // Method 1: iteration
    // Time O(n)
    // Space O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode curr = findKth(head, k);
        if (curr == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (curr != null) {
            ListNode subHead = curr.next;
            curr.next = null;
            // reverse first k nodes
            ListNode newHead = reverse(head);
            prev.next = newHead;
            head.next = subHead;
            prev = head;
            head = subHead;
            curr = findKth(head, k);
        }
        return dummy.next;
    }

    private ListNode findKth(ListNode head, int k) {
        for (int i = 1; i < k; i++) {
            if (head == null || head.next == null) {
                return null;
            }
            head = head.next;
        }
        return head;
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
