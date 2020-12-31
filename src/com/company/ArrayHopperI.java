package com.company;

public class ArrayHopperI {
    public static void main(String[] args) {
        ArrayHopperI s = new ArrayHopperI();
        int[] array = new int[] {1};
        System.out.println(s.canJump(array));   // true

        array = new int[] {1, 2, 0};
        System.out.println(s.canJump(array));   // true
    }

    // Assumption:
    // The given array is not null and has length of at least 1.
    // DP, canJump[i] means from index i, can jump to index array.length - 1
    // M[i] = true if Case 1.1: can reach end from i directly
    //                          i + array[i] >= array.length - 1
    //                Case 1.2: jump to end by passing j, i+1 <= j <= i+array[i]
    //                          any position between i+1, ..., i+array[i] can reach end
    //     false otherwise
    // Time O(n^2)
    // Space O(n)
    public boolean canJump(int[] array) {
        boolean[] M = new boolean[array.length];
        M[array.length - 1] = true;
        for (int i = array.length - 2; i >= 0; i--) {
            // if from index i, it is already possible to jump to the end of the array
            // can jump to end directly from i
            if (i + array[i] >= array.length - 1) {
                M[i] = true;
                continue;
            }
            // if any of the reachable indices from index i is reachable to the end of the array
            for (int j = 1; j <= array[i]; j++) {   // can jump [1, array[i]] steps from i
                if (M[i + j] == true) {
                    M[i] = true;
                    break;
                }
            }
//            for (int j = i + 1; j <= Math.min(i + array[i], array.length - 1); j++) {
//                if (M[j] == true) {
//                    M[i] = true;
//                    break;
//                }
//            }
        }
        return M[0];
    }

    // implementation 2:
    public boolean canJumpI(int[] array) {
        if (array.length == 1) {
            return true;
        }
        boolean[] M = new boolean[array.length];
        for (int i = array.length - 2; i >= 0; i--) {
            // if from index i, it is already possible to jump to the end of the array
            // can jump to end directly from i
            if (i + array[i] >= array.length - 1) {
                M[i] = true;
            } else {
                // if any of the reachable indices from index i is reachable to the end of the array
                for (int j = 1; j <= array[i]; j++) {   // can jump [1, array[i]] steps from i
                    if (M[i + j] == true) {
                        M[i] = true;
                        break;
                    }
                }
            }
        }
        return M[0];
    }
}
