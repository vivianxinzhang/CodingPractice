package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingSchedule {
    public static void main(String[] args) {
        MeetingSchedule s = new MeetingSchedule();

        int[][] intervals = new int[][] {{5, 9}, {4, 18}, {9, 17}};
        System.out.println(s.maximumMeetings(intervals));   // 2

        intervals = new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(s.maximumMeetings(intervals));   // 4
    }

    // Time O(n^2)
    // Space O(n)
    public int maximumMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int start1 = o1[0];
                int end1 = o1[1];
                int start2 = o2[0];
                int end2 = o2[1];
                if (end1 == end2) {
                    return 0;
                }
                return end1 < end2 ? -1 : 1;
            }
        });
        int[] M = new int[intervals.length];
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (noOverLap(intervals[j], intervals[i])) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }
            max = Math.max(max, M[i]);
        }
        return max;
    }

    private boolean noOverLap(int[] pre, int[] cur) {
        if (pre[1] < cur[0]) {
            return true;
        }
        return false;
    }
}
