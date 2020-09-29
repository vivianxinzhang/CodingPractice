package com.company;
import java.util.*;

// Time O(n)
// Space O(h) worst O(n)
public class Solution {
    public ListNode reverse(ListNode head) {
        // Write your solution here
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
