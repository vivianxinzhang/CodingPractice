package com.company;

public class QuickSortLinkedList {
    public static void main(String[] args) {
        QuickSortLinkedList s = new QuickSortLinkedList();
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode six = new ListNode(6);
        ListNode three = new ListNode(3);
        five.next = four;
        four.next = one;
        one.next = two;
        two.next = six;
        six.next = three;
        ListNode head = five;
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
        // 541263
        System.out.println();
        ListNode curr = s.quickSort(five);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
        // 123456
    }

    // Corner Cases:
    // 1. What if the given array is null? In this case, we do not need to do anything.
    // 2. What if the given array is of length zero? In this case, we do not need to do anything.
    // Time O(nlogn)  1.5nlogn  worst case O(n^2)  2n^2
    // Space O(logn) worst case O(n)
    public ListNode quickSort(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallDummy = new ListNode(Integer.MIN_VALUE);
        ListNode largeDummy = new ListNode(Integer.MIN_VALUE);
        ListNode smallTail = smallDummy;
        ListNode largeTail = largeDummy;
        ListNode pivotNode = head;
        ListNode curr = head.next;
        head.next = null;
        while (curr != null) {
            if (curr.value < pivotNode.value) {
                smallTail.next = curr;
                smallTail = smallTail.next;
            } else {
                largeTail.next = curr;
                largeTail = largeTail.next;
            }
            curr = curr.next;
        }
        smallTail.next = null;
        largeTail.next = null;
        smallDummy.next = quickSort(smallDummy.next);
        largeDummy.next = quickSort(largeDummy.next);
        smallTail = findTail(smallDummy);
        smallTail.next = pivotNode;
        pivotNode.next = largeDummy.next;
        return smallDummy.next;
    }

    private ListNode findTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
