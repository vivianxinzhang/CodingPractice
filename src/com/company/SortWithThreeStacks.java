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

    // Time O(n^2)
    // Space O(n)
    public void sort(Deque<Integer> stack) {
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
