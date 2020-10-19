package com.company;
import java.util.*;

public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram s = new ValidAnagram();
        String source = "anagram";
        String target = "nagaram";
        System.out.println(s.isAnagram(source, target));

        source = "rat";
        target = "car";
        System.out.println(s.getMap(source));
        System.out.println(s.getMap(target));
        System.out.println(s.isAnagram(source, target));
    }

    // Time O(m + n)
    // Space O(m + n)
    public boolean isAnagram(String source, String target) {
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
