package com.company;
import java.util.*;

public class StringFrequencyInWindows {
    public static void main(String[] args) {
        StringFrequencyInWindows s = new StringFrequencyInWindows();
        String input = "ABCDABCDD";
        List<Frequency> res = s.frequency(input);
        for (Frequency freq : res) {
            System.out.println(freq.str + ": " + freq.freq);
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
}
