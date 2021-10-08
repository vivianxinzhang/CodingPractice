package com.company;
import java.util.*;

public class MaxWeightsSumOfIntervals {
    public static void main(String[] args) {
        MaxWeightsSumOfIntervals s = new MaxWeightsSumOfIntervals();

        IntervalW[] intervals = new IntervalW[1];
        intervals[0] = new IntervalW(0, 1, 3);
        System.out.println(s.maxWeightSum(intervals));      // 3

        intervals = new IntervalW[3];
        intervals[0] = new IntervalW(4, 5, 1);
        intervals[1] = new IntervalW(0, 6, 1);
        intervals[2] = new IntervalW(2, 5, 3);
        System.out.println(s.maxWeightSum(intervals));      // 3

        intervals[0] = new IntervalW(4, 5, 2);
        intervals[1] = new IntervalW(0, 1, 1);
        intervals[2] = new IntervalW(2, 3, 2);
        System.out.println(s.maxWeightSum(intervals));      // 5
    }

    // Assumptions:
    // The given array of intervals is not null and has length of > 0
    // Method 2: dp
    // M[i] represents maximum weight for non overlapping intervals ending at index i (including i)
    // base case:
    // M[0] = intervals[0].weight
    // induction rule:
    // for all 0 =< j < i:
    // if noOverlap(i, j), T[i] = Math.max(T[i], T[j] + weight[i])
    // return max of all M[i]
    // Time O(n^2)
    // Space O(n)
    public int maxWeightSum(IntervalW[] intervals) {
        Arrays.sort(intervals, new Comparator<IntervalW>() {
            @Override
            public int compare(IntervalW o1, IntervalW o2) {
                if (o1.end == o2.end) {
                    return 0;
                }
                return o1.end < o2.end ? -1 : 1;
            }
        });
        int[] M = new int[intervals.length];
        M[0] = intervals[0].weight;
        int res = intervals[0].weight;
        for (int i = 1; i < intervals.length; i++) {
            M[i] = intervals[i].weight;
            // check all intervals before i
            for (int j = 0; j < i; j++) {
                if (intervals[j].end <= intervals[i].start) {   // no overlap
                    M[i] = Math.max(M[i], M[j] + intervals[i].weight);
                }
            }
            res = Math.max(M[i], res);
        }
        return res;
    }

    // Method 1: dfs
    // how many levels: n
    // each level decide add or not add the current interval to result
    // Time O(2^n * n)
    // Space O(n)
    public int maxWeightSumI(IntervalW[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<IntervalW> list = new ArrayList<>();
        int[] max = new int[] {Integer.MIN_VALUE};
        dfs(intervals, 0, 0, list, max);
        return max[0];
    }

    private void dfs(IntervalW[] intervals, int index, int weightSum, List<IntervalW> curSet, int[] max) {
        if (index == intervals.length) {
            max[0] = Math.max(max[0], weightSum);
            return;
        }
        // add
        if (nonOverLap(intervals[index], curSet)) {         // O(n)
            curSet.add(intervals[index]);
            weightSum += intervals[index].weight;
            dfs(intervals, index + 1, weightSum, curSet, max);
            weightSum -= intervals[index].weight;
            curSet.remove(curSet.size() - 1);
        }
        // not add
        dfs(intervals, index + 1, weightSum, curSet, max);
    }

    private boolean nonOverLap(IntervalW interval, List<IntervalW> list) {
        for (IntervalW pre : list) {
            if (((interval.end <= pre.start) || (interval.start >= pre.end))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private static class IntervalW {
        public int start;
        public int end;
        public int weight;

        public IntervalW(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
