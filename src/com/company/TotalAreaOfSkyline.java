package com.company;
import java.util.*;

public class TotalAreaOfSkyline {
    public static void main(String[] args) {
        TotalAreaOfSkyline s = new TotalAreaOfSkyline();
        List<Building> list1 = new ArrayList<>();

        List<Building> list2 = new ArrayList<>();
        Building b4 = new Building(3, 4, 5);
        Building b5 = new Building(1, 5, 2);
        Building b6 = new Building(0, 2, 3);
        list2.add(b4);
        list2.add(b5);
        list2.add(b6);
        System.out.println(s.totalArea(list2)); // 15

        Building b2 = new Building(2, 4, 2);
        Building b1 = new Building(1, 3, 1);
        Building b3 = new Building(7, 8, 1);
        list1.add(b2);
        list1.add(b1);
        list1.add(b3);
        System.out.println(s.totalArea(list1)); // 5
    }


    static class Building {
        int start;
        int end;
        int height;

        public Building(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }

    class Point {
        int start;
        int end;
        int height;
        boolean startPoint;

        public Point(int start, int end, int height, boolean startPoint) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.startPoint = startPoint;
        }
    }

    // Time O(nlogn)
    // Space O(n)
    public int totalArea(List<Building> buildings) {
        if (buildings == null || buildings.size() == 0) {
            return 0;
        }
        // Step 1: convert List<Building> to points in a minHeap
        PriorityQueue<Point> points = convert(buildings);
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.height == p2.height) {
                    return 0;
                }
                return p1.height > p2.height ? -1 : 1;
            }
        });
        Point firstPoint = points.poll();
        int preX = firstPoint.start;
        maxHeap.offer(firstPoint);
        // Step 2: after sorting, we iterate through the six points.
        //	for each point x <left_x, right_x, h_x, yes/not>
        int totalArea = 0;
        while (!points.isEmpty()){
            Point curr = points.poll();
            //	before calculating the area, we need to do a while loop to pop out invalid height:
            //	if top element, right < current x’s value
            //	keep popping …		// lazy deletion
            int currX = curr.startPoint ? curr.start : curr.end;
            while (!maxHeap.isEmpty() && maxHeap.peek().end < currX) {
                maxHeap.poll();
            }
            //	Case 2.1: if it is a left point,
            //		Calculate the area by using |left_x - prev_x| x maxHeap.top()
            //						width			height
            //		insert the left point into the maxHeap;
            int currHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek().height;
            if (curr.startPoint) {
                totalArea += (currX - preX) * currHeight;
                preX = currX;
                maxHeap.offer(curr);
            } else {
                //	Case 2.2: if it is right point,
                //		Calculate the area by using |right_x - prev_x| x maxHeap.top()
                //						width			height
                //		DO NOT insert the left point into the maxHeap;
                totalArea += (currX - preX) * currHeight;
                preX = currX;
            }
        }
        return totalArea;
    }

    // put all start and end points in minHeap according
    // sort according to their x value
    private  PriorityQueue<Point> convert(List<Building> buildings) {
        PriorityQueue<Point> points = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int o1XValue = o1.startPoint ? o1.start : o1.end;
                int o2xValue = o2.startPoint ? o2.start : o2.end;
                if (o1XValue == o2xValue) {
                    return 0;
                }
                return o1XValue < o2xValue ? -1 : 1;
            }
        });
        for (Building x : buildings) {
            points.add(new Point(x.start, x.end, x.height, true));
            points.add(new Point(x.start, x.end, x.height, false));
        }
        return points;
    }
}
