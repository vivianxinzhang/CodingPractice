package com.company;
import java.util.*;

public class SortInSpecifiedOrder {
    public static void main(String[] args) {
        SortInSpecifiedOrder s = new SortInSpecifiedOrder();
        int[] A1 = new int[] {2, 1, 2, 5, 7, 1, 9, 3};
        int[] A2 = new int[] {2, 1, 3};
        System.out.println(Arrays.toString(A1));
        s.sortSpecial(A1, A2);
        System.out.println(Arrays.toString(A1));
    }

    // Time O(n) worst case O(n^2)
    // Space O(n)
    public int[] sortSpecial(int[] A1, int[] A2) {
        // Write your solution here
        if (A1 == null || A1.length <= 1) {
            return A1;
        }
        // Step 1: put all elements of A1 in hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : A1) {
            Integer count = map.get(number);
            if (count == null) {
                map.put(number, 1);
            } else {
                map.put(number, count + 1);
            }
        }
        int i = 0;
        // Step 2: iterate through A2 and get elements out of the map according to the sequence
        for (int number : A2) {
            Integer count = map.get(number);
            if (count != null) {
                while (count > 0) {
                    A1[i++] = number;
                    count --;
                }
                map.remove(number);
            }
        }
        // Step 3: get all other elements out of A1
        for (int entry : map.keySet()) {
            Integer count = map.get(entry);
            while (count > 0) {
                A1[i++] = entry;
                count--;
            }
            // do not need to remove the key from the map while iterating through map
            // remove the key will throw ConcurrentModificationException
        }
        return A1;
    }
}
