package com.company;
import java.util.*;


public class ThreeSumSmaller {
    public static void main(String[] args) {
        ThreeSumSmaller s = new ThreeSumSmaller();

        int[] array = new int[] {-1, 1, -1, -1};
        System.out.println(s.threeSumSmaller(array, -1));   // 1

        array = new int[] {-2, 0, 1, 3};
        System.out.println(s.threeSumSmaller(array, 2));    // 2
    }

    // given nums = [-2, 0, 1, 3], and target = 2.
    // Return 2. Because there are two triplets which sums are less than 2:
    // [-2, 0, 1]
    // [-2, 0, 3]
    // Time O(n^2)
    // Space O(logn)
    public int threeSumSmaller(int[] num, int target) {
        if (num == null || num.length < 3) {
            return 0;
        }
        Arrays.sort(num);
        int count = 0;
        // fix the first, and find two sum for the rest
        for (int i = 0; i <= num.length - 3; i++) {
            int sum = target - num[i];
            count += twoSumSmaller(num, sum, i + 1, num.length - 1);
        }
        return count;
    }

    private int twoSumSmaller(int[] array, int target, int j, int k) {
        int count = 0;
        while (j < k) {
            if (array[j] + array[k] < target) {
                count += (k - j);
                j++;
            } else {
                k--;
            }
        }
        return count;
    }
}
