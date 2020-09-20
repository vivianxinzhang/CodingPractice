package com.company;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers s = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        ListNode four = new ListNode(4);
        l1.next = four;
        ListNode three = new ListNode(3);
        four.next = three;
        ListNode curr1 = l1;
        while (curr1 != null) {
            System.out.print(curr1.value);
            curr1 = curr1.next;
        }
        System.out.println();
        ListNode l2 = new ListNode(5);
        ListNode six = new ListNode(6);
        l2.next = six;
        ListNode four2 = new ListNode(4);
        six.next = four2;
        ListNode curr2 = l1;
        while (curr2 != null) {
            System.out.print(curr2.value);
            curr2 = curr2.next;
        }
        System.out.println();
        ListNode curr = s.addTwoNumbers(l1, l2);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
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
