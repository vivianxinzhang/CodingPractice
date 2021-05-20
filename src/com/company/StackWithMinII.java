package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMinII {
    Deque<Integer> stack;
    // min used to store elements contribute to min
    Deque<Integer> minStack;

    public StackWithMinII() {
        minStack = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        StackWithMinII s = new StackWithMinII();
        s.push(6);
        System.out.println(s.min());    // 6
        s.push(5);
        s.push(9);
        System.out.println(s.top());    // 9
        System.out.println(s.min());    // 5
        s.pop();
        System.out.println(s.min());    // 5
        System.out.println(s.top());    // 5
        System.out.println(s.min());    // 5
        s.pop();
        System.out.println(s.min());    // 6
    }

    public int pop() {
        // when the popped value is the same as top value of minStack, the value
        // need to be popped from minStack as well.
        if (stack.isEmpty()) {
            return -1;
        }
        int tmp = stack.pollFirst();
        if (tmp == minStack.peek()) {
            minStack.pollFirst();
        }
        return tmp;
    }

    public void push(int element) {
        // when value <= current min value in stack,
        // need to push the value to minStack.
        stack.offerFirst(element);
        if (minStack.isEmpty() || element <= minStack.peekFirst()) {
            minStack.offerFirst(element);
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peekFirst();
    }

    public int min() {
        return minStack.isEmpty() ? -1 : minStack.peekFirst();
    }
}
