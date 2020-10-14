package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println("main");
        Solution s = new Solution();
        String[] combo = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
        System.out.println(Arrays.toString(combo));
        System.out.println(s.getMap(combo));
        System.out.println(Arrays.toString(s.topKFrequent(combo, 2)));
    }

    // Time O(nlogk) <-- O(n) + O(klogk) + O(2(n-k)logk) + O(klogk)
    // Space O()
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        HashMap<String, Integer> map = getMap(combo);
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
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
            } else if (minHeap.peek().getValue() < entry.getValue()){
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return freqArray(minHeap);
    }

    private HashMap<String, Integer> getMap(String[] combo) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : combo) {
            Integer count = map.getOrDefault(str, 0);
            map.put(str, ++count);
        }
        return map;
    }

    private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
        String[] array = new String[minHeap.size()];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = minHeap.poll().getKey();
        }
        return array;
    }
}
