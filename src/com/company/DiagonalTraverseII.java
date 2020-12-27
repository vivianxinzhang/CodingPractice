package com.company;
import java.util.*;

public class DiagonalTraverseII {
    public static void main(String[] args) {
        DiagonalTraverseII s = new DiagonalTraverseII();
        List<Integer> one = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> two = Arrays.asList(6, 7);
        List<Integer> three = Arrays.asList(8);
        List<Integer> four = Arrays.asList(9, 10, 11);
        List<Integer> five = Arrays.asList(12, 13, 14);
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(one);
        nums.add(two);
        nums.add(three);
        nums.add(four);
        nums.add(five);
        System.out.println(Arrays.toString(s.findDiagonalOrder(nums)));
        // [1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14]
    }

    // hashtable
    // The matrix is sparse, we can use hashtable to store the elements
    // For each diagonal, i + j are the same
    // key = i + j, 0 <= key <= (R - 1) + (C - 1)
    // M[i+j] := {ordered elements of diagonal i + j}
    // Time O(n)
    // Space O(n)
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (i + j >= result.size()) {
                    result.add(new ArrayList<>());
                }
                result.get(i + j).add(nums.get(i).get(j));
            }
        }
        int count = 0;
        for (List<Integer> list : result) {
            count += list.size();
        }
        int[] res = new int[count];
        int k = 0;
        for (int i = 0; i < result.size(); i++) {
            for (int j = result.get(i).size() - 1; j >= 0; j--) {
                res[k] = result.get(i).get(j);
                k++;
            }
        }
        return res;
    }
}
