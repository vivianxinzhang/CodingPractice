package com.company;

public class MultiplyTwoLinkedListI {
    public static void main(String[] args) {
        MultiplyTwoLinkedListI s = new MultiplyTwoLinkedListI();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
         one.next = two;
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        one1.next = two1;
        Printer.printLinkedList(s.multiply(one, one1));
        // 12 * 12 = 144        1 -> 4 -> 4

        ListNode zero = new ListNode(0);
        Printer.printLinkedList(s.multiply(one, zero));
        // 12 * 0 = 0           0

        ListNode one2 = new ListNode(1);
        ListNode two2 = new ListNode(2);
        one2.next = two2;
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        four.next = five;
        Printer.printLinkedList(s.multiply(one2, four));
        // 12 * 45 = 540        5 -> 4 -> 0
    }

    // Time O(mn)
    // Space O(m + n)
    public ListNode multiply(ListNode one, ListNode two) {
        if (one == null || two == null) {
            return null;
        }
        one = reverse(one);
        two = reverse(two);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (one != null) {
            int multiplier = one.value;
            ListNode curTail = tail;
            ListNode curTwo = two;
            int num = 0;
            while (curTwo != null) {
                num += curTwo.value * multiplier;
                if (curTail.next == null) {
                    curTail.next = new ListNode(num % 10);
                } else {
                    num += curTail.next.value;
                    curTail.next.value = num % 10;
                }
                num /= 10;
                curTwo = curTwo.next;
                curTail = curTail.next;
            }
            one = one.next;
            tail = tail.next;
        }
        return reverse(dummy.next);
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
}
