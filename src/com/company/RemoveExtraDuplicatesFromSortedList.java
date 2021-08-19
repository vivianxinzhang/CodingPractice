package com.company;

public class RemoveExtraDuplicatesFromSortedList {
    public static void main(String[] args) {
        RemoveExtraDuplicatesFromSortedList s = new RemoveExtraDuplicatesFromSortedList();

        ListNode one = new ListNode(1);
        ListNode one1 = new ListNode(1);
        ListNode one2 = new ListNode(1);
        one.next = one1;
        one1.next = one2;

        ListNode newHead = s.removeDup(one);
        Printer.printLinkedList(newHead);
    }

    // Time O(n)
    // Space O(1)
    // Time O(n)
    // Space O(1)
    public ListNode removeDup(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            if (next != null && next.value == head.value) {
                while (next != null && next.value == head.value) {
                    next = next.next;
                }
                pre.next = next;
                head = next;
            } else {
                pre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }
}
