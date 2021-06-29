package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms s = new MeetingRooms();

        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(s.canAttendMeetings(intervals));     // false
    }

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
}
