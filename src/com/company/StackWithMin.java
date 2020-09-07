package com.company;
import java.util.*;

// Method 1:
public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public StackWithMin() {
        stack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }

    public int min() {
        return minStack.peekFirst();
    }

    public void push(int value) {
        stack.offerFirst(value);
        // when value <= current min value in stack,
        // need to push the value to minStack.
        if (value <= minStack.peekFirst()) {
            minStack.offerFirst(value);
        }
    }

    public int pop() {
        Integer result = stack.pollFirst();
        // when the popped value is the same as top value of minStack, the value
        // need to be popped from minStack as well.
        if (minStack.peekFirst().equals(result)) {
            minStack.pollFirst();
        }
        return result;
    }

    public int top() {
        return stack.peekFirst();
    }
}
