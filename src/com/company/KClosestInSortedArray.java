package com.company;

import java.util.Arrays;

public class KClosestInSortedArray {
    public static void main(String[] args) {
        KClosestInSortedArray s = new KClosestInSortedArray();
        int[] array = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.kClosest(array, 2, 3)));
        System.out.println(Arrays.toString(s.kClosestIII(array, 2, 3)));
        // [2, 1, 3]

        array = new int[] {1, 3, 5};
        System.out.println(Arrays.toString(s.kClosest(array, 10, 3)));
        System.out.println(Arrays.toString(s.kClosestIII(array, 10, 3)));
        // [1, 3, 5]

        array = new int[] {1, 4, 6, 8};
        System.out.println(Arrays.toString(s.kClosest(array, 5, 2)));
        System.out.println(Arrays.toString(s.kClosestIII(array, 5, 2)));
        // [4, 6]
    }

    // Assumptions:
    // 1. k >= 0 && k <= array.length
    // 2. if there is a tie, the element with smaller idx is preferred
    // Method 3: optimize space
    // Time O(logn + logk)
    // Space O(1)
    public int[] kClosestIII(int[] array, int target, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        int left = largestSmallerOrEqual(array, target);    // O(logn)
        int right = left + 1;
        // binary search            O(logk)
        while (k >= 1) {
            if (left < 0) {
                right += k;
                break;
            } else if (right >= array.length) {
                left -= k;
                break;
            } else if (k == 1) {
                if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
                    left--;
                } else {
                    right++;
                }
                break;
            }
            int leftMid = left - (k / 2 - 1);
            int rightMid = right + (k / 2 - 1);
            int leftMidVal = leftMid < 0 ? Integer.MAX_VALUE : Math.abs(array[left] - target);
            int rightMidVal = rightMid >= array.length ? Integer.MAX_VALUE : Math.abs(array[right] - target);
            if (leftMidVal <= rightMidVal) {
                left = leftMid - 1;
            } else {
                right = rightMid + 1;
            }
            k -= k / 2;
        }
        // (left[0], right[0])
        int[] res = Arrays.copyOfRange(array, left + 1, right);
        return res;
    }

    // Method 2:
    // Time O(logn + logk)
    // Space O(logk)
    public int[] kClosest(int[] array, int target, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        int[] left = new int[] {largestSmallerOrEqual(array, target)};    // O(logn)
        int[] right = new int[] {left[0] + 1};
        // binary search            O(logk)
        kCloest(array, target, k, left, right);
        // (left[0], right[0])
        int[] res = Arrays.copyOfRange(array, left[0] + 1, right[0]);
        return res;
    }

    // Time O(logn + logk)
    // Space O(logk)
    private void kCloest(int[] array, int target, int k, int[] left, int[] right) {
        if (left[0] < 0) {
            right[0] += k;
            return;
        }
        if (right[0] >= array.length) {
            left[0] -= k;
            return;
        }
        if (k == 1) {
            if (Math.abs(array[left[0]] - target) <= Math.abs(array[right[0]] - target)) {
                left[0]--;
            } else {
                right[0]++;
            }
            return;
        }
        int leftMid = left[0] - (k / 2 - 1);
        int rightMid = right[0] + (k / 2 - 1);
        int leftMidVal = leftMid < 0 ? Integer.MAX_VALUE : Math.abs(array[left[0]] - target);
        int rightMidVal = rightMid >= array.length ? Integer.MAX_VALUE : Math.abs(array[right[0]] - target);
        if (leftMidVal <= rightMidVal) {
            left[0] = leftMid - 1;
            kCloest(array, target, k - k / 2, left, right);
        } else {
            right[0] = rightMid + 1;
            kCloest(array, target, k - k / 2, left, right);
        }
    }

    // Method 1:
    // Time O(logn + k)
    // Space O(1)
    public int[] kClosestI(int[] array, int target, int k) {
        int left = largestSmallerOrEqual(array, target);
        int right = left + 1;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (right >= array.length || (left >= 0 && (Math.abs(array[left] - target)) <= Math.abs(array[right] - target))) {
                res[i] = array[left];
                left--;
            } else {
                res[i] = array[right];
                right++;
            }
        }
        return res;
    }

    private int largestSmallerOrEqual(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array[right] <= target) {
            return right;
        }
        if (array[left] <= target) {
            return left;
        }
        return -1;
    }
}
