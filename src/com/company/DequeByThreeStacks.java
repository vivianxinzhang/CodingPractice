package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeByThreeStacks {
    private Deque<Integer> left;
    private Deque<Integer> right;
    private Deque<Integer> buffer;
    public DequeByThreeStacks() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        buffer = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        DequeByThreeStacks deque = new DequeByThreeStacks();
        deque.offerFirst(1);    // 1
        deque.offerFirst(2);    // 2 1
        deque.offerFirst(3);    // 3 2 1
        System.out.println(deque.peekLast());   // 1
        deque.pollLast();               // 3 2
        System.out.println(deque.peekLast());   // 2
        deque.pollFirst();              // 2
        System.out.println(deque.peekFirst());   // 2
        System.out.println(deque.peekLast());    // 2
    }

    // Amortized time O(1)
    public void offerFirst(int element) {
        left.offerFirst(element);
    }

    // Amortized time O(1)
    public void offerLast(int element) {
        right.offerFirst(element);
    }

    // Amortized time O(1)
    public Integer pollFirst() {
        moveIfNecessary(right, left);
        return left.isEmpty() ? null : left.pollFirst();
    }

    // Amortized time O(1)
    public Integer pollLast() {
        moveIfNecessary(left, right);
        return right.isEmpty() ? null : right.pollFirst();
    }

    // Amortized time O(1)
    public Integer peekFirst() {
        moveIfNecessary(right, left);
        return left.isEmpty() ? null : left.peekFirst();
    }

    // Amortized time O(1)
    public Integer peekLast() {
        moveIfNecessary(left, right);
        return right.isEmpty() ? null : right.peekFirst();
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    // when the destination stack is empty, move half of the elements from
    // the source stack to the destination stack
    // Time O(n)
    private void moveIfNecessary(Deque<Integer> src, Deque<Integer> dest) {
        if (!dest.isEmpty()) {
            return;
        }
        int halfSize = src.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            buffer.offerFirst(src.pollFirst());
        }
        while (!src.isEmpty()) {
            dest.offerFirst(src.pollFirst());
        }
        while (!buffer.isEmpty()) {
            src.offerFirst(buffer.pollFirst());
        }
    }
}
