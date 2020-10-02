package com.company;

public class ReverseByPair {
    public static void main(String[] args) {
        ReverseByPair s = new ReverseByPair();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode zero = new ListNode(0);
        one.next = two;
        two.next = three;
        ListNode curr = s.reverseInPairs(one);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // iterative
    public ListNode reverseInPairs(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode next = head.next.next;
            ListNode newHead = head.next;
            prev.next = newHead;
            newHead.next = head;
            head.next = next;
            prev = head;
            head = next;
        }
        return dummy.next;
    }

    public ListNode reverseInPairsII(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head, next = null;
        while (curr != null && curr.next != null) {
            next = curr.next.next;
            ListNode subHead = curr.next;
            prev.next = subHead;
            subHead.next = curr;
            curr.next = next;
            prev = curr;
            curr = next;
        }
        return dummy.next;
    }

    // recursive
    public ListNode reverseInPairsI(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode subHead = reverseInPairs(head.next.next);
        ListNode node2 = head.next;
        node2.next = head;
        head.next = subHead;
        return node2;
    }
}
