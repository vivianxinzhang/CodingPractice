package com.company;
import java.util.*;

public class LargestProductOfLength {
    public static void main(String[] args) {
        LargestProductOfLength s = new LargestProductOfLength();
        String[] dict = new String[] {"abcde", "abcd", "ade", "xy"};
        System.out.println(s.largestProduct(dict));

        dict = new String[] {"abc", "abc", "abc", "abc"};
        System.out.println(s.largestProduct(dict));
    }

    // Given a dictionary containing many words,
    // find the largest product of two word's lengths,
    // such that the two words do not share any common characters.
    // Assumptions:
    // 1. The words only contains characters of 'a' to 'z'
    // 2. The dictionary is not null and does not contains null string, and has at least two strings
    // 3. If there is no such pair of words, just return 0
    // Time O(n*word.length() + n^2)    n is dict.length()
    // Space O(dict.length())
    public int largestProduct(String[] dict) {
        // Get the bit mask for each of the word in the dict,
        // the bit mask is represented by the lowest 26 bits of an integer
        // each of the bit represents one of the characters in 'a' - 'z'
        HashMap<String, Integer> bitMasks = getBitMasks(dict);
        // sort the dict by length of the words in descending order
        Arrays.sort(dict, new Comparator<String>(){
            @Override
            public int compare(String s0, String s1) {
                if (s0.length() == s1.length()) {
                    return 0;
                }
                return s0.length() < s1.length() ? 1 : -1;
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
                }
            }
        }
        return largest;
    }

    // Get the bit mask for each of the words
    // Time: O(word.length())
    private HashMap<String, Integer> getBitMasks(String[] dict) {
        HashMap<String, Integer> map = new HashMap<>();
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
}
