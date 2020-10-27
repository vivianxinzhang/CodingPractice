package com.company;
import java.util.*;

public class LongestSubstringWithKTypedCharacters {
    public static void main(String[] args) {
        LongestSubstringWithKTypedCharacters s = new LongestSubstringWithKTypedCharacters();

        String input = "aabcccc";
        System.out.println(s.longest(input, 2));
        System.out.println(s.longestI(input, 2));

        input = "aabcc";
        System.out.println(s.longest(input, 3));
        System.out.println(s.longestI(input, 3));
    }

    // Method 2: linear scan
    // Time O(n)
    // Space O(n)
    public String longestI(String s, int k) {
        int countOfUniqueChar = 0; // number of unique characters

        // Associative array to store the count of characters
        int count[] = new int[26];

        int currStart = 0;
        int currEnd = 0;

        // Also initialize values for result longest window
        int maxLength = 1;
        int globalLeft = 0;
        int globalRight = 0;

        count[s.charAt(0) - 'a']++;  // put the first character

        // Start from the second character and add characters in window according to above explanation
        for (int i = 1; i < s.length(); i++) {
            // Add the character 's[i]' to current window
            count[s.charAt(i) - 'a']++;
            currEnd = i;

            // If there are more than k unique characters in current window, remove from left side
            while (!isValid(count, k)) {
                count[s.charAt(currStart) - 'a']--;
                currStart++;
            }

            // Update the max window size if required
            if (currEnd- currStart + 1 > maxLength) {
                maxLength = currEnd - currStart + 1;
                globalLeft = currStart;
                globalRight = currEnd;
            }
        }
        return s.substring(globalLeft, globalRight + 1);
    }

    private boolean isValid(int[] count, int k) {
        int val = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                val++;
            }
        }
        // Return true if k is greater than or equal to val
        return (k >= val);
    }

    // Method 1: brute force
    // If the length of string is n, then there can be n*(n+1)/2 possible substrings.
    // A simple way is to generate all the substring and check each one whether it has exactly k unique characters or not.
    // If we apply this brute force, it would take O(n2) to generate all substrings and O(n)
    // to do a check on each one. Thus overall it would go O(n3).
    // Time O(n^3)
    // Space O(n)
    public String longest(String input, int k) {
        int globalLeft = 0;
        int globalRight = 0;
        int maxLength = 0;
        for (int start = 0; start < input.length(); start++) {
            for (int end = start; end < input.length(); end++) {
                int currLength = end - start + 1;
                if (currLength > maxLength && containsKChar(input, start, end, k)) {
                    maxLength = currLength;
                    globalLeft = start;
                    globalRight = end;
                }
            }
        }
        return input.substring(globalLeft, globalRight + 1);
    }

    private boolean containsKChar(String input, int start, int end, int k) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(input.charAt(i));
        }
        return set.size() == k;
    }

    // The given string is not null and guaranteed to have
    // at least k different characters.
    // k > 0.
    public String longestII(String input, int k) {
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        int globalLeft = 0;
        int globalRight = 0;
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        while (fast < array.length) {
            // case 1: less than k in set
            if (set.size() < k) {   // move fast pointer to include all repeating next char
                if (!contains(array, slow, fast, array[fast])) {
                    set.add(array[fast]);
                }
                char nextChar = array[fast];
                while (fast < array.length - 1 && array[fast + 1] == nextChar) {
                    fast++;
                }
                // update maxLength when set size is exactly k
                if (set.size() == k && fast - slow + 1 > maxLength) {
                    maxLength = fast - slow + 1;
                    globalLeft = slow;
                    globalRight = fast;
                }
                fast++;
            } else {    // move slow pointer to exclude all repeating last char
                char lastChar = array[slow];
                while (slow < array.length - 1 && array[slow + 1] == lastChar) {
                    slow++;
                }
                slow++;
                if (!contains(array, slow, fast, lastChar)) {
                    set.remove(lastChar);
                }
            }
        }
        return new String(array, globalLeft, globalRight);
    }

    private boolean contains(char[] array, int left, int right, char lastChar) {
        for (int i = left; i <= right; i++) {
            if (array[i] == lastChar) {
                return true;
            }
        }
        return false;
    }
}
