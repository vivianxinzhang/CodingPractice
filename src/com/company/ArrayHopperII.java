package com.company;

public class ArrayHopperII {
    public static void main(String[] args) {
        ArrayHopperII s = new ArrayHopperII();
        int[] array = new int[] {1, 2, 0};
        System.out.println(s.minJump(array));

        array = new int[] {3, 3, 1, 0, 4};
        System.out.println(s.minJump(array));

        array = new int[] {2, 1, 1, 0, 2};
        System.out.println(s.minJump(array));
    }

    // Assumptions: array is not null and is not empty
    // M[i] represents the min number of jumps from the 0th element to the ith element
    // Time O(n^2)
    // Space O(n)
    public int minJump(int[] array) {
        int length = array.length;
        // minJump record the min number of jumps from 0 to each of the indices
        int[] minJump = new int[length];
        // we do not need to jump for index 0
        minJump[0] = 0;
        for (int i = 1; i < length; i++) {
            minJump[i] = -1;    // initialized as unreachable
            // check all the positions between [0, i-1]
            for (int j = i - 1; j >= 0; j--) {
                // j can reach i && 0 can reach j
                if (j + array[j] >= i && minJump[j] != -1) {
                    // update minJump[i] when first time to reach i
                    // or there is a j position with less jumps to reach i
                    if (minJump[i] == -1 || minJump[i] > minJump[j] + 1) {
                        minJump[i] = minJump[j] + 1;
                    }
                }
            }
        }
        return minJump[length - 1];
    }
}
