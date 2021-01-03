package com.company;
import java.util.*;

public class CheckArrayFormationThroughConcatenation {
    public static void main(String[] args) {
        CheckArrayFormationThroughConcatenation s = new CheckArrayFormationThroughConcatenation();
        int[] array = new int[] {91, 4, 64, 78};
        int[][] pieces = new int[][] {{78}, {4, 64}, {91}};
        System.out.println(s.canFormArray(array, pieces));
        System.out.println(s.canFormArrayI(array, pieces));
    }

    // 1 <= pieces.length <= arr.length <= 100
    // sum(pieces[i].length) == arr.length
    // 1 <= pieces[i].length <= arr.length
    // The integers in arr are distinct.
    // The integers in pieces are distinct
    // (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
    // Time O(n)
    // Space O(n)
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        int[] result = new int[arr.length];
        int idx = 0;
        for (int a : arr) {
            int[] piece = map.getOrDefault(a, new int[0]);
            for (int p : piece)
                result[idx++] = p;
        }
        return Arrays.equals(arr, result);
    }

    // Method 2:
    // Time O(n)
    // Space O(n)
    public boolean canFormArrayI(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++){
            map.put(arr[i], i);
        }
        for (int[] piece : pieces) {
            if (!map.containsKey(piece[0])) {
                return false;
            }
            int idx = map.get(piece[0]);
            for (int i = 1; i < piece.length; i++) {
                idx++;
                if (!map.containsKey(piece[i]) || map.get(piece[i]) != idx) {
                    return false;
                }
            }
        }
        return true;
    }
}
