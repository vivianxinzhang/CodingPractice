package DailyChallenge;
import java.util.*;

public class DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        DetermineIfTwoStringsAreClose s = new DetermineIfTwoStringsAreClose();

        System.out.println(s.closeStrings("aba", "abb"));

        System.out.println(s.closeStrings("aacabb", "bbcbaa"));
    }

    // Operation 1: Swap any two existing characters.
    // For example, abcde -> aecdb
    // Operation 1 allows you to freely reorder the string.
    // Operation 2: Transform every occurrence of one existing character into another existing character,
    // and do the same with the other character.
    // For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
    // Operation 2 allows you to freely reassign the letters' frequencies.
    // Time O(n)
    // Space O(n)
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Set<Character> charSet1 = buildCharSet(word1);
        int[] countMap1 = buildCountMap(word1);
        Map<Integer, Integer> map1 = getCountMap(countMap1);

        Set<Character> charSet2 = buildCharSet(word2);
        int[] countMap2 = buildCountMap(word2);
        Map<Integer, Integer> map2 = getCountMap(countMap2);

        return charSet1.equals(charSet2) && map1.equals(map2);
    }

    private Map<Integer, Integer> getCountMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                int count = map.getOrDefault(array[i], 0);
                map.put(array[i], count + 1);
            }
        }
        return map;
    }

    private int[] buildCountMap(String input) {
        int[] count = new int[26];
        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i) - 'a';
            count[idx]++;
        }
        return count;
    }

    private Set<Character> buildCharSet(String input) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            set.add(input.charAt(i));
        }
        return set;
    }
}
