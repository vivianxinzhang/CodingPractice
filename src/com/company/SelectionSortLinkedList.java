package com.company;

public class SelectionSortLinkedList {
    public static void main(String[] args) {
        SelectionSortLinkedList s = new SelectionSortLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        five.next = one;
        one.next = three;
        three.next = two;
        two.next = four;
        Printer.printLinkedList(five);
        System.out.println();
        // Input List:  5 -> 1 -> 3 -> 2 -> 4
        // Output List: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = s.selectionSort(five);
        Printer.printLinkedList(head);
    }

    // The selection sort algorithm sorts an array by repeatedly finding
    // the minimum element (considering ascending order)
    // from unsorted part and putting it at the beginning.
    // Time O(n^2)
    // Space O(1)
    public ListNode selectionSort(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head != null) {
            ListNode smallest = findSmallest(head);
            head = deleteSmallest(head, smallest);
            tail.next = smallest;
            smallest.next = head;
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
