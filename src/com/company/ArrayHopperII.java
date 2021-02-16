package com.company;

public class ArrayHopperII {
    public static void main(String[] args) {
        ArrayHopperII s = new ArrayHopperII();
        int[] array = new int[] {1, 2, 0};
        System.out.println(s.minJump(array));   // 2
        System.out.println(s.minJumpI(array));

        array = new int[] {3, 3, 1, 0, 4};
        System.out.println(s.minJump(array));   // 2
        System.out.println(s.minJumpI(array));

        array = new int[] {2, 1, 1, 0, 2};
        System.out.println(s.minJump(array));   // -1
        System.out.println(s.minJumpI(array));

        array = new int[] {10,0,0,0};
        System.out.println(s.minJump(array));   // 1
        System.out.println(s.minJumpI(array));  // 1
    }

    // Assumptions: array is not null and is not empty
    // Method 1:
    // M[i] represents the min number of jumps from the i-th element to the last element
    // M[i] =  Case 1.1: can reach end from i directly
    //                   i + array[i] >= array.length - 1   =>  M[i] = 1;
    //         Case 1.2: jump to end by passing j, i+1 <= j <= i+array[i]
    //                   i+1, ..., i+array[i] can reach end
    //                   M[i] = min(M[j] + 1), where i+1 <= j <= i+array[i]
    // fill M from right to left
    // Time O(n^2)
    // Space O(n)
    public int minJump(int[] array) {
        // Write your solution here
        // Assumptions: array is not null and is not empty
        int length = array.length;
        int[] minJump = new int[length];
        minJump[length - 1] = 0;
        for (int i = length - 2; i >= 0; i--) {
            minJump[i] = -1;    // initialized as unreachable
            for (int j = i; j <= length - 1; j++) {
                // check all the positions following i
                if (minJump[j] != -1 && i + array[i] >= j) {
                    // j can reach end && i can reach j
                    if (minJump[i] == -1 || minJump[i] > minJump[j] + 1) {
                        // update minJump[i] when first time i can reach end by passing j
                        // or there is a j position with less jumps to the end
                        minJump[i] = minJump[j] + 1;
                    }
                }
            }
        }
        return minJump[0];
    }

    // Method 2:
    // M[i] represents the min number of jumps from the 0th element to the ith element
    // fill M from left to right
    // Time O(n^2)
    // Space O(n)
    public int minJumpI(int[] array) {
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
