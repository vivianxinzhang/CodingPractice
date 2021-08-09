package com.company;
import java.util.*;

public class TopKFrequentWords {
    public static void main(String[] args) {
        TopKFrequentWords s = new TopKFrequentWords();

        String [] array = null;
        System.out.println(Arrays.toString(s.topKFrequent(array, 2)));  // []

        array = new String[] {"a", "a", "b", "c", "c", "c", "d"};
        System.out.println(Arrays.toString(s.topKFrequent(array, 1)));  // [c]

        array = new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
        System.out.println(Arrays.toString(s.topKFrequent(array, 2)));  // [b, c]
    }

    // Assumptions: combo is not null, and k >= 1
    // Time O(nlogk) <-- O(n) + O(klogk) + O(2(n-k)logk) + O(klogk)
    // Time average O((n + k)logk)  <--  O(n + klogk + (n-k)logk + klogk)
    // worst case O(n^2) <-- O(n^2 + klogk + (n-k)logk + klogk)
    // Space O(n + k)
    // Step 1: get frequency map of combo with word as key, frequency as value
    // Step 2: build min heap of Map.Entry
    // Step 3: iterate through hashMap entry set,
    //         if size of min heap < k, offer map entry to hashmap
    //         else, compare top of minHeap with new entry key
    //         if value of top entry is smaller than new entry element,
    //         minHeap.poll() and minHeap.offer(entry)
    public String[] topKFrequent(String[] combo, int k) {
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
