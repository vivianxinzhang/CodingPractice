package com.company;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "   I     love MTV   ";
        char[] array = new char[]{'a', 'b', 'c'};
        char[] newArray = Arrays.copyOf(array, 10);
        System.out.println(Arrays.toString(newArray));
    }

    // Time O(n)
    // Space O(h)
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode leftRes = lowestCommonAncestor(root.left, one, two);
        TreeNode rightRes = lowestCommonAncestor(root.right, one, two);
        if (leftRes != null && rightRes != null) {
            return root;
        }
        return leftRes == null ? rightRes : leftRes;
    }

    public void numNodesLeft(TreeNodeLeft root) {
        numNodes(root);
    }

    private int numNodes(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        int leftRes = numNodes(root.left);
        int rightRes = numNodes(root.right);
        root.numNodesLeft = leftRes;
        return leftRes + rightRes + 1;
    }


    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode subHead = reverseInPairs(head.next.next);
        ListNode node2 = head.next;
        head.next = subHead;
        node2.next = head;
        return node2;
    }

    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        helper(matrix, 0, matrix.length, result);
        return result;
    }

    private void helper(int[][] matrix, int start, int end, List<Integer> result) {
        if (start > end) {
            return;
        }
        if (start == end) {
            result.add(matrix[start][start]);
            return;
        }
        // top row
        for (int i = start; i < end; i++) {
            result.add(matrix[start][i]);
        }
        // right col
        for (int i = start; i < end; i++) {
            result.add(matrix[i][end]);
        }
        // bottom row
        for (int i = end; i > start; i--) {
            result.add(matrix[end][i]);
        }
        // left col
        for (int i = end; i > start; i--) {
            result.add(matrix[i][start]);
        }
        start++;
        end--;
        helper(matrix, start, end, result);
    }

    public class TreeNodeLeft {
        public int key;
        public TreeNodeLeft left;
        public TreeNodeLeft right;
        public int numNodesLeft;

        public TreeNodeLeft(int key) {
            this.key = key;
        }
    }
}