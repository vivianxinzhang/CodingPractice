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
        heapsort(array);            // - O(nlogn)
        return array[array.length / 2];
    }

    public void heapsort(int[] array) {
        // Step 1: heapify whole input array to maxHeap.                              - O(n)
        heapify(array); // O(n)
        // Step 2: n times poll from maxHeap, and adjust to maintain heap property     - O(nlogn)
        // [0, endIdx] is maxHeap
        // [endId + 1, n - 1] is the sorted part in ascending order
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            // 2.1 swap the first element with end element in the heap
            swap(array, 0, endIdx);
            // 2.2 last element is sorted, endIdx--
            // 2.3 percolateDown the first element in range [0, endIdx]
            // percolateDown first element to maintain heap property for the unsorted part in heap
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
            int swapCandidateIdx = leftChildIdx;
            while (rightChildIdx <= endIdx && array[rightChildIdx] > array[swapCandidateIdx]) {
                swapCandidateIdx = rightChildIdx;
            }
            if (array[swapCandidateIdx] > array[i]) {
                swap(array, i, swapCandidateIdx);
            } else {
                break;
            }
            i = swapCandidateIdx;
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
        // Step 1: put all values of array in hash map, key is the number, value if count of the number
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        double halfCount = array.length / 2;
        // Step 2: iterate through hash map and find which one has more than half of the total count
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > halfCount) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
