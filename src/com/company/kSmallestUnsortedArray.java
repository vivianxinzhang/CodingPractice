package com.company;

import java.util.*;

public class kSmallestUnsortedArray {
    public static void main(String[] args) {
        kSmallestUnsortedArray s = new kSmallestUnsortedArray();

        int[] array = new int[] {1};
        System.out.println(Arrays.toString(s.kSmallest(array, 0)));
        // [1, 2, 3]

        array = new int[] {3, 4, 1, 2, 5};
        System.out.println(Arrays.toString(s.kSmallest(array, 3)));
        // [1, 2, 3]

        array = new int[] {3, 1, 5, 2, 4};
        System.out.println(Arrays.toString(s.kSmallest(array, 4)));
        // [1, 2, 3, 4]
    }

    // Assumptions:
    // A is not null
    // K is >= 0 and smaller than or equal to size of A
    // Return:
    // an array with size K containing the K smallest numbers in ascending order
    // Method 4: quick select
    // Time O(n) => if want answer to be sorted O(n + k + klogk)
    // worst case O(n^2)  => if want answer to be sorted O(n^2 + k + k^2)
    // Space O(1) <= O(logk) worst case O(k)
    // if pivot separates array 1/2 and 1/2
    // O(n + n/2 + n/4 + ... 1) = O(1/(1-1/2) * n) = O(2n) = O(n)
    // if pivot separates array to 1/10 and 9/10
    // O(n + (9/10)n + (9/10)^2*n + ... + 1 = O(1/(1-9/10) * n) = O(10n) = O(n)
    public int[] kSmallestIV(int[] array, int k) {
        // corner case
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        // quick select to find the kth smallest element
        // after calling this method, the first k elements in the array
        // are the k smallest ones(but not sorted)
        quickSelect(array, 0, array.length - 1, k - 1);
        // copy out the first k elements and sort them
        int[] result = Arrays.copyOf(array, k); // O(k)
        Arrays.sort(result);    // O(klogk)
        return result;
    }

    private void quickSelect(int[] array, int left, int right, int target) {
        // like quick sort, we need to do the partition using pivot value
        int pivotIdx = partition(array, left, right);
        // unlike quick sort, we only need to do quick select on at most one partition.
        // if the pivot is already the kth smallest element, we can directly return
        if (pivotIdx == target) {
            return;
        } else if (pivotIdx > target) {
            // only need to recursively call quick select on the left
            // partition if the kth smallest one is in the left part
            quickSelect(array, left, pivotIdx - 1, target);
        } else {
            // only need to recursively call quick select on the right
            // partition if the kth smallest one is in the right part
            quickSelect(array, pivotIdx + 1, right, target);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left, j = right - 1;
        while (i <= j) {
            if (array[i] < pivot) {
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

    // Method 1: sorting
    // Time = O(nlogn)
    // Space = O(1)
    public int[] kSmallestI(int[] array, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        Arrays.sort(array);
        return k >= array.length ? array : Arrays.copyOf(array, k);
    }

    // Method 2: minHeap
    // n sized minHeap -- offline algorithm
    // Step 1: heapify the whole array with minHeap
    // Step 2: pop out the smallest k elements
    // Time = O(n + klogn)
    // Space = O(1)
    public int[] kSmallest(int[] array, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        List<Integer> list = toList(array);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(list);
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    private List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }

    // Time = O(logn)
    // Space = O(n)
    public int[] kSmallestIII(int[] array, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {      // O(nlogn)
            minHeap.offer(array[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    // Method 3: max heap
    // Implementation 2: k sized maxHeap  -- online algorithm
    // Step 1: heapify the first k elements with maxHeap
    // Step 2: traverse through the rest and compare each element with top of maxHeap
    //    Case 1: new element < top of maxHeap
    //            pop out top of maxHeap and insert new element into maxHeap
    //    Case 2: new element >= top of maxHeap
    //		      continue
    // Time  O(k + (n - k)*logk + klogk) ？？O(nlogk + K) ??
    // Space O(k)
    // Assumptions: 1) array is not null 2) k >= 0 and k <= array.length
    public int[] kSmallestII(int[] array, int k) {
        // handle all possible corner cases at the very beginning
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        // offer the first k elements into the max heap O(k)
        for (int i = 0; i < k; i++) {
            maxHeap.offer(array[i]);
        }
        // for the other elements, only offer it into the max heap
        // if it is smaller than the max value in the max heap  O((n-k)logk)
        for (int i = k; i < array.length; i++) {
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] result = new int[k];
        // result is stored in ascending order, start to fill from the last spot
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}
