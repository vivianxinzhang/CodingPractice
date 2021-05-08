package com.company;

public class DeleteNodeAtIndex {
    public static void main(String[] args) {
        DeleteNodeAtIndex s = new DeleteNodeAtIndex();
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
        // Input List:  1 -> 2 -> 3 -> 4
        // Output List: 2 -> 3 -> 5
        ListNode curr = s.deleteNode(one, 0);
        Printer.printLinkedList(curr);
    }

    // Assumptions:
    // the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order.
    // Time O(n)
    // Space O(1)
    public ListNode deleteNode(ListNode head, int index) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        while (pre.next != null) {
            if (i == index) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
            i++;
        }
        return dummy.next;
    }
}
