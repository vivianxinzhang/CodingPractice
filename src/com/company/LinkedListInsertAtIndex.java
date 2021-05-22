package com.company;

public class LinkedListInsertAtIndex {
    public static void main(String[] args) {
        LinkedListInsertAtIndex s = new LinkedListInsertAtIndex();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        Printer.printLinkedList(one);
        // 1 -> 2 -> 3
        ListNode res = s.insert(one, 3, 4);
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5
        Printer.printLinkedList(res);

        res = s.insert(one, 0, 0);
        Printer.printLinkedList(res);
        // 0 -> 1 -> 2 -> 3 -> 4
    }

    // Insert a new element at a specific index in the given linked list.
    // The index is 0 based, and if the index is out of the list's scope,
    // you do not need to do anything.
    // Using dummy head:
    // Time O(n)
    // Space O(1)
    public ListNode insert(ListNode head, int index, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
            if (pre == null) {
                return dummy.next;
            }
        }
        // pre is the previous node of insert position
        ListNode node = new ListNode(value);
        node.next = pre.next;
        pre.next = node;
        return dummy.next;
    }

    // Not using dummy head:
    // Time O(n)
    // Space O(1)
    public ListNode insertI(ListNode head, int index, int value) {
        if (index == 0) {
            ListNode newHead = new ListNode(value);
            newHead.next = head;
            return newHead;
        }
        ListNode pre = head;
        for (int i = 0; i < index - 1; i++) {
            if (pre.next == null) {
                return head;
            }
            pre = pre.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = pre.next;
        pre.next = newNode;
        return head;
    }
}
