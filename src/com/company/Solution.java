package com.company;
import java.util.*;


public class Solution {
    Deque<Integer> left;
    Deque<Integer> right;
    Deque<Integer> buffer;

    public Solution() {
        // Write your solution here.
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
        moveHalfIfNecessary(left, right);
        return left.isEmpty() ? null : left.pollFirst();
    }

    public Integer pollLast() {
        moveHalfIfNecessary(right, left);
        return right.isEmpty() ? null : left.pollFirst();
    }

    public Integer peekFirst() {
        moveHalfIfNecessary(left, right);
        return left.isEmpty() ? null : left.peekFirst();
    }

    public Integer peekLast() {
        moveHalfIfNecessary(right, left);
        return right.isEmpty() ? null : right.peekFirst();
    }

    private void moveHalfIfNecessary(Deque<Integer> dest, Deque<Integer> src) {
        if (!dest.isEmpty()) {
            return;
        }
        int size = src.size();
        for (int i = 0; i < size / 2; i++) {
            buffer.offerFirst(src.pollFirst());
        }
        while (!src.isEmpty()) {
            dest.offerFirst(src.pollFirst());
        }
        while (!buffer.isEmpty()) {
            src.offerFirst(buffer.pollFirst());
        }
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return false;
    }
}