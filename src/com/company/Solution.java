package com.company;
import java.util.*;

/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
//
public class Solution {
    public ListNode removeDup(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            if (curr.value == prev.value) {
                while (curr != null && curr.value == prev.value) {
                    curr = curr.next;
                }
            }
             prev.next = curr;
            prev = curr;
            curr = curr.next;
        }
        return head;
    }
}


