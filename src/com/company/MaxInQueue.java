package com.company;
import java.util.ArrayDeque;
import java.util.Queue;

public class MaxInQueue {
    public int maxInQueue(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int max = queue.poll();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxInQueue s = new MaxInQueue();
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(queue);
        System.out.println(s.maxInQueue(queue));

        queue.offer(1);
        queue.offer(2);
        queue.offer(5);
        System.out.println(queue);
        System.out.println(s.maxInQueue(queue));
    }
}
