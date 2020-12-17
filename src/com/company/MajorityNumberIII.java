package com.company;
import java.util.*;

public class MajorityNumberIII {
    public static void main(String[] args) {
        MajorityNumberIII s = new MajorityNumberIII();
        int[] A = {1, 2, 1, 2, 1};
        System.out.println(s.majority(A, 3));  // return [1, 2]
        A = new int[] {1, 2, 1, 2, 3, 3, 1};
        System.out.println(s.majority(A, 3));  // return [1]
    }

    // Time O(n)
    // Space O(k)
    public List<Integer> majority(int[] array, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num : array) {
            Integer count = map.get(num);
            if(count != null) {
                map.put(num, count + 1);
            } else if(map.size() < k - 1){
                map.put(num, 1);
            } else {
                List<Integer> keysToRemove = new ArrayList<>();
                for(Integer key : map.keySet()) {
                    Integer old = map.get(key) - 1;
                    map.put(key, old);
                    if(old == 0) {
                        keysToRemove.add(key);
                    }
                }
                for(int key : keysToRemove) {
                    map.remove(key);
                }
            }
        }

        for(Integer key : map.keySet()) {
            map.put(key, 0);
        }
        for(int num : array) {
            Integer count = map.get(num);
            if(count != null) {
                count += 1;
                if(count > (array.length / k)) {
                    result.add(num);
                    map.remove(num);
                } else  {
                    map.put(num, count);
                }
            }
        }
        return result;
    }

    // Time O(n)
    // Space O(n)
    public List<Integer> majorityI(int[] array, int k) {
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
            if (entry.getValue() > L / k) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
