package com.company;
import java.util.*;

public class TopKFrequentWords {
    // Time average O((nlogk)  <--  O(n + klogk + 2(n-k)logk + klogk)
    // worst case O(n^2) <-- O(n^2 + klogk + 2(n-k)logk + klogk)
    // Space O(n + k)
    // Assumptions: combo is not null, and k >= 1
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if (combo == null || combo.length == 0 || k == 0) {
            return new String[0];
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : combo) {
            Integer count = map.get(str);
            if (count == null) {
                map.put(str, 1);
            } else {
                map.put(str, count + 1);
            }
        }
        // PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return 0;
                }
                return o1.getValue() < o2.getValue() ? -1 : 1;
            }
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return freqArray(minHeap);
    }

    private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
        String[] array = new String[minHeap.size()];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = minHeap.poll().getKey();
        }
        return array;
    }
}
