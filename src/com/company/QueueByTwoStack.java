package com.company;
import java.util.*;

public class QueueByTwoStack {
    Deque<Integer> in;
    Deque<Integer> out;
    public QueueByTwoStack() {
        // Write your solution here.
        in = new ArrayDeque<>();
        out =	new ArrayDeque<>();
    }

    private void reshuffleIfNecessary() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollFirst());
            }
        }
    }

    public Integer poll() {
        reshuffleIfNecessary();
        return out.isEmpty() ? null : out.pollFirst();
    }

    public void offer(int element) {
        in.offerFirst(element);
    }

    public Integer peek() {
        reshuffleIfNecessary();
        return out.isEmpty() ? null : out.peekFirst();
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
