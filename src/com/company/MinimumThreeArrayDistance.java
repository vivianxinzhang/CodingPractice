package com.company;

public class MinimumThreeArrayDistance {
    public static void main(String[] args) {
        MinimumThreeArrayDistance s = new MinimumThreeArrayDistance();
        int[] a = new int[] {1, 2, 3};
        int[] b = new int[] {4, 5};
        int[] c = new int[] {3, 4};
        System.out.println(s.minDistance(a, b, c));
    }

    // Assumptions:
    // The given three arrays are not null or empty.
    // Method 2:
    // Time O(min(a.length, b.length, c.length))
    // Space O(1)
    public int minDistance(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        int min = Integer.MAX_VALUE;
        while (i < a.length && j < b.length && k < c.length) {
            int curr = Math.abs(a[i] - b[j]) + Math.abs(b[j] - c[k]) + Math.abs(a[i] - c[k]);
            min = Math.min(min, curr);
            if (a[i] <= b[j] && a[i] <= c[k]) {
                i++;
            } else if (b[j] <= a[i] && b[j] <= c[k]) {
                j++;
            } else {
                k++;
            }
        }
        return min;
    }

    // Method 1:
    // Time O(a.length * b.length * c.length)
    // Space O(1)
    public int minDistanceI(int[] a, int[] b, int[] c) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < c.length; k++) {
                    int curDistance = Math.abs(a[i] - b[j]) + Math.abs(a[i] - c[k]) + Math.abs(b[j] - c[k]);
                    minDistance = Math.min(minDistance, curDistance);
                }
            }
        }
        return minDistance;
    }
}
