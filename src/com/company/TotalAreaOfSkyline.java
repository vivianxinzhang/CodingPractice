package com.company;
import java.util.*;

public class TotalAreaOfSkyline {
    public static void main(String[] args) {
        TotalAreaOfSkyline s = new TotalAreaOfSkyline();

        List<Building> list0 = null;
        System.out.println(s.totalArea(list0));    // 0

        list0 = Arrays.asList(new Building(0, 1, 2));
        System.out.println(s.totalArea(list0));    // 2

        List<Building> list1 = new ArrayList<>();
        List<Building> list2 = new ArrayList<>();
        Building b4 = new Building(3, 4, 5);
        Building b5 = new Building(1, 5, 2);
        Building b6 = new Building(0, 2, 3);
        list2.add(b4);
        list2.add(b5);
        list2.add(b6);
        System.out.println(s.totalArea(list2));    // 15

        Building b2 = new Building(2, 4, 2);
        Building b1 = new Building(1, 3, 1);
        Building b3 = new Building(7, 8, 1);
        list1.add(b2);
        list1.add(b1);
        list1.add(b3);
        System.out.println(s.totalArea(list1));    // 5
    }

    class Point {
        int start;
        int end;
        int height;
        boolean isStart;

        public Point(int start, int end, int height, boolean isStart) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.isStart = isStart;
        }
    }

    // Time O(nlogn)
    // Space O(n)
    public int totalArea(List<Building> buildings) {
        if (buildings == null || buildings.size() == 0) {
            return 0;
        }
        // Step 1: convert List<Building> to points in a pointsMinHeap
        PriorityQueue<Point> pointsMinHeap = convert(buildings);
        PriorityQueue<Point> heightMaxHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.height == p2.height) {
                    return 0;
                }
                return p1.height > p2.height ? -1 : 1;
            }
        });
        Point firstPoint = pointsMinHeap.poll();
        int preX = firstPoint.start;
        heightMaxHeap.offer(firstPoint);
        // Step 2: after sorting, we iterate through the six points.
        // for each point x <left_x, right_x, h_x, yes/not>
        int totalArea = 0;
        while (!pointsMinHeap.isEmpty()){
            Point cur = pointsMinHeap.poll();
            //	before calculating the area, we need to do a while loop to pop out invalid height:
            //	if top element, right < current x’s value
            //	keep popping …		// lazy deletion
            int curX = cur.isStart ? cur.start : cur.end;
            while (!heightMaxHeap.isEmpty() && heightMaxHeap.peek().end < curX) {
                heightMaxHeap.poll();
            }
            //	Case 2.1: if it is a left point,
            //		Calculate the area by using |left_x - prev_x| * maxHeap.top()
            //						width			height
            //		insert the left point into the maxHeap;
            int curMaxHeight = heightMaxHeap.isEmpty() ? 0 : heightMaxHeap.peek().height;
            if (cur.isStart) {
                totalArea += (curX - preX) * curMaxHeight;
                preX = curX;
                heightMaxHeap.offer(cur);
            } else {
                //	Case 2.2: if it is right point,
                //		Calculate the area by using |right_x - prev_x| * maxHeap.top()
                //						                   width			height
                //		DO NOT insert the right point into the maxHeap;
                totalArea += (curX - preX) * curMaxHeight;
                preX = curX;
            }
        }
        return totalArea;
    }

    // put all start and end points in a pointsMinHeap, sort according to their x value
    private PriorityQueue<Point> convert(List<Building> buildings) {
        PriorityQueue<Point> points = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int o1XValue = o1.isStart ? o1.start : o1.end;
                int o2xValue = o2.isStart ? o2.start : o2.end;
                if (o1XValue == o2xValue) {
                    return 0;
                }
                return o1XValue < o2xValue ? -1 : 1;
            }
        });
        for (Building x : buildings) {
            System.out.println("building height is " + x.height);
            points.add(new Point(x.start, x.end, x.height, true));
            points.add(new Point(x.start, x.end, x.height, false));
        }
        return points;
    }
}
