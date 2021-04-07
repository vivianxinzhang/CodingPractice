package com.company;

import java.util.*;

// Method 1:
public class StackWithMin {
    public static void main(String[] args) {
        StackWithMin s = new StackWithMin();
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


    // min used to store elements contribute to min
    Deque<Integer> min;
    Deque<Integer> stack;

    public StackWithMin() {
        // write your solution here
        min = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }

    public int pop() {
        // when the popped value is the same as top value of minStack, the value
        // need to be popped from minStack as well.
        if (stack.isEmpty()) {
            return -1;
        }
        int tmp = stack.pop();
        if (tmp == min.peek()) {
            min.pop();
        }
        return tmp;
    }

    public void push(int element) {
        // when value <= current min value in stack,
        // need to push the value to minStack.
        stack.push(element);
        if (min.isEmpty() || element <= min.peek()) {
            min.push(element);
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public int min() {
        return min.isEmpty() ? -1 : min.peek();
    }
}
