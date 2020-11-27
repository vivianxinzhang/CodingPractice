package com.company;
import java.util.*;

public class CommonNumbersOfTwoArraysII {
    public static void main(String[] args) {
        CommonNumbersOfTwoArraysII s = new CommonNumbersOfTwoArraysII();
        int[] A = new int[] {1, 2, 3};
        int[] B = new int[] {3, 1, 4};
        System.out.println(s.common(A, B));
    }

    // Assumption:
    // 1. Both of the two arrays are not null.
    // 2. In any of the two arrays, there could be duplicate numbers.
    // Method 1: two pointers
    // Time: average O(m + n + mlogm + nlogn)
    // Space: O(1)
    public List<Integer> common(int[] A, int[] B) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(A);
        Arrays.sort(B);;
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                result.add(A[i]);
                i++;
                j++;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Assume size A < size B
    // Method 2: hashmap
    // Time: average O(m + n + nlogn), worst case O(m^2 + m*n + nlogn)
    // Space: O(m)
    public List<Integer> commonI(int[] A, int[] B) {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<Integer, Integer> countA = new HashMap<>();
        // Step 1: Insert all numbers from array a into a hashMap
        // O(m) in average, O(m^2) in worst case
        for (int num : A) {
            Integer ct = countA.get(num);
            if (ct != null) {
                countA.put(num, ct + 1);
            } else {
                countA.put(num, 1);
            }
        }
        // Step 2: For each element in array b, check if it’s in the HashSet or not
        // O(n) in average, O(m*n) in worst case
        // 单个元素查找 average O(1)  worst case 为hashset 的 size O(m)
        Arrays.sort(B);
        for (int num : B) {
            Integer ct = countA.get(num);
            if (ct != null && ct > 0) {
                result.add(num);
                countA.put(num, ct - 1);
            }
        }
        return result;
    }
}
