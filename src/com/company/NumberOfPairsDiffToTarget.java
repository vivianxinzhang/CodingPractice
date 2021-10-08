package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfPairsDiffToTarget {
    public static void main(String[] args) {
        NumberOfPairsDiffToTarget s = new NumberOfPairsDiffToTarget();

        int[] array = new int[] {3, 2};
        System.out.println(s.pairs(array, 1));    // 1
        array = new int[] {3, 1, 2, 1};
        System.out.println(s.pairs(array, 2));    // 2
    }

    // Assumptions:
    // 1. There could be elements with duplicate value in the array, and each of the elements is considered different.
    // 2. The given array is not null and has length >= 2.

    // Method 6:
    // 1) Initialize count as 0.
    // 2) Insert all elements of arr[] in an AVL tree. While inserting,
    //    ignore an element if already present in AVL tree.
    // 3) Do following for each element arr[i].
    //    a) Search for arr[i] + k in AVL tree, if found then increment count.
    //    b) Search for arr[i] - k in AVL tree, if found then increment count.
    //    c) Remove arr[i] from AVL tree.
    // Time O(nlogn)
    // Space O(1)

    // Method 5: sorting + two pointers
    // Sort the array arr
    // Take two pointers, l, and r, both pointing to 1st element
    // Take the difference arr[r] – arr[l]
    //      If value diff is K, increment count and move both pointers to next element
    //      if value diff > k, move l to next element
    //      if value diff < k, move r to next element
    // return count
    // Time O(nlogn)
    // Space O(n)
    public int pairsV(int[] array, int target) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < array.length) {
            if (Math.abs(array[right] - array[left]) == target) {
                int leftCount = 1;
                int rightCount = 1;
                left++;
                while (array[left] == array[left - 1]) {
                    leftCount++;
                    left++;
                }
                right++;
                while (right< array.length && array[right] == array[right - 1]) {
                    rightCount++;
                    right++;
                }
                count += leftCount * rightCount;
            } else if (Math.abs(array[right] - array[left]) > target) {
                left++;
            } else {
                right++;
            }
        }
        return count;
    }

    // Method 4: Use Binary Search (Works with duplicates in the array):
    // 1) Initialize count as 0.
    // 2) Sort all numbers in increasing order.
    // 4) Do following for each element arr[i]
    //  a) Binary Search for the first occurrence of arr[i] + k in the sub array arr[i+1, N-1], let this index be ‘X’.
    //  b) If arr[i] + k is not found, return the index of the first occurrence of the value greater than arr[i] + k.
    //  c) Repeat steps a and b to search for the first occurrence of arr[i] + k + 1, let this index be ‘Y’.
    //  d) Increment count with ‘Y – X’.
    // 5) Return count.
    public int pairsIV(int[] array, int target) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int x = firstOccur(array, array[i] + target, i + 1);
            if (x != -1) {
                int y = firstOccur(array, array[i] + target + 1, i + 1);
                count += y - x;
            }
        }
        return count;
    }

    private int firstOccur(int[] array, int target, int left) {
        int right = array.length - 1;
        int res = array.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    // Method 3: Use Sorting + binary search with no duplicates
    // 1) Initialize count as 0
    // 2) Sort all numbers in increasing order.
    // 3) Remove duplicates from array.
    // 4) Do following for each element arr[i]
    //    a) Binary Search for arr[i] + target in subarray from i+1 to n-1.
    //    b) If arr[i] + target found, increment count.
    // 5) Return count.
    // Time O(nlogn)
    // Space O(1)
    public int pairsIII(int[] array, int target) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            if (binarySearch(array, array[i] + target, i + 1, n - 1) != -1) {
                count++;
            }
        }
        return count;
    }

    private int binarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Method 2: using map
    // Time O(n)
    // Space O(n)
    public int pairsII(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : array) {
            Integer c1 = map.get(num - target);
            count += c1 == null ? 0 : c1;
            Integer c2 = map.get(num + target);
            count += c2 == null ? 0 : c2;
            int countNum = map.getOrDefault(num, 0);
            map.put(num, countNum + 1);
        }
        return count;
    }

    // Method 1: brute force
    // Time O(n^2)
    // Space O(1)
    public int pairs(int[] array, int target) {
        int count = 0;
        // Pick all elements one by one
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                // See if there is a pair diff equals target
                if (Math.abs(array[i] - array[j]) == target) {
                    count++;
                }
            }
        }
        return count;
    }
}
