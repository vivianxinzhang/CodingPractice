package com.company;
import java.util.*;

public class ClosestNumberInBinarySearchTreeII {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder rec = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = rec.reconstruct(level);
        /*           5
                  /    \
                 3      8
              /    \       \
            1     4         11
        * */
        ClosestNumberInBinarySearchTreeII s = new ClosestNumberInBinarySearchTreeII();
        System.out.println(Arrays.toString(s.closestKValues(root, 10, 3)));     // [5, 8, 11]
        System.out.println(Arrays.toString(s.closestKValuesI(root, 10, 3)));    // [5, 8, 11]

        level = new int[]{5, 2, 11, 6, 14};
        root = rec.reconstruct(level);
        /*           5
                  /    \
                 2      11
                      /    \
                     6     11
        * */
        System.out.println(Arrays.toString(s.closestKValues(root, 4, 3)));     // [2, 5, 6]
        System.out.println(Arrays.toString(s.closestKValuesI(root, 3, 3)));    // [2, 5, 6]
    }

    // Assumptions: the given binary search tree is not null
    // Data structure:
    // a sliding window of size k
    // Algorithm:
    // We use a de-queue(queue) of size k to simulate a sliding window.
    // We run in-order traversal on this BST, and for each new node X,
    // Case 1: if de-queue.size < k, we just insert X into the deque
    // Case 2: if queue.size == k,
    //      Case 2.1: if abs(X-target) < abs(dequeue.left() - target),
    //                we call dequeue.pollFirst() and insert X into the deque
    //      Case 2.2 return
    // Time O(n)
    // Space O(k)
    public int[] closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> result = new ArrayDeque<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        while (!stack.isEmpty()) {
            // curr is the current element we are traversing
            TreeNode curr = stack.pop();
            // whether add curr to result or not
            if (result.size() < k) {
                result.addLast(curr.key);
            } else if (Math.abs(curr.key - target) < Math.abs(result.peekFirst() - target)) {
                result.removeFirst();
                result.addLast(curr.key);
            } else {
                break;
            }
            pushLeft(curr.right, stack);
        }
        return toIntArray(result);
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    private int[] toIntArray(Deque<Integer> deque) {
        int[] result = new int[deque.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = deque.removeFirst();
        }
        return result;
    }

    // Recursion: update value in sliding window while doing inorder traversal
    // Time O(n)
    // Space O(max(h, k)) worst case O(n)
    public int[] closestKValuesI(TreeNode root, double target, int k) {
        Deque<Integer> result = new ArrayDeque<>();
        helper(root, target, k, result);
        return toIntArray(result);
    }

    private void helper(TreeNode root, double target, int k, Deque<Integer> result) {
        if (root == null) {
            return;
        }
        helper(root.left, target, k, result);
        if (result.size() < k) {
            result.add(root.key);
        } else if (Math.abs(root.key - target) < Math.abs(result.peekFirst() - target)) {
            result.pollFirst();
            result.offerLast(root.key);
        }
        helper(root.right, target, k, result);
    }

    private int[] listToIntArray(List<Integer> list) {
        int k = list.size();
        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            if (i < list.size()) {
                result[i] = list.get(i);
            }
        }
        return result;
    }
}
