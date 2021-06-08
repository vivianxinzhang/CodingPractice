package com.company;

import java.util.HashSet;

public class MissingNumberI {
    public static void main(String[] args) {
        System.out.println(0^1);
        System.out.println(1^1);

        System.out.println(0^2);
        System.out.println(2^2);

        MissingNumberI s = new MissingNumberI();
        int[] array = {1, 2, 3};
        System.out.println(s.missingIII(array));
    }

    // Time O(n)
    // Space O(1)
    public int missing(int[] array) {
        // try to swap the numbers to its corresponding position
        // for the number x, the corresponding position is x - 1
        for (int i = 0; i < array.length; i++) {
            // while array[i] is not i + 1, swap array[i] to its correct position if possible
            while (array[i] != i + 1 && array[i] != array.length + 1) {
                swap(array, i, array[i] - 1);
            }
        }
        // if the missing number is in range 1 - n-1,
        // then we can find it by checking the index i where array[i] != i + 1
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i + 1) {
                return i + 1;
            }
        }
        // if all the numbers of 1 - n-1 are in position, the missing number is n
        return array.length + 1;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Method 3: bit operation
    // Time O(n)
    // Space O(1)
    public int missingIII(int[] array) {
        int n = array.length + 1;
        int xor = 0;
        // xor 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        // after this operation, all the numbers from 1 to n
        // are pair xor'ed except for the missing number
        // since x ^ x = 0, the remaining number is the result
        for (int num : array) {
            xor ^= num;
        }
        return xor;
    }

    // Method 2: use sum  -->  may have Integer overflow issue when doing sum using int
    // Assumption: array is not null
    // Time O(n)
    // Space O(1)
    public int missingII(int[] array) {
        int n = array.length + 1;
        long targetSum = (0L + n) * (n + 1) / 2;
        long actualSum = 0L;
        for (int num : array) {
            actualSum += num;
        }
        return (int) (targetSum - actualSum);
    }

    // Method 1: use HashSet
    // Assumption: array is not null
    // Time O(n)
    // Space O(1)
    public int missingI(int[] array) {
        int n = array.length + 1;
        HashSet<Integer> set = new HashSet<>();
        for (int num : array) {
            set.add(num);
        }
        for (int i = 1; i < n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return n;
    }
}
