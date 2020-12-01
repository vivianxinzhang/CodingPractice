package com.company;
import java.util.*;

public class CommonElementsInKSortedLists {
    public static void main(String[] args) {
        CommonElementsInKSortedLists s = new CommonElementsInKSortedLists();
        List<Integer> one = Arrays.asList(1, 3, 5, 7);
        List<Integer> zero = Arrays.asList(0, 3, 7);
        List<Integer> two = Arrays.asList(1, 5, 7, 9);
        List<List<Integer>> input = new ArrayList<>();
        input.add(zero);
        input.add(one);
        input.add(two);
        System.out.println(s.commonElementsInKSortedLists(input));
    }

    // Assumptions:
    // 1. input and its elements are not null, and support fast random access
    // 2. List<Integer> sorted in ascending order
    // Method 1: iterative reduction
    // Time O(kn)
    // Space O(1)
    public List<Integer> commonElementsInKSortedLists(List<List<Integer>> input) {
        List<Integer> result = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            result = helper(result, input.get(i));
        }
        return result;
    }

    private List<Integer> helper(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            int compare = a.get(i).compareTo(b.get(j));
            if (compare == 0) {
                result.add(a.get(i));
                i++;
                j++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Method 2: binary reduction
    // Time O(kn * logk)
    // Space O(1)

    // Method 3: play together (heap)
    // Time O(klogk * n)
    // Space O(k)
}
