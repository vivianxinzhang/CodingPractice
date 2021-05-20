package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class MergeSortWithThreeStacks {
    public static void main(String[] args) {
        MergeSortWithThreeStacks s = new MergeSortWithThreeStacks();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(1);
        stack.offerFirst(4);
        stack.offerFirst(3);
        stack.offerFirst(1);
        stack.offerFirst(2);
        System.out.println(stack);     //  2, 1, 3, 4, 1]
        s.sort(stack);
        System.out.println(stack);     //  1, 1, 2, 3, 4]
    }

    // Assumptions:
    // 1. The given stack is not null.
    // 2. The time complexity should be O(nlogn).
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
        int size2 = size / 2;
        for (int i = 0; i < size2; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        int size1 = size - size2;
        mergeSort(s1, s2, s3, size1);
        mergeSort(s2, s1, s3, size2);
        merge(s1, s2, s3, size1, size2);
    }

    private void merge(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3, int size1, int size2) {
        int i = 0;
        int j = 0;
        while (i < size1 && j < size2) {
            if (s1.peekFirst() < s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                i++;
            } else {
                s3.offerFirst(s2.pollFirst());
                j++;
            }
        }
        while (i < size1) {
            s3.offerFirst(s1.pollFirst());
            i++;
        }
        while (j < size2) {
            s3.offerFirst(s2.pollFirst());
            j++;
        }
        for (int k = 0; k < size1 + size2; k++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}