package com.company;

public class ArrayHopperI {
    public static void main(String[] args) {
        ArrayHopperI s = new ArrayHopperI();
        int[] array = new int[] {1};
        System.out.println(s.canJump(array));

        array = new int[] {1, 2, 0};
        System.out.println(s.canJump(array));
    }

    // Recommended solution:
    // Method 2: DP, canJump[i] means from index i, can jump to index array.length - 1
    // Time O(n^2)
    // Space O(n)
    public boolean canJump(int[] array) {
        // Write your solution here
        boolean[] M = new boolean[array.length];
        M[array.length - 1] = true;
        for (int i = array.length - 2; i >= 0; i--) {
            if (i + array[i] >= array.length - 1) {     // can jump to end directly from i
                M[i] = true;
                continue;
            }
            for (int j = 1; j <= array[i]; j++) {   // can jump [1, array[i]] steps from i
                if (M[i + j] == true) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }
}
