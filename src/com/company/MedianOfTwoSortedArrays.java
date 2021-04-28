package com.company;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays s = new MedianOfTwoSortedArrays();
        // 0, 1, 2, 3
        // (1th + 2th) / 2 = (1 + 2) / 2 = 1.5
        // 0, 1, 2
        // 1th = 1
        int[] A = new int[] {1, 4};
        int[] B = new int[] {2, 3};
        System.out.println(s.median(A, B)); // 2.5

        A = new int[] {1};
        B = new int[] {2, 3};
        System.out.println(s.median(A, B));   // 2.0
    }

    // Assumptions:
    // 1. The two given array are not null and at least one of them is not empty
    // 2. The two given array are guaranteed to be sorted
    // Time O(logk + logk) = O(logk))
    // Space O(logk)
    public double median(int[] A, int[] B) {
        int totalLength = A.length + B.length;
        return totalLength % 2 == 1 ? findKth(A, B, (totalLength + 1) / 2)
                : (findKth(A, B, totalLength / 2) + findKth(A, B,totalLength / 2 + 1)) / 2.0;
    }

    // If we only look at A, currently I am looking at A[i], how do I know whether this element is the one I am looking for?
    // B[k - i - 2] <= A[i] <= B[k - i - 1], to check this O(1)
    // Case 1: If this is satisfied, then A[i] is the kth smallest in the merged results.
    // Case 2: When no,
    //   Case 2.1: A[i] < B[k - i - 2], if the answer exists in A, then it must exists on the right side of A[i]
    //   Case 2.2: A[i] > B[k - i - 1], then the answer must exist on the left side of A[i]
    // The search space has this monotonic property.
    // If we use this binary search and cannot find the answer in A, then use the same logic to find the answer in B.
    private int findKth(int[] A, int[] B, int k) {
        // Assuming 0 <= k < a.length + b.length
        int ansFromA = binarySearch(A, B, k);
        return ansFromA == -1 ? B[binarySearch(B, A, k)] : A[ansFromA];
    }

    private int binarySearch(int[] A, int[] B, int k) {
        // using binary search to find an i in A such that B[k - i - 2] <= A[i] <= B[k - i - 1]
        // return -1 if no such i. Otherwise, teh found i will be returned.
        int left = 0;
        int right = Math.min(k, A.length) - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < ReadFromIndex(B, k - mid - 2)) {
                left = mid + 1;
            } else if (A[mid] > ReadFromIndex(B, k - mid - 1)) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return  -1;
    }

    private int ReadFromIndex(int[] array, int index) {
        if (index < 0) {
            // mid is too big in A
            return Integer.MIN_VALUE;
        } else if (index >= array.length) {
            // mid is too small in A
            return Integer.MAX_VALUE;
        } else {
            return array[index];
        }
    }

    // in the subarray of a starting from index aleft, and subarray b starting from
    // index bleft, find the kth smallest element among these two subarrays
    private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
        // three base cases:
        // 1. we already eliminate all the elements in a
        // 2. we already eliminate all the elements in b
        // 3. when k is reduced to 1, don't miss this base case
        // The reason why we have this as base case is in the following logic
        // we need k >= 2 to make it work
        if (aleft >= a.length) {
            return b[bleft + k - 1];
        }
        if (bleft >= b.length) {
            return a[aleft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }
        // we compare the k/2 th element in a's subarray
        // and the k/2 the element in b's subarray
        // to determine which k/2 partition can be surely included in the smallest k elements
        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        if (aval <= bval) {
            return kth(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return kth(a, aleft, b, bmid + 1, k - k / 2);
        }
    }

    // Method 1: two pointers
    // use two pointers i and j to traverse A and B loop k rounds to find the kth smallest
    // value by moving forward the pointer pointing to smaller value in each round
    // Time O(k)
    // Space O(1)
    public double medianI(int[] a, int[] b) {
        int total = a.length + b.length;
        if (total == 1) {
            return a.length == 0 ? b[0] : a[0];
        }
        int halfIdx = (total - 1) / 2;
        int kSmallest = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int t = 0; t <= halfIdx; t++) {
            if (i < a.length && j < b.length) {
                kSmallest = a[i] < b[j] ? a[i++] :b[j++];
            } else if (i < a.length) {
                kSmallest = a[i++];
            } else {
                kSmallest = b[j++];
            }
        }
        if (total % 2 != 0) {
            return kSmallest;
        }
        int nextSmallest;
        if (i < a.length && j < b.length) {
            nextSmallest = a[i] < b[j] ? a[i] : b[j];
        } else if (i < a.length) {
            nextSmallest = a[i];
        } else {
            nextSmallest = b[j];
        }
        return ((double)kSmallest + nextSmallest) / 2;
    }
}
