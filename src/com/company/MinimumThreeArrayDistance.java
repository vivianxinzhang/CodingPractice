package com.company;

public class MinimumThreeArrayDistance {
    public static void main(String[] args) {
        MinimumThreeArrayDistance s = new MinimumThreeArrayDistance();

        int[] a = new int[] {1, 2, 3};
        int[] b = new int[] {4, 5};
        int[] c = new int[] {3, 4};
        System.out.println(s.minDistance(a, b, c));     // 2

         a = new int[] {625,626,627,629,630,631,632,633,636,638,639,640,641,642,643,644,647,649,650,653,654,656,657,658,660,662,664,666,667,670,672,673,676,678,679,680,681,683,684,685,686,687,688,689,692,697,698,699,700,701,702,704,705,706,709,710,713,714,716,717,718,719,720,721,724,726,728,729,735,737,738,739,740,743,746,747,748,749,751,752,753,754,757,758,761,762,764,765,767,771,772,773,774,775,776,777,778,780,786,787,788,789,791,794,795,798,800,801,803,805,806,808,811,812,814,817,819,821,822,824,825,827,828,829,830,833,834,839,840,841,844,846,847,848,849,850,852,857,859,861,862,863,865,866};
         b = new int[]{336,338,346,348,355,356,359,364,366,370,373,374,375,376,378,379,382,383,393,405,407,410,411,412,416,418,419,423,424,426,427,428,432,434,439,442,445,447,450,451};
         c =new int[]{578,585,589,592,597,607,608,614,622,648,658};
        System.out.println(s.minDistance(a, b, c));     // 348
    }

    // Assumptions:
    // The given three arrays are not null or empty.
    // Method 2:
    // Time O(a.length + b.length + c.length))
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
