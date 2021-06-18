package com.company;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStringI {
    /**
     * Two Strings are called isomorphic if the letters in one String
     * can be remapped to get the second String. Remapping a letter means
     * replacing all occurrences of it with another letter.
     * The ordering of the letters remains unchanged.
     * The mapping is two way and no two letters may map to the same letter,
     * but a letter may map to itself.
     * Determine if two given String are isomorphic.*/
    public static void main(String[] args) {
        IsomorphicStringI s = new IsomorphicStringI();

        System.out.println(s.isomorphic("abca", "xyzx"));   // true
        System.out.println(s.isomorphic("abba", "cccc"));   // false
    }

    // Assumptions:
    // The two given Strings are not null.
    // Time O(n)
    // Space O(n)
    public boolean isomorphic(String source, String target) {
        if (source.length() != target.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            if (map.containsKey(source.charAt(i))) {
                // source.charAt(i) 对应的字母 与 target.charAt(i) 不同
                if (map.get(source.charAt(i)) != target.charAt(i)) {
                    return false;
                }
            } else if (map.containsValue(target.charAt(i))) {
                // map 里没有 key: source.charAt(i)
                // 并且已经有另一个 character 与 target.charAt(i) 对应
                return false;
            } else {
                // source.charAt(i) 与 target.charAt(i) 对应
                map.put(source.charAt(i), target.charAt(i));
            }
        }
        return true;
    }
}
