package com.company;
import java.util.*;

public class FirstMissingPositiveII {
    public static void main(String[] args) {
        FirstMissingPositiveII s = new FirstMissingPositiveII();
        int[] array = new int[] {-3,-2,-10,-7,-8};
        System.out.println(s.firstMissingPositive(array));

        array = new int[] {0, 2, 3, 1};
        System.out.println(s.firstMissingPositive(array));

        array = new int[] {3, 4, -2, 1, -4};
        System.out.println(s.firstMissingPositive(array));
    }

    // Time O(nlogn)
    // Space O(logn)
    public int firstMissingPositive(int[] input) {
        // Write your solution here
        if (input == null || input.length == 0) {
            return 1;
        }
        Arrays.sort(input);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] > 0) {
                count++;
                if (input[i] != count) {
                    return count;
                }
            }
        }
        return count == 0 ? 1 : input[input.length - 1] + 1;
    }
}
