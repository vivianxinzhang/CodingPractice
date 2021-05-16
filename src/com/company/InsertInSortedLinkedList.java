package com.company;

public class InsertInSortedLinkedList {
    public static void main(String[] args) {
        InsertInSortedLinkedList s = new InsertInSortedLinkedList();
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
        // Input List:        1 -> 2 -> 3 -> 4 -> 5
        // Output List: -1 -> 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = s.insert(one, -1);
        Printer.printLinkedList(head);
    }

    // Solution 2:
    // use a dummy head (-infinity)
    // Time O(n)
    // Space O(1)
    public ListNode insert(ListNode head, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.value < value) {
            pre = pre.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = pre.next;
        pre.next = newNode;
        return dummy.next;
    }

    // Solution 1:
    // Case 1: head == null || target <= head.value
    //         insert before the head
    // Case 2: cur.next == null || cur.next.value >= target
    //         insert to the following place
    // Time O(n)
    // Space O(1)
    public ListNode insertI(ListNode head, int value) {
        if (head == null || value <= head.value) {
            ListNode newHead = new ListNode(value);
            newHead.next = head;
            return newHead;
        }
        ListNode pre = head;
        while (pre.next != null && pre.next.value < value) {
            pre = pre.next;
        }
        ListNode node = new ListNode(value);
        node.next = pre.next;
        pre.next = node;
        return head;
    }
}
