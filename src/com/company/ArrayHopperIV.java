package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ArrayHopperIV {
    public static void main(String[] args) {
        ArrayHopperIV s = new ArrayHopperIV();

        int[] array = new int[]{1};
        System.out.println(s.minJump(array, 0));    // 0
        array = new int[]{4, 0, 1, 0, 0};
        System.out.println(s.minJump(array, 2));    // -1
        array = new int[]{4, 2, 1, 2, 0, 0};
        System.out.println(s.minJump(array, 0));    // 2
    }

    // Assumptions:
    // The given array is not null and has length of at least 1.
    // Time O(n)
    // Space O(n)
    public int minJump(int[] array, int index) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        boolean[] visited = new boolean[array.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(index);
        visited[index] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // each step generate tmp from queue
                int tmp = queue.poll();
                if (tmp == array.length - 1) {
                    return step;
                }
                // and expand numbers which can be reached from tmp
                int leftIdx = Math.max(tmp - array[tmp], 0);
                int rightIdx = Math.min(tmp + array[tmp], array.length - 1);
                for (int idx = leftIdx; idx <= rightIdx; idx++) {
                    if (!visited[idx]) {
                        queue.offer(idx);
                        visited[idx] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
