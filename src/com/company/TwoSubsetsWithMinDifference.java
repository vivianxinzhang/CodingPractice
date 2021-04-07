package com.company;

public class TwoSubsetsWithMinDifference {
    public static void main(String[] args) {
        TwoSubsetsWithMinDifference s = new TwoSubsetsWithMinDifference();
        int[] array = new int[] {1, 3, 0};
        System.out.println(s.minDifference(array));

        array = new int[] {1, 3, 2};
        System.out.println(s.minDifference(array));
    }

    // Assumptions:
    // The given integer array is not null and it has length of >= 2.
    // Method 1:
    // Time O(2^n)
    // Space O(n)
    public int minDifference(int[] array) {
        int totalSum = getSum(array);
        int[] minDiff = new int[] {Integer.MAX_VALUE};
        int currSum = 0;
        dfs(array, 0, currSum, 0, totalSum, minDiff);
        return minDiff[0];
    }

    // current level considering number at index i
    // have already picked # of count, still need to pick n/2 - count
    // have already considered i numbers, still have n - i to consider
    private void dfs(int[] array, int i, int currSum, int count, int totalSum, int[] minDiff) {
        // picked n/2 elements, return
        if (count == array.length / 2) {
            int currDiff = Math.abs((totalSum - currSum) - currSum);
            minDiff[0] = Math.min(currDiff, minDiff[0]);
            return;
        }
        // how elements need to be picked: array.length / 2 - count
        // how many element left: array.length - index
        // count < n/2, still need n/2-count elements, but have less elements left not considered
        // array.length - i < n / 2 - count
        if (array.length - i < array.length / 2 - count) {
            return;
        }
        // this includes condition index == array.length
        // when pass count == array.length/2 which means count < array.length/2 -> array.length/2 - count > 0
        // index == array.length which means array.length - index == 0
        // not add array[i] to currSum
        dfs(array, i + 1, currSum, count, totalSum, minDiff);
        // add array[i] to currSum
        currSum += array[i];
        dfs(array, i + 1, currSum, count + 1, totalSum, minDiff);
        currSum -= array[i];
    }

    private int getSum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
}
