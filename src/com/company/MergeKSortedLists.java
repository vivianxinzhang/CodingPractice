package com.company;
import java.util.*;

public class MergeKSortedLists {
    public static void main(String[] args) {
        MergeKSortedLists s = new MergeKSortedLists();
        // 1 -> 3 -> 5
        ListNode one = new ListNode(1);
        ListNode three = new ListNode(3);
        ListNode five = new ListNode(5);
        one.next = three;
        three.next = five;
        // 0 -> 7
        ListNode zero = new ListNode(0);
        ListNode seven = new ListNode(7);
        zero.next = seven;
        // 2 -> 6
        ListNode two = new ListNode(2);
        ListNode six = new ListNode(6);
        two.next = six;

        List<ListNode> listOfLists = new ArrayList<>();
        ListNode head = s.merge(listOfLists);
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        listOfLists.add(zero);
        listOfLists.add(one);
        listOfLists.add(two);

        for (ListNode node : listOfLists) {
            head = node;
            while (head != null) {
                System.out.print(head.value + " ");
                head = head.next;
            }
            System.out.println();
        }

        head = s.merge(listOfLists);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    // Assumptions: ListOfLists is not null, and none of the lists is null.
    // Method 3: play together (heap)
    // Time O(kn * logk)
    // Space O(k)
    public ListNode merge(List<ListNode> listOfLists) {
        Queue<ListNode> minHeap = new PriorityQueue<>(new MyComparator());
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode head : listOfLists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode curr = minHeap.poll();
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) {
                minHeap.offer(curr.next);
            }
        }
//        while (!minHeap.isEmpty()) {
//            tail.next = minHeap.poll();
//            if (tail.next.next != null) {
//                minHeap.offer(tail.next.next);
//            }
//            tail = tail.next;
//        }
        return dummy.next;
    }

    static class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if (o1.value == o2.value) {
                return 0;
            }
            return o1.value < o2.value ? -1 : 1;
        }
    }
}
