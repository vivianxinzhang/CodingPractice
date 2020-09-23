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

    public void offerFirst(int element) {
        left.offerFirst(element);
    }

    public void offerLast(int element) {
        right.offerFirst(element);
    }

    public Integer pollFirst() {
        moveIfNecessary(right, left);
        return left.isEmpty() ? null : left.pollFirst();
    }

    public Integer pollLast() {
        moveIfNecessary(left, right);
        return right.isEmpty() ? null : right.pollFirst();
    }

    public Integer peekFirst() {
        moveIfNecessary(right, left);
        return left.isEmpty() ? null : left.peekFirst();
    }

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
