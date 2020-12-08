package com.company;
import java.util.*;

public class MajorityNumberI {
    public static void main(String[] args) {
        MajorityNumberI s = new MajorityNumberI();
        int[] array = new int[] {1, 2, 1, 2, 2};
        System.out.println(s.majority(array));
        System.out.println(s.majorityI(array));
        System.out.println(s.majorityII(array));
        System.out.println();
        array = new int[] {1, 2, 1, 2, 1};
        System.out.println(s.majority(array));
        System.out.println(s.majorityI(array));
        System.out.println(s.majorityII(array));
    }

    // Assumption:
    // 1. array is not null and is not empty
    // 2. majority number guarantees to exist
    // Method 3: Majority voting algorithm - (Boyer Moore algorithm)
    // majority number: m / N > 1 / 2
    // Removing two different elements from the input does not change the majority element.
    // Case 1: two elements removed are not majority number
    //         m / (N - 2) > 1 / 2
    // Case 2: removed one majority number and one non-majority number:
    //         (m - 1) / (N - 2) > 1 / 2
    // Time O(n)
    // Space O(1)
    public int majority(int[] array) {
        int candidate = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (count == 0) {  // assign a new candidate
                count++;
                candidate = array[i];
            } else if (candidate == array[i]) {  // cannot remove, update count
                count++;
            } else {  // remove two different number
                count--;
            }
        }
        return candidate;
    }

    // Method 2: heap sort + return mid
    // Time: O(nlogn)
    // Space: O(1)
    public int majorityII(int[] array) {
        heapsort(array);
        return array[array.length / 2];
    }

    public void heapsort(int[] array) {
        // Step 1: heapify the input array to maxHeap.      - O(n)
        heapify(array); // O(n)
        // Step 2: n times poll and percolateDown operation - O(nlogn)
        //         swap the polled element with end element in the heap
        //         (last element is sorted, heapSize--)
        //         percolateDown first element to maintain heap property for the unsorted part in heap
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(array, 0, endIdx);
            percolateDown(array, 0, endIdx - 1);
        }
    }

    public void heapify(int[] array) {
        int endIdx = array.length - 1;
        for (int i = (endIdx - 1) / 2; i >= 0; i--) {
            percolateDown(array, i, endIdx);
        }
    }

    private void percolateDown(int[] array, int i, int endIdx) {
        while (2 * i + 1 <= endIdx) {
            // need to get new leftChildIdx
            int leftChildIdx = 2 * i + 1;
            int rightChildIdx = 2 * i + 2;
            int swapCandidate = leftChildIdx;
            while (rightChildIdx <= endIdx && array[rightChildIdx] > array[swapCandidate]) {
                swapCandidate = rightChildIdx;
            }
            if (array[swapCandidate] > array[i]) {
                swap(array, i, swapCandidate);
            } else {
                break;
            }
            i = swapCandidate;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Method 1:HashMap for counting.
    // Time: O(n)
    // Space: O(n)
    public int majorityI(int[] array) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : array) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        double half = array.length / 2;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > half) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
