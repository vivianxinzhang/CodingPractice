package com.company;
import java.util.*;

public class LargestProductOfLength {
    public static void main(String[] args) {
        LargestProductOfLength s = new LargestProductOfLength();
        String[] dict = new String[] {"abcde", "abcd", "ade", "xy"};
        System.out.println(s.largestProduct(dict));     // 10

        dict = new String[] {"abc", "abc", "abc", "abc"};
        System.out.println(s.largestProduct(dict));     // 0

        dict = new String[] {"abcdefhi", "ix", "hj", "x"};
        System.out.println(s.largestProduct(dict));     // 8
    }

    // Given a dictionary containing many words,
    // find the largest product of two word's lengths,
    // such that the two words do not share any common characters.
    // Assumptions:
    // 1. The words only contains characters of 'a' to 'z'
    // 2. The dictionary is not null and does not contains null string, and has at least two strings
    // 3. If there is no such pair of words, just return 0
    // Method 2:
    // Time O(n*word.length() + n^2)    n is dict.length()
    // Space O(n)
    public int largestProduct(String[] dict) {
        // Get the bit mask for each of the word in the dict,
        // the bit mask is represented by the lowest 26 bits of an integer
        // each of the bit represents one of the characters in 'a' - 'z'
        Map<String, Integer> bitMasks = getBitMasks(dict);
        // sort the dict by length of the words in descending order
        Arrays.sort(dict, new Comparator<String>(){
            @Override
            public int compare(String s0, String s1) {
                if (s0.length() == s1.length()) {
                    return 0;
                }
                return s0.length() > s1.length() ? -1 : 1;
            }
        }) ;
        int largest = 0;
        // note the order of constructing all the pairs,
        // we make our best to try largest product
        // Time O(n^2) with early termination
        for (int i = 1; i < dict.length; i++) {
            for (int j = 0; j < i; j++) {
                // early break if the product is already smaller than the current largest one
                int prod = dict[i].length() * dict[j].length();
                if (prod <= largest) {
                    break;
                }
                int iMask = bitMasks.get(dict[i]);
                int jMask = bitMasks.get(dict[j]);
                // if two words do not share any common characters,
                // the bit masks "and" result should be 0 since
                // there is not any position such that in the two bit masks they are all 1
                if ((iMask & jMask) == 0) {
                    largest = prod;
                    break;
                }
            }
        }
        return largest;
    }

    // Get the bit mask for each of the words
    // Time: O(dict.length * word.length())
    private Map<String, Integer> getBitMasks(String[] dict) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : dict) {
            int bitMask = 0;
            for (int i = 0; i < str.length(); i++) {
                // the 26 characters 'a' - 'z' are mapped to 0 - 25th bit.
                // to determine which bit it is for a character x,
                // use (x - 'a') since their values are in a consecutive range
                // if character x exists in the word, we set the bit at
                // corresponding index to 1
                bitMask |= 1 << (str.charAt(i) - 'a');
            }
            map.put(str, bitMask);
        }
        return map;
    }

    // Method 1: Brute force
    // Time = O(dict.length^2 * (s[i].length + s[j].length))
    // Space O(word.length)
    public int largestProductI(String[] dict) {
        int maxProduct = 0;
        if (dict == null) {
            return maxProduct;
        }
        for (int i = 0; i < dict.length; i++) {
            for (int j = i + 1; j < dict.length; j++) {
                if (!haveCommonLetter(dict[i], dict[j])) {
                    int currProduct = dict[i].length() * dict[j].length();
                    maxProduct = Math.max(currProduct, maxProduct);
                }
            }
        }
        return maxProduct;
    }

    // Time O(m + n)
    // space O(m)
    private boolean haveCommonLetter(String a, String b) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            set.add(a.charAt(i));
        }
        for (int j = 0; j < b.length(); j++) {
            if (set.contains(b.charAt(j))) {
                return true;
            }
        }
        return false;
    }
}
