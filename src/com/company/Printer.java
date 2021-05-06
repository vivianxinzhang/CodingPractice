package com.company;

public class Printer {
    public static void printLinkedList(ListNode cur) {
        while (cur != null) {
            System.out.print(cur.value);
            cur = cur.next;
            if (cur != null) {
                System.out.print(" -> ");
            }
        }
    }
}
