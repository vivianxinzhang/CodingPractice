package com.company;
import java.util.*;

public class MajorityNumberII {
    public static void main(String[] args) {
        MajorityNumberII s = new MajorityNumberII();
        int[] A = {1,2,3, 1};
        System.out.println(s.majority(A));  // return [3]
        A = new int[] {1, 2, 1, 2, 3, 3, 1};
        System.out.println(s.majority(A));  // return [1]
        A = new int[] {1};   // return []
        System.out.println(s.majority(A));  // return [1]
    }

    // Method 2:
    // Time O(n)
    // Space O(1)
    public List<Integer> majority(int[] array) {
        List<Integer> result = new ArrayList<>();
        Integer num1 = null;
        Integer num2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (num1 != null && array[i] == num1) {
                count1++;
            } else if (num2 != null && array[i] == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = array[i];
                count1 = 1;
            } else if (count2 == 0) {
                num2 = array[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int num : array) {
            if (num == num1) {
                count1++;
            }
            if (num == num2) {
                count2++;
            }
        }
        if (count1 > array.length / 3) {
            result.add(num1);
        }
        if (count2 > array.length / 3) {
            result.add(num2);
        }
        return result;
    }

    // Method 1:
    // Time O(n)
    // Space O(n)
    public List<Integer> majorityI(int[] array) {
        int L = array.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int ele : array) {
            Integer count = map.get(ele);
            if (count != null) {
                map.put(ele, count + 1);
            } else {
                map.put(ele, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > L / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
