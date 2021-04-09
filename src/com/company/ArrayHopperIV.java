package com.company;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArrayHopperIV {
    public static void main(String[] args) {
        ArrayHopperIV s = new ArrayHopperIV();

        int[] array = new int[]{1};
        System.out.println(s.minJump(array, 0));    // 0

        array = new int[]{1, 2, 0};
        System.out.println(s.minJump(array, 0));    // 2

        array = new int[]{1, 3, 1, 2, 2};
        System.out.println(s.minJump(array, 2));    // 2

        array = new int[]{3, 3, 1, 0, 0};
        System.out.println(s.minJump(array, 2));    // 2

        array = new int[]{4, 0, 1, 0, 0};
        System.out.println(s.minJump(array, 2));    // -1

        array = new int[]{4, 2, 1, 2, 0, 0};
        System.out.println(s.minJump(array, 0));    // 2
    }

    // Time O(n)
    // Space O(n)
    public int minJump(int[] array, int index) {
        // Write your solution here
        if (index == array.length - 1) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(index);
        boolean[] visited = new boolean[array.length];
        int step = 0;
        // each step generate curr from queue
        // and expand left and right which can be reached from curr
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();
                if (currIdx == array.length - 1) {
                    return step;
                }
                int currJump = array[currIdx];
                int leftIdx = currIdx - currJump;
                int rightIdx = currIdx + currJump;
                if (rightIdx >= array.length - 1) {
                    return step;
                }
                leftIdx = leftIdx >= 0 ? leftIdx : 0;
                for (int j = leftIdx; j <= rightIdx; j++) {
                    if (j != currIdx && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }
        return -1;
    }
}
