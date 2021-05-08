package com.company;

import java.util.Arrays;

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

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
