package com.company;

public class DeleteNodesAtIndices {
    public static void main(String[] args) {
        DeleteNodesAtIndices s = new DeleteNodesAtIndices();
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
        int[] indices = new int[] {0, 3, 5};
        ListNode curr = s.deleteNodes(one, indices);
        Printer.printLinkedList(curr);
    }

    // Assumptions:
    // the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order.
    // Time O(n)
    // Space O(1)
    public ListNode deleteNodes(ListNode head, int[] indices) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int j = 0;
        int i = 0;
        while (pre.next != null && j < indices.length) {
            if (i == indices[j]) {
                pre.next = pre.next.next;
                j++;
            } else {
                pre = pre.next;
            }
            i++;
        }
        return dummy.next;
    }
}
