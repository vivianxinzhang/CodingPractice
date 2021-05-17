package com.company;

public class MergeSortLinkedList {
    public static void main(String[] args) {
        MergeSortLinkedList s = new MergeSortLinkedList();
        ListNode five = new ListNode(5);
        ListNode one = new ListNode(1);
        ListNode four = new ListNode(4);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        five.next = one;
        one.next = four;
        four.next = two;
        two.next = three;
        Printer.printLinkedList(five);
        // Input List:  5 -> 1 -> 4 -> 2 -> 3
        // Output List: 1 -> 2 -> 3 -> 4 -> 5
        ListNode res = s.mergeSort(five);
        Printer.printLinkedList(res);
    }

    // Time O(nlogn)
    // Space O(logn)
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // split the list into two halves
        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null;
        // merge sort each half
        ListNode leftHalf = mergeSort(head);
        ListNode rightHalf = mergeSort(middleNext);
        // combine two halves
        return merge(leftHalf, rightHalf);
    }

    public ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode curr1 = one;
        ListNode curr2 = two;
        while (curr1 != null && curr2 != null) {
            if (curr1.value < curr2.value) {
                curr.next = curr1;
                curr1 = curr1.next;
            } else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        if (curr1 != null) {
            curr.next = curr1;
        }
        if (curr2 != null) {
            curr.next = curr2;
        }
        return dummy.next;
    }
}
