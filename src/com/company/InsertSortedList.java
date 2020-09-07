package com.company;

public class InsertSortedList {
    public static void main(String[] args) {
        InsertSortedList s = new InsertSortedList();
        ListNode head = GenerateLinkedList.generate(5)
        ListNode curr = head;
        curr = s.insert(head, 3);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // Method 2: using dummy head
    // Time O(n)
    // Space O(1)
    public ListNode insert2(ListNode head, int value) {
        // Write your solution here
        ListNode newNode = new ListNode(value);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null && curr.value < value) {
            prev = curr;
            curr = curr.next;
        }
        newNode.next = curr;
        prev.next = newNode;
        return dummy.next;
    }

    // Method 1: do not use dummy, need to discuss different cases
    // Time O(n)
    // Space O(1)
    public ListNode insert(ListNode head, int value) {
        // Write your solution here
        ListNode newNode = new ListNode(value);
        // 1. determine if the inserted node is before head
        if (head == null || head.value >= value) {
            newNode.next = head;
            return newNode;
        }
        // 2. insert the new node to the right position
        // using the previous node to traverse the linked list
        // the insert position of the new node should be between prev and prev.next
        ListNode prev = head;
        while (prev.next != null && prev.next.value < value) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }


}

