package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers s = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        ListNode four = new ListNode(4);
        l1.next = four;
        ListNode three = new ListNode(3);
        four.next = three;
        ListNode cur1 = l1;
        Printer.printLinkedList(cur1);
        ListNode l2 = new ListNode(5);
        ListNode six = new ListNode(6);
        l2.next = six;
        ListNode four2 = new ListNode(4);
        six.next = four2;
        ListNode cur2 = l2;
        Printer.printLinkedList(cur2);
        ListNode cur = s.addTwoNumbers(l1, l2);
        Printer.printLinkedList(cur);
        System.out.println();

        ListNode one = new ListNode(5);
        ListNode two = new ListNode(5);
        cur = s.addTwoNumbers(one, two);
        Printer.printLinkedList(cur);
    }

    // You are given two non-empty linked lists representing two non-negative integers.
    // The most significant digit comes first and each of their nodes contain a single digit.
    // Add the two numbers and return it as a linked list.
    //You may assume the two numbers do not contain any leading zero, except the number 0 itself.
    // Follow up:
    // What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
    // num1: 3 -> 4 -> 2
    // num1: 4 -> 6 -> 5
    // res:  8 -> 0 -> 7
    // Method 1:
    // Time O(max(m,n))
    // Space O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        while (l1 != null) {
            s1.offerFirst(l1.value);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.offerFirst(l2.value);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int num = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                num += s1.pollFirst();
            }
            if (!s2.isEmpty()) {
                num += s2.pollFirst();
            }
            tail.next = new ListNode(num % 10);
            num /= 10;
            tail = tail.next;
        }
        if (num != 0) {
            tail.next = new ListNode(num);
        }
        return reverse(dummy.next);
    }

    // Method 1:
    // Time O(max(m,n))
    // Space O(1)
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverse(l1);
        ListNode reverseL2 = reverse(l2);
        return reverse(add(reverseL1, reverseL2));
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode add(ListNode a, ListNode b) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int val = 0;
        while (a != null || b != null) {
            if (a != null) {
                val += a.value;
                a = a.next;
            }
            if (b != null) {
                val += b.value;
                b = b.next;
            }
            cur.next = new ListNode(val % 10);
            val /= 10;
            cur = cur.next;
        }
        if (val != 0) {
            cur.next = new ListNode(val);
        }
        return dummy.next;
    }

    public ListNode addTwoNumbersI(ListNode l1, ListNode l2) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int num = 0;
        while (l1 != null && l2 != null) {
            num += l1.value;
            l1 = l1.next;
            num += l2.value;
            l2 = l2.next;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
        }
        while (l1 != null) {
            num += l1.value;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            num += l2.value;
            curr.next = new ListNode(num % 10);
            num /= 10;
            curr = curr.next;
            l2 = l2.next;
        }
        while (num != 0) {
            curr.next = new ListNode(num % 10);
            num /= 10;
        }
        return dummy.next;
    }
}
