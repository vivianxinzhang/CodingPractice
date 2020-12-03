package com.company;
import java.util.*;

public class MaxWeightsSumOfIntervals {
    public static void main(String[] args) {
        MaxWeightsSumOfIntervals s = new MaxWeightsSumOfIntervals();
        IntervalW[] intervals = new IntervalW[3];
        intervals[0] = new IntervalW(4, 5, 2);
        intervals[1] = new IntervalW(0, 1, 1);
        intervals[2] = new IntervalW(2, 3, 2);
        System.out.println(s.maxWeightSum(intervals));
    }

    // Time O(2^n * n)
    // Space O(n)
    public int maxWeightSum(IntervalW[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<IntervalW> currSet = new ArrayList<>();
        int[] maxWeightsSum = new int[1];
        maxWeightsSum[0] = Integer.MIN_VALUE;
        dfs(intervals, 0, 0, currSet, maxWeightsSum);
        return maxWeightsSum[0];
    }

    private void dfs(IntervalW[] intervals, int i, int weightSum, List<IntervalW> currSet, int[] max) {
        if (i == intervals.length) {
            max[0] = Math.max(max[0], weightSum);
            return;
        }
        // add
        if (!overLap(intervals[i], currSet)) {
            currSet.add(intervals[i]);
            weightSum += intervals[i].weight;
            dfs(intervals, i + 1, weightSum, currSet, max);
            weightSum -= intervals[i].weight;
            currSet.remove(currSet.size() - 1);
        }
        // not add
        dfs(intervals, i + 1, weightSum, currSet, max);
    }

    private boolean overLap(IntervalW interval, List<IntervalW> currSet) {
        for (IntervalW preInterval : currSet) {
            if (!((interval.end < preInterval.start) || (interval.start >= preInterval.end))) {
                return true;
            }
        }
        return false;
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
