package com.company;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers s = new AddTwoNumbers();
//        ListNode l1 = new ListNode(2);
//        ListNode four = new ListNode(4);
//        l1.next = four;
//        ListNode three = new ListNode(3);
//        four.next = three;
//        ListNode curr1 = l1;
//        while (curr1 != null) {
//            System.out.print(curr1.value);
//            curr1 = curr1.next;
//        }
//        System.out.println();
//        ListNode l2 = new ListNode(5);
//        ListNode six = new ListNode(6);
//        l2.next = six;
//        ListNode four2 = new ListNode(4);
//        six.next = four2;
//        ListNode curr2 = l2;
//        while (curr2 != null) {
//            System.out.print(curr2.value);
//            curr2 = curr2.next;
//        }
//        System.out.println();
//        ListNode curr = s.addTwoNumbers(l1, l2);
//        while (curr != null) {
//            System.out.print(curr.value);
//            curr = curr.next;
//        }

        ListNode one = new ListNode(5);
        ListNode two = new ListNode(5);
        ListNode curr = s.addTwoNumbers(one, two);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }
    // You are given two non-empty linked lists representing two non-negative integers.
    // The most significant digit comes first and each of their nodes contain a single digit.
    // Add the two numbers and return it as a linked list.
    //You may assume the two numbers do not contain any leading zero, except the number 0 itself.
    // Follow up:
    //What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
    // Time O(max(m,n))
    // Space O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
