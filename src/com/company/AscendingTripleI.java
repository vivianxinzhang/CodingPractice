package com.company;

public class AscendingTripleI {
    public static void main(String[] args) {
        AscendingTripleI s = new AscendingTripleI();
        int[] array = new int[] {1, 5, 2, 4};
        System.out.println(s.existIJK(array));
    }

    // Time O(n^2)
    // Space O(n)
    public boolean existIJK(int[] array) {
        if (array == null || array.length <= 2) {
            return false;
        }
        int[] M = new int[array.length];
        M[0] = 1;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    M[i] = Math.max(M[j] + 1, M[i]);
                    if (M[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // maintain lowest ending for length 1 and length 2 while traversing the array
    // small: lowest ending for length 1
    // medium: lowest ending for length 2
    // Time O(n)
    // Space O(1)
    public boolean existIJKI(int[] array) {
        if (array == null || array.length <= 2) return false;
        int small = Integer.MAX_VALUE, medium = Integer.MAX_VALUE;
        for (int num : array) {
            if (num <= small) {
                // update small if n is smaller than both
                small = num;
            } else if (num <= medium) {
                // update medium only if greater than small but smaller than big
                medium = num;
            } else {
                return true;    // return if you find a number bigger than both
            }
        }
        return false;
    }
}
