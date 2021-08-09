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
        Printer.printLinkedList(curr);
        // 2 -> 1 -> 4 -> 3 -> 5
    }

    // 1->2->3
    // 2->1->3
    // 1->2->3->4
    // 2->1->4->3
    // iterative:
    // Time O(n)
    // Space O(1)
    public ListNode reverseInPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        // pre->1->2|->3
        while (pre.next != null && pre.next.next != null) {
            // pre->1->2|->3
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            // pre->1 2|->3
            //      |_____↑
            node1.next = node2.next;
            //      2
            //      ↓
            // pre->1 2|->3
            //      |_____↑
            node2.next = node1;
            // pre->2
            //      ↓
            //      1     3
            //      |_____↑
            pre.next = node2;
            pre = node1;
        }
        return dummy.next;
    }

    public ListNode reverseInPairsII(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            ListNode subHead = head.next;
            tail.next = subHead;
            subHead.next = head;
            head.next = next;
            tail = head;
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
