package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueByTwoStacks {
    public static void main(String[] args) {
        QueueByTwoStacks queue = new QueueByTwoStacks();
        queue.offer(1);
        System.out.println(queue.peek());       // 1
        System.out.println(queue.size());       // 1
        System.out.println(queue.isEmpty());    // false
        System.out.println(queue.poll());       // 1
        System.out.println(queue.peek());       // null
        System.out.println(queue.size());       // 0
        System.out.println(queue.isEmpty());    // true
    }
    /**
     * Assumption
     * The elements in the queue are all Integers.
     * poll() and peek() return Integer, when the queue is empty, poll() and peek() should return null
     * size() should return the number of elements buffered in the queue.
     * isEmpty() should return true if there is no element buffered in the queue, false otherwise.
     * Result:
     * a.一句话解释 high-level idea
     * how to realize FILO with stack, when we move elements from one stack to another stack, the order of the elements are reversed
     * b. 每个重要数据结构和变量的物理意义
     * Stack1: input stack
     * Stack2: output stack
     * c. 在mid-level的层面解释算法，例如初始化，每一步干什么，终止条件，或者base case + recursion rule 等
     *
     * stack1: is the only stack to store new elements when adding a new element into the queue
     * stack1.push()								-- O(1)
     * stack2: is the only stack to pop old elements out of the queue.
     * Case 1: If stack2 is not empty: then we can just pop			-- O(1) best
     * Case 2: If stack2 is empty						-- O(n) worst
     * move all elements from stack 1 to stack2 one by one
     * then pop out the top element from Stack2
     *
     * d. 时间 空间复杂度
     * offer(), size(), isEmpty()  -- O(1)
     * poll(), peek() -- amortized O(1)
     * 1 O(n) move can be followed by n time O(1) pop() for poll() method and at least n times O(1) peek for peek() method
     * */
    Deque<Integer> in;
    Deque<Integer> out;

    public QueueByTwoStacks() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }

    public Integer poll() {
        if (!out.isEmpty()) {
            return out.pollFirst();
        }
        move(in, out);
        return out.isEmpty() ? null : out.pollFirst();
    }

    private void move(Deque<Integer> in, Deque<Integer> out) {
        if (!out.isEmpty()) {
            return;
        }
        while (!in.isEmpty()) {
            out.offerFirst(in.pollFirst());
        }
    }

    public void offer(int element) {
        in.offerFirst(element);
    }

    public Integer peek() {
        if (!out.isEmpty()) {
            return out.peekFirst();
        }
        move(in, out);
        return out.isEmpty() ? null : out.peekFirst();
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
