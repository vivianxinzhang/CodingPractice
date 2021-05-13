package com.company;

public class KthSmallestInTwoSortedArrays {
    public static void main(String[] args) {
        KthSmallestInTwoSortedArrays s = new KthSmallestInTwoSortedArrays();
        int[] A = new int[] {1, 4, 6};
        int[] B = new int[] {2, 3};
        System.out.println(s.kth(A, B, 3));  // 3

        A = new int[] {1, 2, 3, 4};
        B = new int[] {1};
        System.out.println(s.kth(A, B, 2));  // 1
    }

    // Assumptions:
    // 1. a, b is not null, at least one of them is not empty,
    // 2. k <= a.length + b.length, k >= 1
    // Method 3: Binary Search using recursion
    // 核心：1. search space decreases over time after each iteration
    //      2. target cannot be ruled out
    // Time O(logk)
    // Space O(logk)
    public int kth(int[] a, int[] b, int k) {
        return kth(a, 0, b, 0, k);
    }

    // in the subarray of a starting from index aleft, and subarray b starting from
    // index bleft, find the kth smallest element among these two subarrays
    public int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        // three base cases:
        // 1. we already eliminate all the elements in a
        // 2. we already eliminate all the elements in b
        // 3. when k is reduced to 1, don't miss this base case
        // The reason why we have this as base case is in the following logic
        // we need k >= 2 to make it work
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        // we compare the k/2 th element in a's subarray
        // and the k/2 the element in b's subarray
        // to determine which k/2 partition can be surely
        // included in the smallest k elements
        int amid = aLeft + k/2 - 1;
        int bmid = bLeft + k/2 - 1;
        // if amid out of bound, we want to delete the the elements in b
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        // if bmid out of bound, we want to delete the the elements in a
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        // either first k/2 in a are smaller
        // or b index is out of range
        if (aval <= bval) {
            return kth(a, amid + 1, b, bLeft, k - k/2);
        } else {
            return kth(a, aLeft, b, bmid + 1, k - k/2);
        }
    }

    // Method 3: binary search with Space optimization
    // Time O(logk)
    // Space O(1) iteration O(logk) → O(1)
    public int kthIII(int[] a, int[] b, int k) {
        if (k < 0 || k > a.length + b.length) {
            return -1;
        }
        int aleft = 0, bleft = 0;
        while (k >= 2) {
            if (aleft >= a.length) {
                return b[bleft + k - 1];
            }
            if (bleft >= b.length) {
                return a[aleft + k - 1];
            }
            int amid = aleft + k/2 - 1;
            int bmid = bleft + k/2 - 1;
            int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
            int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
            if (aval <= bval) {
                aleft = amid + 1;
            } else {
                bleft = bmid + 1;
            }
            k -= k/2;
        }
        if (aleft >= a.length) {
            return b[bleft];
        }
        if (bleft >= b.length) {
            return a[aleft];
        }
        return a[aleft] < b[bleft] ? a[aleft] : b[bleft];
    }

    // If we only look at A, currently I am looking at A[i], how do I know whether this element is the one I am looking for?
    // B[k - i - 2] <= A[i] <= B[k - i - 1], to check this O(1)
    // Case 1: If this is satisfied, then A[i] is the kth smallest in the merged results.
    // Case 2: When no,
    //   Case 2.1: A[i] < B[k - i - 2], if the answer exists in A, then it must exists on the right side of A[i]
    //   Case 2.2: A[i] > B[k - i - 1], then the answer must exist on the left side of A[i]
    // The search space has this monotonic property.
    // If we use this binary search and cannot find the answer in A, then use the same logic to find the answer in B.
    // Method 2:
    // Time O(logk + logk) = O(logk))
    // Space O(1)
    public int kthII(int[] A, int[] B, int k) {
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

    // index mid from A:
    // 0 <= mid <= k - 1
    // query in B:
    // 0 <= k - mid - 1 <= k - 1
    // -1 <= k - mid - 2 <= k - 2
    private int ReadFromIndex(int[] array, int index) {
        if (index < 0) {    // index == -1
            // mid is too big in A, mid + 1 elements in A <= a[mid]
            // k - (mid + 1) in B should be <= a[mid]
            // k - (mid + 1) < 0 means mid + 1 is bigger than k
            // there are already more elements in A than k
            return Integer.MIN_VALUE;
        } else if (index >= array.length) {
            // need to have more elements in B in order for a[mid] to be the kth elemnt
            // but no more element, means mid is too small, need to move left border
            return Integer.MAX_VALUE;
        } else {
            return array[index];
        }
    }

    // Method 1: two pointers
    // use two pointers i and j to traverse A and B loop k rounds to find the kth smallest
    // value by moving forward the pointer pointing to smaller value in each round
    // Time O(k)
    // Space O(1)
    public int kthI(int[] a, int[] b, int k) {
        if (k < 0 || k > a.length + b.length) {
            return Integer.MIN_VALUE;
        }
        int kthSmallest = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int count = 0; count < k; count++) {
            if (i < a.length && j < b.length) {
                kthSmallest = a[i] < b[j] ? a[i++] : b[j++];
            } else if (i < a.length) {
                kthSmallest = a[i++];
            } else {
                kthSmallest = b[j++];
            }
        }
        return kthSmallest;
    }
}
