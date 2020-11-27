package com.company;
import java.util.*;

public class CommonNumbersOfTwoArraysI {
    public static void main(String[] args) {
        CommonNumbersOfTwoArraysI s = new CommonNumbersOfTwoArraysI();
        int[] A = new int[] {1, 2, 3};
        int[] B = new int[] {3, 1, 4};
        System.out.println(s.common(A, B));
    }

    // Assumption:
    // 1. Both arrays are not null.
    // 2. There are no duplicate numbers in each of the two arrays respectively.
    // Time: average O(m + n)
    // Space: O(m)
    public List<Integer> common(int[] A, int[] B) {
        // Assume size A < size B
        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(B);
        HashSet<Integer> setA = new HashSet<>();
        // Step 1: Insert all numbers from array a into a hashset
        // O(m) in average, O(m^2) in worst case
        for (int num : A) {
            setA.add(num);
        }
        // Step 2: For each element in array b, check if it’s in the HashSet or not
        // O(n) in average, O(m*n) in worst case
        // 单个元素查找 average O(1)  worst case 为hashset 的 size O(m)
        for (int num : B) {
            if (setA.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }
}
