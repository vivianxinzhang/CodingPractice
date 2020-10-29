package com.company;

import java.util.Arrays;

public class ArrayHopperIII {
    public static void main(String[] args) {
        ArrayHopperIII s = new ArrayHopperIII();
        int[] array = new int[] {2, 1, 0};
        System.out.println(s.minJump(array));

        array = new int[] {1};
        System.out.println(s.minJump(array));

        array = new int[] {1, 2, 0};
        System.out.println(s.minJump(array));
    }

    // minJump[i] means min # of jumps needed to jump out of the array
    // Time O(n^2)
    // Space O(n)
    public int minJump(int[] array) {
        int[] M = new int[array.length];
        Arrays.fill(M, Integer.MAX_VALUE);
        M[array.length - 1] = array[array.length - 1] > 0 ? 1 : Integer.MAX_VALUE;
        for (int i = array.length - 2; i >= 0; i--) {
            if (i + array[i] > array.length - 1) {     // can jump to end directly from i
                M[i] = 1;
                continue;
            }
            for (int j = 1; j <= array[i]; j++) {   // can jump [1, array[i]] steps from i
                // can reach i + 1, ... i + array[i] index
                if (M[i + j] != Integer.MAX_VALUE) {
                    M[i] = Math.min(M[i], M[i + j] + 1);
                }
            }
        }
        return M[0] == Integer.MAX_VALUE ? -1 : M[0];
    }
}
