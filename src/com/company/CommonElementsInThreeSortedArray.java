package com.company;
import java.util.*;

public class CommonElementsInThreeSortedArray {
    public static void main(String[] args) {
        CommonElementsInThreeSortedArray s = new CommonElementsInThreeSortedArray();
        int[] A = new int[] {1, 2, 3, 7};
        int[] B = new int[] {1, 4, 7, 8};
        int[] C = new int[] {0, 1, 7, 9, 10};
        System.out.println(s.common(A, B, C));
    }

    // Assumptions: a, b, c are not null
    // Time: average O(m + n + k)
    // Space: O(1)
    public List<Integer> common(int[] a, int[] b, int[] c) {
        List<Integer> common = new ArrayList<>();
        int ai = 0;
        int bi = 0;
        int ci = 0;
        while (ai < a.length && bi < b.length && ci < c.length) {
            if (a[ai] == b[bi] && a[ai] == c[ci]) {
                common.add(a[ai]);
                ai++;
                bi++;
                ci++;
            } else if (a[ai] <= b[bi] && a[ai] <= c[ci]) {
                ai++;
            } else if (b[bi] <= a[ai] && b[bi] <= c[ci]) {
                bi++;
            } else {
                ci++;
            }
        }
        return common;
    }
}
