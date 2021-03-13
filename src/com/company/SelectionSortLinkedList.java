package com.company;

public class SelectionSortLinkedList {
    public static void main(String[] args) {
        SelectionSortLinkedList s = new SelectionSortLinkedList();
        ListNode one = new ListNode(1);
        System.out.println(s.selectionSort(one));
    }

    // Time O(n^2)
    // Space O(1)
    public ListNode selectionSort(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        while (head != null) {
            ListNode smallest = findSmallest(head);
            head = deleteSmallest(head, smallest);
            tail.next = smallest;
            tail = tail.next;
        }
        return dummy.next;
    }

    private ListNode deleteSmallest(ListNode head, ListNode smallest) {
        if (head == smallest) {
            return head.next;
        }
        ListNode prev = head;
        while (prev.next != null && prev.next != smallest) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    private ListNode findSmallest(ListNode head) {
        ListNode min = head;
        while (head != null) {
            if (head.value < min.value) {
                min = head;
            }
            head = head.next;
        }
        return min;
    }
}
