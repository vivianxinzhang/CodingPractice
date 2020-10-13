package com.company;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestWithOnlyTwoThreeAsFactors {
    public static void main(String[] args) {
        KthSmallestWithOnlyTwoThreeAsFactors s = new KthSmallestWithOnlyTwoThreeAsFactors();
        System.out.println(s.kth(6));
    }

    // Time O(klogk)
    // Space O(k)
    public int kth(int k) {
        // Write your solution here
        PriorityQueue<Combo> minHeap = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(new Combo(0, 0, 1));
        visited.add(1);
        for (int i = 0; i < k - 1; i++) {
            Combo curr = minHeap.poll();
            if (!visited.contains(curr.s * 2)) {
                Combo incXCombo = new Combo(curr.x + 1, curr.y, curr.s * 2);
                minHeap.offer(incXCombo);
                visited.add(curr.s * 2);
            }
            if (!visited.contains(curr.s * 3)) {
                Combo incYCombo = new Combo(curr.x, curr.y + 1, curr.s * 3);
                minHeap.offer(incYCombo);
                visited.add(curr.s * 3);
            }
        }
        return minHeap.peek().s;
    }

    private class Combo implements Comparable<Combo>{
        int x;
        int y;
        int s;

        public Combo(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }

        @Override
        public int compareTo(Combo other) {
            if (this.s == other.s) {
                return 0;
            }
            return this.s < other.s ? -1 : 1;
        }
    }
}
