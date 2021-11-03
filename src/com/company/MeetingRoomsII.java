package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public static void main(String[] args) {
        MeetingRoomsII s = new MeetingRoomsII();

        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(s.minMeetingRooms(intervals));     // 2

        intervals = new int[][] {{16,21},{0,11},{6,9},{1,2},{15,36},{4,21}};
        System.out.println(s.minMeetingRooms(intervals));     // 3

        intervals = new int[][] {{16,21},{0,11},{6,9},{1,2},{15,36},{4,21},{12,31},{22,42},{7,29},{21,45},{3,26},{17,18}};
        System.out.println(s.minMeetingRooms(intervals));     // 7
    }

    // Method 2:
    // Sort the intervals by starting time, so it will always find the earliest
    // available meeting once the most recent one is end.
    // Time O(nlogn)
    // Space O(n)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // Arrays.sort(intervals, (int[] a, int[] b) -> (a[0] - b[0]));
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return 0;
                }
                return o1[0] < o2[0] ? -1 : 1;
            }
        });
        // minHeap has the ending time for the last meeting in each room
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) {
                    return 0;
                }
                return o1 < o2 ? -1 : 1;
            }
        });
        minHeap.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            // minHeap.peek() represents the earliest ending time for all last meetings
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        return minHeap.size();
    }

    // Method 1:
    // Time O(nlog(n)) <-- O(2nlog(2n))
    // Space O(n)
    public int minMeetingRoomsI(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        PriorityQueue<Point> minHeap = convert(intervals);
        Point pre = minHeap.poll();
        int preX = pre.x;
        int count = 1;
        int max = 1;
        while (!minHeap.isEmpty()) {
            Point cur = minHeap.poll();
            int curX = cur.x;
            if (cur.isStart) {
                count++;
            } else {
                count--;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    private PriorityQueue<Point> convert(int[][] intervals) {
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x == o2.x) {
                    if ((o1.isStart && o2.isStart) || (!o1.isStart && !o2.isStart)) {
                        return 0;
                    } else {
                        return o1.isStart ? 1 : -1;
                    }
                }
                return o1.x < o2.x ? -1 : 1;
            }
        });
        for (int[] interval : intervals) {
            minHeap.offer(new Point(interval[0], true));
            minHeap.offer(new Point(interval[1], false));
        }
        return minHeap;
    }

    class Point {
        int x;
        boolean isStart;

        public Point(int x, boolean isStart) {
            this.x = x;
            this.isStart = isStart;
        }
    }
}
