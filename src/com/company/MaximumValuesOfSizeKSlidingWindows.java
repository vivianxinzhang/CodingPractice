package com.company;
import java.util.*;

public class MaximumValuesOfSizeKSlidingWindows {
    public static void main(String[] args) {
        MaximumValuesOfSizeKSlidingWindows s = new MaximumValuesOfSizeKSlidingWindows();
        int[] array = new int[] {1, 2, 3, 2, 4, 2, 1};
        System.out.println(s.maxWindows(array, 3));
        System.out.println(s.maxWindowsI(array, 3));
        System.out.println(s.maxWindowsII(array, 3));
    }

    // Assumptions:
    // 1. array is not null or not empty, 2. k >= 1 and k <= a.length
    // Method 3: use a decreasing deque to maintain max candidates
    // Time O(n)
    // Space O(k)
    public List<Integer> maxWindows(int[] array, int k) {
        List<Integer> max = new ArrayList<>();
        // use a descending deque to solve this problem,
        // we store the index instead of the actual value in the deque,
        // and we make sure:
        // 1. the deque only contains index in the current sliding window
        // 2. for any index, the previous index with smaller value is discarded from the deque
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            // discard any index with smaller value than index i
            // 有又大又新的进来之后 之前又小又旧的不可能是max了
            // while (the last element in max_candidates < current element):
            // remove the last element in max_candidate
            while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
                deque.pollLast();
            }
            // remove the oldest element in the sliding window: if this oldest element
            // is the one at the front of max_candidates, then we should remove it as well.
            // it is possible the head element is out of the current
            // sliding window so we might need to discard it as well
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // put the current element into max_candidates.
            deque.offerLast(i);
            // update result
            if (i >= k - 1) {
                max.add(array[deque.peekFirst()]);
            }
        }
        return max;
    }

    // Method 2: optimize finding max using maxHeap with lazy evaluation
    // Time O((nlogn)
    // Space O(n)
    public List<Integer> maxWindowsII(int[] array, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? 1 : -1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            maxHeap.offer(new Pair(array[i], i));
            if (i >= k -1) {
                int currMax = getMaxInSlidingWindow(maxHeap, i - k + 1);
                result.add(currMax);
            }
        }
        return result;
    }

    // Lazy evaluation:
    // We know the index range for our sliding window and we also the index of
    // every element pushed onto the max heap, so we can use the index information to check
    // whether this max from the max heap actually falls into our sliding window.
    private int getMaxInSlidingWindow(PriorityQueue<Pair> maxHeap, int left) {
        while (maxHeap.peek().index < left) {
            maxHeap.poll();
        }
        return maxHeap.peek().value;
    }

    class Pair {
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    // Method 1: brute force
    // Time O((n-k+1) * k)
    // Space O(1)
    public List<Integer> maxWindowsI(int[] array, int k) {
        List<Integer> result = new ArrayList<>();
        for (int i = k - 1; i < array.length; i++) {
            int currMax = findMax(array, i - k + 1, i);
            result.add(currMax);
        }
        return result;
    }

    private int findMax(int[] array, int left, int right) {
        int max = array[left];
        for (int i = left + 1; i <= right; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }
}
