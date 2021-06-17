package com.company;

import java.util.*;

public class RopeCut {
    public static void main(String[] args) {
        RopeCut s = new RopeCut();
        List<List<Integer>> ropes = new ArrayList<>();
        ropes.add(Arrays.asList(2, 2, 2, 2));
        ropes.add(Arrays.asList(1, 4, 3));
        ropes.add(Arrays.asList(5, 2, 1));
        System.out.println(s.leastRopeSegments(ropes));
    }

    // The line doesn't cross a rope segment if it goes through an edge of a segment of a rope.
    // Find a vertical line that cross the least number of rope segments.
    // Return the number of rope segments that the line crossed.
    // |---2---|---2---|---2---|---2---|        (2,1)   (4,1)   (6,1)
    // |-1-|-------4-------|-----3-----|        (1,1)   (2,1)   (4,1)   (5,1)   (6,1)
    // |------5------------|---2---|-1-|        (1,1)   (2,1)   (4,1)   (5,2)   (6,1)   (7,1)
    // Assumptions:
    // 1. The width sum of rope segments in different rows are the same and won't exceed 2147483647.
    // 2. The length of a rope segment is larger than 0 and less than length of the rope.
    // Time O(mn)
    // Space O(mn) <- O(length of rope)
    public int leastRopeSegments(List<List<Integer>> ropes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ropes.size(); i++) {
            int sum = 0;
            for (int j = 0; j < ropes.get(i).size() - 1; j++) {
                sum += (ropes.get(i).get(j));
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            max = Math.max(max, map.get(key));
        }
        return max == Integer.MIN_VALUE ? ropes.size() : ropes.size() - max;
    }
}
