package com.company;
import java.util.*;

public class KSmallestInUnsortedArray {
    public static void main(String[] args) {
        KSmallestInUnsortedArray s = new KSmallestInUnsortedArray();
        int[] array = new int[] {3, 4, 1, 2, 5};
        System.out.println(Arrays.toString(s.kSmallest(array, 3)));
        System.out.println(Arrays.toString(s.kSmallestI(array, 3)));
    }

    // Assumptions:
    // 1. A is not null
    // 2. k is >= 0 and k <= array.length
    // Method 1: quick select
    // Time O(n) worst case O(n^2)
    // Space O(logn) worst case O(n)
    public int[] kSmallest(int[] array, int k) {
        // handle corner cases at the beginning
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        // quickselect to find the kth smallest element.
        // after calling this method, the first k elements in the array are
        // the k smallest ones(but not sorted).
        quickSelect(array, k - 1, 0, array.length - 1);
        // copy out the first k elements and sort them.
        int[] result = Arrays.copyOf(array, k);
        Arrays.sort(result);
        return result;
    }

    private void quickSelect(int[] array, int targetIdx, int left, int right) {
        // like quick sort, we need to do the partition using pivot value.
        int pivotIdx = partition(array, left, right);
        // unlike quick sort, we only need to do quickselect on at most one partition.
        // if the pivot is already the kth smallest element, we can directly return.
        if (pivotIdx == targetIdx) {
            return;
        } else if (targetIdx < pivotIdx) {
            // only need to recursively call quick select on the left partition
            // if the kth smallest one is in the left partition.
            quickSelect(array, targetIdx, left, pivotIdx - 1);
        } else {
            // only need to recursively call quick select on the right partition
            // if the kth smallest one is in the right partition.
            quickSelect(array, targetIdx, pivotIdx + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] <= pivot) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Method 2: k sized max heap
    // Time O(n) worst case O(n^2)
    // Space O(logn) worst case O(n)
    public int[] kSmallestI(int[] array, int k) {
        // handle all possible corner cases at the very beginning.
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                // offer the first k elements into max heap
                // Notice: if you want to utilize heapify(), the only thing you can do
                // is to call a certain constructor of PriorityQueue.
                maxHeap.offer(array[i]);
            } else if (array[i] < maxHeap.peek()) {
                // for the other elements, only offer it into the max heap if it is smaller
                // than the max value in the max heap.
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}
