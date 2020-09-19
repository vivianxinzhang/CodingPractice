package com.company;
import java.util.*;

public class Solution {
    public void sort(Deque<Integer> s1) {

        Deque<Integer> s2 = new ArrayDeque<Integer>();
        Deque<Integer> s3 = new ArrayDeque<Integer>();
        // Write your solution here.
        sort(s1, s2, s3, s1.size());
    }

    private void sort(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3, int size) {
        if (size == 1) {
            return;
        }
        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        sort(s1, s2, s3, size - size / 2);
        sort(s2, s1, s3, size / 2);
        // merge the top size - size / 2 elements from s1 and
        // top size / 2 elements form s2
        int i = 0, j = 0;
        while (i < size - size / 2 && j < size / 2) {
            if (s1.peekFirst() < s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                i++;
            } else {
                s3.offerFirst(s2.pollFirst());
                j++;
            }
        }
        while (i < size - size / 2) {
            s3.offerFirst(s1.pollFirst());
            i++;
        }
        while (j < size / 2) {
            s3.offerFirst(s2.pollFirst());
            j++;
        }
        // move elements back to s1
        for (int k = 0; k < size; k++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}
