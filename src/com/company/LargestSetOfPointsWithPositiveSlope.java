package com.company;
import java.util.*;

public class LargestSetOfPointsWithPositiveSlope {
    public static void main(String[] args) {
        LargestSetOfPointsWithPositiveSlope s = new LargestSetOfPointsWithPositiveSlope();

        Point[] points1 = new Point[3];
        points1[0] = new Point(0, 10);
        points1[1] = new Point(1, 3);
        points1[2] = new Point(2, 5);
        System.out.println(s.largest(points1));      // 2

        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 3);
        points[3] = new Point(3, 3);
        System.out.println(s.largest(points));      // 3
    }

    // Assumptions: points is not null
    // Step1: Sort all points according to their x-coordinates. --Time = O(nlogn)
    // Step2: Use the solution of longest ascending subsequence to find solutions with respect to the y-coordinates.
    // Time O(n^2)
    // Space O(nlogn) <- O(nlogn + n)
    public int largest(Point[] points) {
        // we need to sort the points by x ascending and y descending
        // y descending 为了避免把 相同x不同y slope==infinity 的 points 加入结果
        Arrays.sort(points, new MyComparator());
        // similar to longest ascending subsequence
        int result = 0;
        int[] longest = new int[points.length];
        for (int i = 0; i < longest.length; i++) {
            for (int j = 0; j < i; j++) {
                if (points[j].y < points[i].y) {
                    // find max longest[j] among all possible and valid j positions
                    longest[i] = Math.max(longest[i], longest[j]);
                }
            }
            // update longest[i] 继承遗产
            longest[i]++;
            result = Math.max(result, longest[i]);
        }
        return result == 1 ? 0 : result;
    }

    // this comparator will sort the points by x ascending and y descending
    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            // if x are different, sort according to x in ascending order
            // if x are same, sort according to y in descending order
            return p1.x != p2.x ? p1.x - p2.x : p2.y - p1.y;
        }
    }

    // Time O(n^2)
    // Space O(n)
    public int largestI(Point[] points) {
        // we need to sort the points by x ascending and y descending
        Arrays.sort(points, new MyComparator());
        // similar to longest ascending subsequence
        int result = 0;
        int[] longest = new int[points.length];
        for (int i = 0; i < longest.length; i++) {
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                if (points[j].y < points[i].y) {
                    // find max longest[j] among all possible and valid j positions
                    longest[i] = Math.max(longest[i], longest[j] + 1);
                }
            }
            result = Math.max(result, longest[i]);
        }
        return result == 1 ? 0 : result;
    }
}
