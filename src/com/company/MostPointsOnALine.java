package com.company;
import java.util.*;

public class MostPointsOnALine {
    public static void main(String[] args) {
        MostPointsOnALine s = new MostPointsOnALine();
        Point[] array = new Point[4];
        array[0] = new Point(0, 0);
        array[1] = new Point(1, 1);
        array[2] = new Point(2, 3);
        array[3] = new Point(3, 3);
        System.out.println(s.most(array));
    }

    public int most(Point[] points) {
        // Assumptions: points is not null, and points.length >= 2
        // record the maximum number of points on the same line
        int result = 0;
        // we use each pair of points to form a line
        for (int i = 0; i < points.length; i++) {
            // any line can be represented by a point and a slope
            // we take the point as seed and try to find all possbible slopes
            Point seed = points[i];
            // record the points with same <x, y>
            int same = 1;
            // record the points with same x, for the special case of infinite slope
            int sameX = 0;
            // record the maximum number of points on the same line crossing the seed point
            int most = 0;
            // a map with all possible slopes
            HashMap<Double, Integer> cnt = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                Point tmp = points[j];
                if (tmp.x == seed.x && tmp.y == seed.y) {
                    // handle the points with same <x,y>
                    same++;
                } else if (tmp.x == seed.x) {
                    // handle the points with same x
                    sameX++;
                } else {
                    // otherwise, just calculate the slope and increment the counter
                    // for the calculated slope
                    double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
                    if (!cnt.containsKey(slope)) {
                        cnt.put(slope, 1);
                    } else {
                        cnt.put(slope, cnt.get(slope) + 1);
                    }
                    most = Math.max(most, cnt.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            result = Math.max(result, most);
        }
        return result;
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
