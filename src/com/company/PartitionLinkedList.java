package com.company;

public class PartitionLinkedList {
    // Given a linked list and a target value T, partition it such that all nodes less than T are listed
    // before the nodes larger than or equal to target value T.
    // The original relative order of the nodes in each of the two partitions should be preserved.
    // Time O(n)
    // Space O(1)
    public ListNode partition(ListNode head, int target) {
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode smallTail = smallDummy;
        ListNode largeTail = largeDummy;
        while (head != null) {
            if (head.value < target) {
                smallTail.next = head;
                smallTail = smallTail.next;
            } else {
                largeTail.next = head;
                largeTail = largeTail.next;
            }
            head = head.next;
        }
        // connect the two partitions
        smallTail.next = largeDummy.next;
        // un-link the last node in large partition
        largeTail.next = null;
        return smallDummy.next;
    }
}
