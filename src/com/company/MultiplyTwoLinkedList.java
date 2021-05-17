package com.company;

public class MultiplyTwoLinkedList {
    public static void main(String[] args) {
        MultiplyTwoLinkedList s = new MultiplyTwoLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        one.next = two;
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        one1.next = two1;
        System.out.println(s.multiply(one, one1));   // 12 * 12 = 144

        ListNode zero = new ListNode(0);
        System.out.println(s.multiply(one, zero));   // 12 * 0 = 0

        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        four.next = five;
        System.out.println(s.multiply(one, four));   // 12 * 45 = 540
    }

    public long multiply(ListNode one, ListNode two) {
        long num1 = getNumber(one);
        long num2 = getNumber(two);
        return num1 * num2;
    }

    private long getNumber(ListNode head) {
        long num = 0;
        while (head != null) {
            num *= 10;
            num += head.value;
            head = head.next;
        }
        return num;
    }
}
