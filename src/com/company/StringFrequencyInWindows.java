package com.company;
import java.util.*;

public class StringFrequencyInWindows {
    public static void main(String[] args) {
        StringFrequencyInWindows s = new StringFrequencyInWindows();

        String input = "ABCDABCDD";
        // output: ABCD: 2   BCDA: 1   CDAB: 1   DABC: 1
        List<Frequency> res = s.frequency(input);
        for (Frequency freq : res) {
            System.out.print(freq.str + ": " + freq.freq + "   ");
        }
        System.out.println();

        input = "CABDACBCCDADCB";
        // output: ADCB: 1   BDAC: 1   CABD: 1   DACB: 1
        res = s.frequency(input);
        for (Frequency freq : res) {
            System.out.print(freq.str + ": " + freq.freq + "   ");
        }
    }

    // Method 1:
    // Assumptions:
    // 1. The given string is not null and has length of >= 4.
    // 2. In the output, if two substrings have the same frequency, then they should be sorted by the their natural order.
    // Time  O(nlogn)
    // Space O(n)
    public List<Frequency> frequency(String input) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= input.length() - 4; i++) {     // O(n)
            String str = input.substring(i, i + 4);     // O(4)
            if (valid(str)) {   // O(4)
                int count = map.getOrDefault(str, 0);
                map.put(str, count + 1);
            }
        }
        return toList(map);
    }

    private boolean valid(String str) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'A') {
                countA++;
            } else if (ch == 'B') {
                countB++;
            } else if (ch == 'C') {
                countC++;
            } else if (ch == 'D') {
                countD++;
            }
        }
        return countA == 1 && countB == 1 && countC == 1 && countD == 1;
    }

    private List<Frequency> toList(Map<String, Integer> map) {
        Frequency[] array = new Frequency[map.size()];
        int index = 0;
        for (String str : map.keySet()) {
            array[index] = new Frequency(str, map.get(str));
            index++;
        }
        Arrays.sort(array, new Comparator<Frequency>() {    // O(nlogn)
            @Override
            public int compare(Frequency o1, Frequency o2) {
                if (o1.freq == o2.freq) {
                    return o1.str.compareTo(o2.str);
                }
                return o1.freq > o2.freq ? -1 : 1;
            }
        });
        List<Frequency> list = new ArrayList<>();
        for (Frequency freq : array) {
            list.add(freq);
        }
        return list;
    }

    // another implementation
    private List<Frequency> toListI(Map<String, Integer> map) {
        // PriorityQueue<Frequency> maxHeap = new PriorityQueue<>((f1, f2) -> (f1.freq == f2.freq ? f1.str.compareTo(f2.str) : f2.freq - f1.freq));
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o1.getValue() > o2.getValue() ? -1 : 1;
            }
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            maxHeap.offer(entry);
        }
        List<Frequency> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Map.Entry<String, Integer> tmp = maxHeap.poll();
            res.add(new Frequency(tmp.getKey(), tmp.getValue()));
        }
        return res;
    }
}
