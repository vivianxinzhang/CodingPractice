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
        // 0 7
        // 1 3 5
        // 2 6

        head = s.mergeII(listOfLists);
        Printer.printLinkedList(head);
        // 0 -> 1 -> 2 -> 3 -> 5 -> 6 -> 7
    }

    // Assumptions:
    // 1. ListOfLists is not null, 2. none of the lists is null.
    // Method 3: play together (heap)
    // Time O(kn * logk)
    // Space O(k)
    public ListNode merge(List<ListNode> listOfLists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new MyComparator());
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
            if (curr.next != null) {
                minHeap.offer(curr.next);
            }
            tail = tail.next;
        }
        return dummy.next;
    }

    class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if (o1.value == o2.value) {
                return 0;
            }
            return o1.value < o2.value ? -1 : 1;
        }
    }

    // Method 2: binary reduction
    // 2.1 iteration: space optimization
    // Time O(kn * logk)
    // Space O(1)
    public ListNode mergeII(List<ListNode> listOfLists) {
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        int size = listOfLists.size();
        while (size > 1) {
            List<ListNode> newList = new ArrayList<>();
            int i = 0;
            int j = listOfLists.size() - 1;
            while (i < j) {
                newList.add(mergeTwo(listOfLists.get(i), listOfLists.get(j)));
                i++;
                j--;
            }
            if (i == j) {
                newList.add(listOfLists.get(i));
            }
            listOfLists = newList;
            size = listOfLists.size();
        }
        return listOfLists.get(0);
    }

    private ListNode mergeTwo(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (one != null && two != null) {
            if (one.value < two.value) {
                tail.next = one;
                one = one.next;
            } else {
                tail.next = two;
                two = two.next;
            }
            tail = tail.next;
        }
        if (one != null) {
            tail.next = one;
        }
        if (two != null) {
            tail.next = two;
        }
        return dummy.next;
    }

    // binary reduction
    // 2.2 recursion: space optimization
    // Time O(kn * logk)
    // Space O(logk)
    public ListNode mergeIII(List<ListNode> listOfLists) {
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        return mergeInBinaryReduction(listOfLists, 0, listOfLists.size() - 1);
    }

    private ListNode mergeInBinaryReduction(List<ListNode> listOfLists, int i, int j) {
        if (i == j) {
            return listOfLists.get(i);
        }
        if (i == j - 1) {
            return mergeTwo(listOfLists.get(i), listOfLists.get(j));
        }
        int mid = i + (j - i) / 2;
        ListNode left = mergeInBinaryReduction(listOfLists, i, mid);
        ListNode right = mergeInBinaryReduction(listOfLists, mid + 1, j);
        return mergeTwo(left, right);
    }

    // Method 1: iterative reduction
    // Time O(k^2 * n)
    // Space O(1)
    public ListNode mergeI(List<ListNode> listOfLists) {
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        ListNode head = listOfLists.get(0);
        for (int i = 1; i < listOfLists.size(); i++) {
            head = mergeTwo(head, listOfLists.get(i));
        }
        return head;
    }
}
