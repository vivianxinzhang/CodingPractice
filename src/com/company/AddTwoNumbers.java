package com.company;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int number = 0;
        while (l1 != null && l2 != null) {
            number += l1.value;
            l1 = l1.next;
            number += l2.value;
            l2 = l2.next;
            tail.next = new ListNode(number % 10);
            number /= 10;
            tail = tail.next;
        }
        while (l1 != null) {
            number += l1.value;
            tail.next = new ListNode(number % 10);
            l1 = l1.next;
            tail = tail.next;
            number /= 10;
        }
        while (l2 != null) {
            number += l2.value;
            tail.next = new ListNode(number % 10);
            l2 = l2.next;
            tail = tail.next;
            number /= 10;
        }
        while (number != 0) {
            tail.next = new ListNode(number % 10);
            number /= 10;
        }
        return dummy.next;
    }
}
