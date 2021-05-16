package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public static void printCircularLinkedList(ListNode head) {
        ListNode cur = head;
        boolean seenHeadAgain = false;
        while (cur != null && !seenHeadAgain) {
            System.out.print(cur.value);
            cur = cur.next;
            if (cur == head) {
                seenHeadAgain = true;
            }
            System.out.print(" -> ");
        }
        System.out.print("(" + cur.value + ")");
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
