package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem {
    public static void main(String[] args) {
        TheSkylineProblem s = new TheSkylineProblem();

        int[][] buildings = new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] res = s.getSkyline(buildings);
        Printer.printMatrix(res);
        // [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]

        buildings = new int[][]{{0,244,8002},{2,65,8062},{4,284,7793},{5,107,7173},{5,125,9395},{5,265,2527},{5,266,4461},{7,210,7548},{12,266,7883},{15,149,7843},{15,164,9440},{18,80,4461},{19,279,1018},{21,46,1816},{22,198,6978},{27,293,7684},{30,206,370},{31,105,4599},{41,108,4934},{43,156,3317},{43,197,9750},{43,205,5274},{44,255,3135},{45,248,818},{49,223,7066},{50,166,1603},{51,122,2559},{52,134,4272},{58,230,2109},{59,92,4118},{67,90,2192},{73,161,8469},{74,103,7381},{74,217,1090},{74,293,7583},{75,154,588},{84,148,9010},{87,111,1173},{98,263,3725},{100,230,7551},{100,296,4323},{109,187,6362},{111,225,2803},{113,286,7727},{116,224,7068},{120,280,2224},{120,284,6650},{123,167,2962},{133,292,5479},{138,256,7026},{139,268,610},{145,254,8063},{155,241,4737},{160,271,3152},{161,181,6477},{180,236,985},{189,285,2724},{201,294,8977},{216,218,8356},{228,253,2799},{228,254,3030},{233,297,7504},{237,281,3012},{238,260,3972},{241,298,6141},{271,273,1020},{275,280,9587},{277,280,8510},{293,299,6089}};
        res = s.getSkyline(buildings);
        Printer.printMatrix(res);
        // expected [[0, 8002], [2, 8062], [5, 9395], [15, 9440], [43, 9750], [197, 8063], [201, 8977], [275, 9587], [280, 8977], [294, 7504], [297, 6141], [298, 6089], [299, 0]]
        // but was: [[0, 8002], [2, 8062], [5, 9395], [197, 8063], [201, 8977], [275, 9587], [280, 8977], [294, 7504], [297, 6141], [298, 6089], [299, 0]]
    }

    // Assumptions:
    // The given array of buildings is not null, the buildings are not null.
    // Time O(nlogn)
    // Space O(n)
    public int[][] getSkyline(int[][] buildings) {
        PriorityQueue<Point> minHeap = convert(buildings);
        PriorityQueue<Point> heightMaxHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.height == o2.height) {
                    return 0;
                }
                return o1.height > o2.height ? -1 : 1;
            }
        });
        List<int[]> list = new ArrayList<>();
        Point pre = minHeap.poll();
        heightMaxHeap.offer(pre);
        int preX = pre.start;
        int preHeight = heightMaxHeap.peek().height;
        list.add(new int[] {preX, preHeight});
        while (!minHeap.isEmpty()) {
            Point cur = minHeap.poll();
            int curX = cur.isStart ? cur.start : cur.end;
            if (cur.isStart) {
                heightMaxHeap.offer(cur);
            }
            while (!heightMaxHeap.isEmpty() && heightMaxHeap.peek().end <= curX) {
                heightMaxHeap.poll();
                System.out.println("inner-loop");
            }
            Point curHeightPoint = heightMaxHeap.peek();
            int curHeight = curHeightPoint == null ? 0 : curHeightPoint.height;
            if (curX != preX && curHeight != preHeight) {
                list.add(new int[] {curX, curHeight});
            }
            preX = curX;
            preHeight = curHeight;
            System.out.println("outer-loop");
        }
        return toArray(list);
    }

    // put all start and end points in a pointsMinHeap, sort according to their x value
    private PriorityQueue<Point> convert(int[][] buildings) {
        PriorityQueue<Point> points = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int o1xValue = o1.isStart ? o1.start : o1.end;
                int o2xValue = o2.isStart ? o2.start : o2.end;
                if (o1xValue == o2xValue) {
                    return 0;
                }
                return o1xValue < o2xValue ? -1 : 1;
            }
        });
        for (int[] building : buildings) {
            // System.out.println("building height is " + x.height);
            points.add(new Point(building[0], building[1], building[2], true));
            points.add(new Point(building[0], building[1], building[2], false));
        }
        return points;
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

    private int[][] toArray(List<int[]> list) {
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
