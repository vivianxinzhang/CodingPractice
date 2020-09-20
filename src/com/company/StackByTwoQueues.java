package com.company;
import java.util.*;

public class StackByTwoQueues {
    Queue<Integer> q1;  // used to store element
    Queue<Integer> q2;  // used as a buffer
    /** Initialize your data structure here. */
    public StackByTwoQueues() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    // Time O(1)
    public void push(int x) {
        q1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    // move all but the last element from Q1 to Q2
    // dequeue the last element from Q1
    // swap the reference of Q1 and Q2
    // Time O(n)
    public Integer pop() {
        Integer prev = q1.poll();
        Integer curr = q1.poll();
        while (curr != null) { // when curr == null, prev is the last element in the queue
            q2.offer(prev);
            prev = curr;
            curr = q1.poll();
        }
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return prev;
    }

    /** Get the top element. */
    // Q1拿出元素后判断Q1是否为空
    // 如果不为空 塞入Q2
    // 如果为空 将该元素返回
    // Time O(n)
    public Integer top() {
        Integer result = pop(); // top is the method we write
        if (result != null) {
            q1.offer(result);
        }
        return result;
    }

    /** Returns whether the stack is empty. */
    // Time O(n)
    public boolean isEmpty() {
        return top() == null; // top is the method we write
    }
}
