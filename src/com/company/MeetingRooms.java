package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms s = new MeetingRooms();

        int[][] intervals = new int[][] {{23, 27}};
        System.out.println(s.canAttendMeetings(intervals));     // true
        intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(s.canAttendMeetings(intervals));     // false
    }

    // Method 2:
    // Sorting according to start time
    // compare curr meeting's start time with previous meeting's end time
    // and check if two neighbor meeting overlap
    // Time O(nlogn)
    // Space O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return 0;
                }
                return o1[0] < o2[0] ? -1 : 1;
            }
        });
        for (int i = 1; i < intervals.length; i++) {
            // next meeting start time earlier than previous meeting end time
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    // Method 2:
    // Time O(n^2)
    // Space O(1)
    public boolean canAttendMeetingsI(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            if (overLapPrevious(intervals, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean overLapPrevious(int[][] intervals, int index) {
        int[] cur = intervals[index];
        for (int i = 0; i < index; i++) {
            int[] pre = intervals[i];
            if (pre[1] <= cur[0] || pre[0] >= cur[1]) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }
}
