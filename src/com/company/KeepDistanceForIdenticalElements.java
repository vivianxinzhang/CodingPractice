package com.company;
import java.util.*;

public class KeepDistanceForIdenticalElements {
    public static void main(String[] args) {
        KeepDistanceForIdenticalElements s = new KeepDistanceForIdenticalElements();
        System.out.println("Put [1, 1]:");
        System.out.println(Arrays.toString(s.keepDistance(1)));
        System.out.println(Arrays.toString(s.keepDistanceI(1)));
        System.out.println(Arrays.toString(s.keepDistanceII(1)));

        System.out.println("Put [1, 1], [2, 2]:");
        System.out.println(Arrays.toString(s.keepDistance(2)));
        System.out.println(Arrays.toString(s.keepDistanceI(2)));
        System.out.println(Arrays.toString(s.keepDistanceII(2)));

        System.out.println("Put [1, 1], [2, 2], [3, 3]:");
        System.out.println(Arrays.toString(s.keepDistance(3)));
        System.out.println(Arrays.toString(s.keepDistanceI(3)));
        System.out.println(Arrays.toString(s.keepDistanceII(3)));
    }

    // Assumptions: k > 0
    // The problem can reduce to how to place each element from [1 to k],
    // among all the permutation, find the correct one(s).
    // Method 3: k levels; put one pair at a time
    // Time O(n!)
    // Space O(n)
    public int[] keepDistance(int k) {
        int[] array = new int[2 * k];
        return helper3(array, k) ? array : null;
    }

    private boolean helper3(int[] array, int n) {
        if (n == 0) {
            return true;
        }
        // try each position that does not has a number yet
        // [ , , , , , ]
        // put a pair of n with distance n
        // first position is i, second position is i + n + 1
        for (int i = 0; i < array.length - n - 1; i++) {
            // [3, , , , 3, ] or [ , 3, , , , 3]
            if (array[i] == 0 && array[i + n + 1] == 0) {
                array[i] = n;
                array[i + n + 1] = n;
                if (helper3(array, n - 1)) {
                    return true;
                }
                // recover previous state
                array[i] = 0;
                array[i + n + 1] = 0;
            }
        }
        return false;
    }

    // Method 2: another method to generate all permutations
    // Time O(n!)
    // Space O(n)
    // res: resulting array, since each element can only appear twice, the size is 2 * k
    // used: array to keep track of how many times the element is used
    // (0: haven't used, 1: used once, 2: used twice)
    // base case: if the pointer has reach the end of the res array, then our res array is all filled -> return true;
    // recursive function: try all the number in the used array (not 2),
    // place first element if not yet placed OR place the second element with the distance i in front of the current idx.
    public int[] keepDistanceII(int k) {
        int[] res = new int[2 * k];
        int[] used = new int[k + 1];
        return helper(res, 0, used) ? res : null;
    }

    private boolean helper(int[] res, int idx, int[] used) {
        if (idx == res.length) return true;
        for (int i = 1; i < used.length; ++i) {
            // case 1: unused -> just place it
            // case 2: used once, find if current idx can be placed (check if has a distance equal to the i)
            if (used[i] == 0 || (used[i] == 1 && idx > i && res[idx - i - 1] == i)) {
                res[idx] = i;
                used[i]++;
                if (helper(res, idx + 1, used)) return true;
                used[i]--;
            }
        }
        return false;
    }

    // Method 1: use the swap-swap method to generate all permutations
    // Time O(n!)
    // Space O(n)
    public int[] keepDistanceI(int k) {
        int[] res = new int[2 * k];
        for (int i = 0; i < k; i++) {
            res[i * 2] = i + 1;
            res[i * 2 + 1] = i + 1;
        }
        // used[i] == true if and only if number i is used once
        boolean[] used = new boolean[k + 1];
        return helper(res, 0, used) ? res : null;
    }

    private boolean helper(int[] array, int index, boolean[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = index; i < array.length; i++) {
            // what is the current number?
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                swap(array, index, i);
                used[cur] = !used[cur];
                if (helper(array, index + 1, used)) {
                    return true;
                }
                swap(array, index, i);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
