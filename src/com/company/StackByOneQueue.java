package com.company;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackByOneQueue {
    private Queue<Integer> q;

    /** Initialize your data structure here. */
    public StackByOneQueue() {
        q = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
        if (q.isEmpty()) {
            return null;
        }
        int size = q.size();
        while (size != 1) {
            q.offer(q.poll());
            size--;
        }
        return q.poll();
    }

    /** Get the top element. */
    public Integer top() {
        if (q.isEmpty()) {
            return null;
        }
        int result = pop();
        q.offer(result);
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean isEmpty() {
        return q.isEmpty();
    }
}
