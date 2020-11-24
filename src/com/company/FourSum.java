package com.company;
import java.util.*;

public class FourSum {
    public static void main(String[] args) {
        FourSum s = new FourSum();
        int[] array = new int[] {2, 1, 3, 2, 4, 3, 4, 2};
        System.out.println(s.existI(array, 9));
        System.out.println(s.exist(array, 9));

        array = new int[] {1, 2, 2, 3, 2, 4};
        System.out.println(s.existI(array, 9));
        System.out.println(s.exist(array, 9));
    }

    // Assumptions: array is not null, array.length >= 4.
    // Method 1: sort the array first, O(n^3)
    // O(n^3)
    // O(1)
    public boolean existI(int[] array, int target) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 3; i++) {
            for (int j = i + 1; j < array.length - 2; j++) {
                int left = j + 1;
                int right = array.length - 1;
                int curTarget = target - array[i] - array[j];
                while (left < right) {
                    int sum = array[left] + array[right];
                    if (sum == curTarget) {
                        return true;
                    } else if (sum < curTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return false;
    }

    // Method 2: HashMap
    // O(n^3)
    // O(1)
    public boolean exist(int[] array, int target) {
        Map<Integer, Pair> map = new HashMap<>();
        // the order of traversing i, j is not arbitrary, we should guarantee
        // we can always look at the pair with the smallest right index.
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                int pairSum = array[j] + array[i];
                // we need to guarantee there exists another pair with right index
                // smaller than teh current pair's left index
                if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < j) {
                    return true;
                }
                // we only need to store the pair with smallest right index
                if (!map.containsKey(pairSum)) {
                    map.put(pairSum, new Pair(j, i));
                }
            }
        }
        return false;
    }

    // each pair is representing a pair of numbers in the array by their index.
    static class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
