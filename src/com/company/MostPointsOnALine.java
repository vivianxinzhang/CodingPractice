package com.company;
import java.util.*;

public class MostPointsOnALine {
    public static void main(String[] args) {
        MostPointsOnALine s = new MostPointsOnALine();

        Point[] array = new Point[2];
        array[0] = new Point(1, 1);
        array[1] = new Point(2, 2);
        System.out.println(s.most(array));  // 2

        array = new Point[4];
        array[0] = new Point(0, 0);
        array[1] = new Point(1, 1);
        array[2] = new Point(2, 3);
        array[3] = new Point(3, 3);
        System.out.println(s.most(array));  // 3

        array = new Point[8];
        array[0] = new Point(1, 1);
        array[1] = new Point(2, 3);
        array[2] = new Point(1, 1);
        array[3] = new Point(2, 3);
        array[4] = new Point(1, 1);
        array[5] = new Point(2, 2);
        array[6] = new Point(3, 4);
        array[7] = new Point(9, 10);
        System.out.println(s.most(array));  // 5
    }

    // Assumptions: points is not null, and points.length >= 2
    // Time O(n^2)
    // Space O(n)
    public int most(Point[] points) {
        // record the maximum number of points on the same line
        int globalMax = 0;
        // we use each pair of points to form a line
        // for for loop 枚举两个点， 两点确定一条直线
        for (int i = 0; i < points.length; i++) {
            // any line can be represented by a point and a slope
            // we take the point as seed and try to find all possible slopes
            // 一个点和斜率可以确定一条直线 用斜率来记录从seed出发所有可能的直线
            Point seed = points[i];
            // record the points with same <x, y>
            int same = 1;
            // record the points with same x, for the special case of infinite slope
            int sameX = 0;
            // record the maximum number of points on the same line crossing the seed point
            // 穿过seed的所有line里 最多有多少个点在同一条线上
            int currMax = 0;
            // a map with all possible slopes
            // key: slope
            // value: count of points on the line passing through seed with key as slope
            HashMap<Double, Integer> cnt = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;   // same point continue
                }
                Point tmp = points[j];
                if (tmp.x == seed.x && tmp.y == seed.y) {
                    // handle the points with same <x,y> values (same location)
                    same++;
                } else if (tmp.x == seed.x) {
                    // handle the points with same x
                    // special case: on the same vertical line
                    // x's are equal, y's are not equal, slope is infinite
                    // 更新穿过 seed 的 vertical line 上最多有多少个点
                    sameX++;
                } else {
                    // otherwise, just calculate the slope and increment the counter
                    // for the calculated slope
                    double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
                    // update count in HashMap
                    if (!cnt.containsKey(slope)) {
                        cnt.put(slope, 1);
                    } else {
                        cnt.put(slope, cnt.get(slope) + 1);
                    }
                    currMax = Math.max(currMax, cnt.get(slope));
                }
            }
            // update currMax, among all the lines passing through seed,
            // what is the maximum number of points on a line
            currMax = Math.max(currMax, sameX) + same;
            // update globalMax
            globalMax = Math.max(globalMax, currMax);
        }
        return globalMax;
    }
}
