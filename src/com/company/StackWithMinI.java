package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMinI {
    public static void main(String[] args) {
        StackWithMinI s = new StackWithMinI();
        // Test 1:
//        s.push(5);
//        System.out.println(s.top());    // 5
//        System.out.println(s.min());    // 5
//        System.out.println(s.pop());    // 5
//        System.out.println(s.min());    // 5
//        System.out.println(s.top());    // -1
//        s.push(10);
//        System.out.println(s.top());    // 10
//        System.out.println(s.min());    // 5

        // Test 2:
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

    // stack1: is used to store input elements
    // stack2: is used to store the min element so far in stack1 (as its top element)
    Deque<Integer> stack;
    Deque<Integer> minStack;

    public StackWithMinI() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int tmp = stack.pollFirst();
        minStack.pollFirst();
        return tmp;
    }

    public void push(int element) {
        stack.offerFirst(element);
        if (minStack.isEmpty()) {
            minStack.offerFirst(element);
            return;
        }
        int curMin = minStack.peekFirst();
        if (element < curMin) {
            minStack.offerFirst(element);
        } else {
            minStack.offerFirst(curMin);
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peekFirst();
    }

    public int min() {
        return minStack.isEmpty() ? -1 : minStack.peekFirst();
    }
}