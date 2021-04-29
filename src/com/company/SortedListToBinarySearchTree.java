package com.company;

public class SortedListToBinarySearchTree {
    public static void main(String[] args) {
        SortedListToBinarySearchTree s = new SortedListToBinarySearchTree();
        ListNode one = new ListNode(1);
        ListNode three = new ListNode(3);
        one.next = three;
        System.out.println(s.sortedListToBST(one));
    }

    // Algorithm:
    // Recursive method to create Tree --> sortedListToBST(ListNode head)
    // if head = null, return null
    // find mid node: call the mid method mid(head)
    // create root node with value as mid.val
    // if head = mid, return root
    // root.left = recursive call sortedListToBST(head)
    // root.right = recursive call sortedListToBST(mid.next)
    // Time O(nlogn)
    // Space O(logn)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = findMid(head);
        TreeNode root = new TreeNode(mid.value);
        if (mid == head) {
            return root;
        }
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    // Find mid and break previous links --> mid(ListNode)
    // initialize slow, fast, prev to head
    // while fast NOT_NULL and fast.next NOT_NULL
    //      assign slow to pre, move slow 1 step, move fast 2 steps
    // if pre is NOT_Null, break line between pre and pre.next
    // find mid and de-link two parts
    private ListNode findMid(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        return slow;
    }

    // Time O(nlogn)
    // Space O(logn)
    public TreeNode sortedListToBSTI(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.value);
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            pre = pre.next;
        }
        TreeNode root = new TreeNode(slow.value);
        ListNode left = dummy.next;
        pre.next = null;
        ListNode right = slow.next;
        slow.next = null;
        root.left = sortedListToBST(left);
        root.right = sortedListToBST(right);
        return root;
    }
}
