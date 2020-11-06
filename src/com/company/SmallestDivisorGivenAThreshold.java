package com.company;

public class SmallestDivisorGivenAThreshold {
    public static void main(String[] args) {
        SmallestDivisorGivenAThreshold s = new SmallestDivisorGivenAThreshold();
        int[] nums = new int[] {1,2,3};
        System.out.println(s.smallestDivisor(nums, 1));   // 1

        nums = new int[] {1,2,3};
        System.out.println(s.smallestDivisor(nums, 6));   // 1

        nums = new int[] {1,2,3};
        System.out.println(s.smallestDivisor(nums, 1000000));   // 1

        nums = new int[] {1,2,5,9};
        System.out.println(s.smallestDivisor(nums, 6));     // 5

        nums = new int[] {2,3,5,7,11};
        System.out.println(s.smallestDivisor(nums, 11));    // 3

        nums = new int[] {19};
        System.out.println(s.smallestDivisor(nums, 5));     // 4
    }
    // Method 1: Brute-Force + Math
    // Time O(n * log(divisor))
    // Space O(1)
    public int smallestDivisor(int[] nums, int threshold) {
        if (threshold < nums.length) {
            return -1;
        }
        return binarySearch(nums, 1, max(nums), threshold);
    }

    private int max(int[] nums) {
        int max = nums[0];
        for (int ele : nums) {
            max = Math.max(max, ele);
        }
        return max;
    }

    // Method 1: Brute-Force + Binary Search
    // Time O(n * log(divisor))
    // Space O(1)
    public int smallestDivisorII(int[] nums, int threshold) {
        if (threshold < nums.length) {
            return -1;
        }
        int left = 1;
        int right = 2;
        while (computeSum(nums, right) > threshold) {
            left = right;
            right *= 2;
        }
        return binarySearch(nums, left, right, threshold);
    }

    private long computeSum(int[] nums, int x) {
        long s = 0;
        for (int n : nums) {
            s += n / x + (n % x == 0 ? 0 : 1);
        }
        return s;
    }

    private int binarySearch(int[] nums, int left, int right, int threshold) {
        while (left < right - 1) {
            int pivot = left + ((right - left) >> 1);
            long num = computeSum(nums, pivot);
            if (num > threshold) {
                left = pivot;
            } else {
                right = pivot;
            }
        }
        // at the end of loop, left > right,
        // computeSum(right) > threshold
        // computeSum(left) <= threshold
        // --> return left
        if (computeSum(nums, left) <= threshold) {
            return left;
        }
        if (computeSum(nums, right) <= threshold) {
            return right;
        }
        return -1;
    }

    // Method 0: brute-force
    // Time O(n * divisor)
    // Space O(1)
    public int smallestDivisorI(int[] nums, int threshold) {
        int divisor = 1;
        while (computeSum(nums, divisor) > threshold) {
            divisor++;
        }
        return divisor;
    }
}
