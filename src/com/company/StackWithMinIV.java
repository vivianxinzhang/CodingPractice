package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMinIV {
    Deque<Integer> stack;
    // min used to store elements contribute to min
    Deque<Pair> minStack;

    public StackWithMinIV() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        StackWithMinIV s = new StackWithMinIV();
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
        if (stack.isEmpty()) {
            return -1;
        }
        int res = stack.pollFirst();
        // when the popped value is the same minValue as top value of minStack, the size of stack is
        // the firstSeenSize of this minValue
        if (res == minStack.peekFirst().minValue && stack.size() + 1 == minStack.peekFirst().firstSeenSize) {
            minStack.pollFirst();
        }
        return res;
    }

    public void push(int element) {
        stack.offerFirst(element);
        // when minStack is empty or first time seen this minValue
        // also need to push the value to minStack.
        if (minStack.isEmpty() || element < minStack.peekFirst().minValue) {
            minStack.offerFirst(new Pair(element, stack.size()));
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peekFirst();
    }

    public int min() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peekFirst().minValue;
    }

    class Pair {
        int minValue;
        int firstSeenSize;  // size of original stack when we first seen the minValue

        public Pair(int minValue, int firstSeenSize) {
            this.minValue = minValue;
            this.firstSeenSize = firstSeenSize;
        }
    }
}
