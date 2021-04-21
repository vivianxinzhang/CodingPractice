package com.company;
import java.util.*;

public class LoginNumbersPerInterval {
    public static void main(String[] args) {
        LoginNumbersPerInterval s = new LoginNumbersPerInterval();
        int[][] logins = new int[][] {{1, 2}, {0, 4}, {5, 6}};
        int[][] res = s.sessionNum(logins);
        // [[0, 1, 1], [1, 2, 2], [2, 4, 1], [5, 6, 1]]
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        logins = new int[][] {{1,6}, {2,3}, {10,11}, {7,11}, {3,4}};
        res = s.sessionNum(logins);
        // [[1, 2, 1], [2, 4, 2], [4, 6, 1], [7, 10, 1], [10, 11, 2]]
        // [[1, 2, 1], [2, 3, 2], [3, 4, 2], [4, 6, 1], [7, 10, 1], [10, 11, 2]]
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Time O(mnlogmn)
    // Space O(mn)
    public int[][] sessionNum(int[][] logins) {
        if (logins == null || logins.length == 0) {
            return null;
        }
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.time == o2.time) {
                    return 0;
                }
                return o1.time < o2.time ? -1 : 1;
            }
        });
        for (int[] pair : logins) {
            minHeap.offer(new Point(pair[0], true));
            minHeap.offer(new Point(pair[1], false));
        }
        int count = 0;
        int left = 0;
        List<int[]> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Point cur = minHeap.poll();
            int right = cur.time;
            if (left != right && count != 0) {
                int[] pre = null;
                Integer preLeft = null;
                Integer preRight = null;
                Integer preCount = null;
                if (res.size() > 0) {
                    pre = res.get(res.size() - 1);
                    preLeft = pre[0];
                    preRight = pre[1];
                    preCount = pre[2];
                }
                if (pre != null && count == preCount && left == preRight) {
                    res.set(res.size() - 1, new int[] {preLeft, right, count});
                } else {
                    res.add(new int[] {left, right, count});
                }
            }
            if (cur.isLeft) {
                count++;
            } else {
                count--;
            }
            left = right;
        }
        return toArray(res);
    }

    private int[][] toArray(List<int[]> res) {
        int[][] array = new int[res.size()][];
        int index = 0;
        for (int[] entry : res) {
            array[index++] = entry;
        }
        return array;
    }

    class Point {
        int time;
        boolean isLeft;

        public Point(int time, boolean isLeft) {
            this.time = time;
            this.isLeft = isLeft;
        }
    }
}
