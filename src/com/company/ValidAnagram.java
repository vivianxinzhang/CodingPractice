package com.company;
import java.util.*;

public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram s = new ValidAnagram();
        String source = "anagram";
        String target = "nagaram";
        System.out.println(s.isAnagram(source, target));    // true

        source = "rat";
        target = "car";
        System.out.println(s.isAnagram(source, target));    // false
    }

    // Follow up:
    // What if the inputs contain unicode characters? How would you adapt your solution to such case?
    // Time O(m + n)
    // Space O(1)
    public boolean isAnagram(String source, String target) {
        int[] count = new int[26];
        for (int i = 0; i < source.length(); i++) {
            count[source.charAt(i) - 'a']++;
        }
        for (int i = 0; i < target.length(); i++) {
            count[target.charAt(i) - 'a']--;
        }
        int unMatch = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                unMatch++;
            }
        }
        return unMatch == 0;
    }

    // Note:
    // You may assume the string contains only lowercase alphabets.
    // Time O(m + n)
    // Space O(1)  <--  O(26)
    public boolean isAnagramII(String source, String target) {
        Map<Character, Integer> map = getSourceMap(source);
        for (int i = 0; i < target.length(); i++) {
            int count = map.get(target.charAt(i));
            map.put(target.charAt(i), count - 1);
        }
        return countUnMatch(map) == 0;
    }

    private int countUnMatch(Map<Character, Integer> map) {
        int count = 0;
        for (int value : map.values()) {
            if (value != 0) {
                count++;
            }
        }
        return count;
    }

    private Map<Character, Integer> getSourceMap(String source) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            map.put(ch, 0);
        }
        for (int i = 0; i < source.length(); i++) {
            int count = map.getOrDefault(source.charAt(i), 0);
            map.put(source.charAt(i), count + 1);
        }
        return map;
    }

    // Time O(m + n)
    // Space O(m + n)
    public boolean isAnagramI(String source, String target) {
        // corner case:
        if (source == null && target == null) {
            return true;
        } else if (source == null || target == null) {
            return false;
        } else if (source.length() != target.length()) {
            return false;
        }
        Map<Character, Integer> tMap = getMap(target);
        Map<Character, Integer> sMap = getMap(source);
        return sMap.equals(tMap);
    }

    private Map<Character, Integer> getMap(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            int count = map.getOrDefault(input.charAt(i), 0);
            count++;
            map.put(input.charAt(i), count);
        }
        return map;
    }
}
