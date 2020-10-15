package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println("main");
        Solution s = new Solution();
//        String[] combo = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
//        System.out.println(Arrays.toString(combo));
//        String str = "abc";
        int[] A = {1, 3, 5};
        int[] B = {4, 8};
        System.out.println(s.kthSmallest(A, B, 4));
    }

    // Time O(m + n)
    // Space O(1)
    public List<Integer> common(int[] A, int[] B) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int ele : A) {
            seen.add(ele);
        }
        for (int ele : B) {
            if (seen.contains(ele)) {
                result.add(ele);
            }
        }
        return result;
    }
}
