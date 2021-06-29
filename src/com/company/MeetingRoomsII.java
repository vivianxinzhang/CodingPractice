package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public static void main(String[] args) {
        MeetingRoomsII s = new MeetingRoomsII();

        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(s.minMeetingRooms(intervals));     // 2
    }

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
}
