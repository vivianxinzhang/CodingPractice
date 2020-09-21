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

    public ListNode reverseInPairs(ListNode head) {
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
