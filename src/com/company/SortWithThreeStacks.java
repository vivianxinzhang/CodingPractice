package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class SortWithThreeStacks {
    public static void main(String[] args) {
        SortWithThreeStacks s = new SortWithThreeStacks();

        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(1);
        stack.offerFirst(4);
        stack.offerFirst(3);
        stack.offerFirst(1);
        stack.offerFirst(2);
        System.out.println(stack);     //  2, 1, 3, 4, 1]
        s.sort(stack);
        System.out.println(stack);     //  4, 3, 2, 1, 1]
    }

    // Assumptions:
    // 1. The given stack is not null.
    // 2. The time complexity should be O(n log n).
    // Method 2:
    // Time O(nlogn)
    // Space O(logn)
    public void sort(Deque<Integer> s1) {
        Deque<Integer> s2 = new ArrayDeque<>();
        Deque<Integer> s3 = new ArrayDeque<>();
        mergeSort(s1, s2, s3, s1.size());
    }

    private void mergeSort(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3, int size) {
        if (size <= 1) {
            return;
        }
        int half = size / 2;
        for (int i = 0; i < half; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        mergeSort(s1, s2, s3, size - half);
        mergeSort(s2, s1, s3, half);
        merge(s1, size - half, s2, half, s3);
    }

    private void merge(Deque<Integer> s1, int size1, Deque<Integer> s2, int size2, Deque<Integer> s3) {
        int total = size1 + size2;
        while (size1 > 0 && size2 > 0) {
            if (s1.peekFirst() < s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                size1--;
            } else {
                s3.offerFirst(s2.pollFirst());
                size2--;
            }
        }
        while (size1 > 0) {
            s3.offerFirst(s1.pollFirst());
            size1--;
        }
        while (size2 > 0) {
            s3.offerFirst(s2.pollFirst());
            size2--;
        }
        for (int i = 0; i < total; i++) {
            s1.offerFirst(s3.pollFirst());
        }
    }

    // Method 1:
    // Time O(n^2)
    // Space O(n)
    public void sortI(Deque<Integer> stack) {
        if (stack == null || stack.size() <= 1) {
            return;
        }
        Deque<Integer> buffer = new ArrayDeque<>();
        Deque<Integer> res = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int curMax = stack.peekFirst();
            int count = 0;
            while (!stack.isEmpty()) {
                int cur = stack.pollFirst();
                if (cur > curMax) {
                    curMax = cur;
                    count = 1;
                } else if (cur == curMax) {
                    count++;
                }
                buffer.offerFirst(cur);
            }
            while (count > 0) {
                res.offerFirst(curMax);
                count--;
            }
            while (!buffer.isEmpty()) {
                int cur = buffer.pollFirst();
                if (cur != curMax) {
                    stack.offerFirst(cur);
                }
            }
        }
        while (!res.isEmpty()) {
            stack.offerFirst(res.pollFirst());
        }
    }
}
