package com.company;

public class ReverseLinkedListInPairs {
    public static void main(String[] args) {
        ReverseLinkedListInPairs s = new ReverseLinkedListInPairs();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode curr = s.reverseInPairs(one);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // iterative
    // Time O(n)
    // Space O(1)
    public ListNode reverseInPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            pre.next = head.next;
            pre.next.next = head;
            head.next = next;
            pre = head;
            head = next;
        }
        return dummy.next;
    }

    // recursive
    // Time O(n)
    // Space O(n)
    public ListNode reverseInPairsI(ListNode head) {
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
