package com.company;

public class InsertionSortLinkedList {
    public static void main(String[] args) {
        InsertionSortLinkedList s = new InsertionSortLinkedList();

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
        ListNode head = s.insertionSort(five);
        Printer.printLinkedList(head);
    }

    // At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
    // Case 1: if the new element is bigger than previous node, move pre pointer,
    // Case 2: if the new element is smaller than prev, insert the new node to the sorted part.
    // Time O(n^2)
    // Space O(n)
    public ListNode insertionSort(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode tail = dummy;
        while (tail.next != null) {
            // 这种 case需要单独处理 否则会出现死循环
            // (tail指针没有移动 node被拆下来之后 又插入了原来的位置)
            if (tail.next.value > tail.value) {
                tail = tail.next;
            } else {
                ListNode node = tail.next;
                tail.next = tail.next.next;
                node.next = null;
                insert(dummy, node);
            }
        }
        return dummy.next;
    }

    private void insert(ListNode head, ListNode node) {
        while (head.next != null && head.next.value < node.value) {
            head = head.next;
        }
        node.next = head.next;
        head.next = node;
    }
}
