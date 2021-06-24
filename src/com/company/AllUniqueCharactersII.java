package com.company;
import java.util.*;

public class AllUniqueCharactersII {
    public static void main(String[] args) {
        AllUniqueCharactersII s = new AllUniqueCharactersII();

        System.out.println(s.allUnique("abca+/"));  // false
        System.out.println(s.allUnique("abA8"));    // true
    }

    // Assumption:
    // we are using ASCII encoding and the number of valid letters is 256,
    // encoded from 0 to 255
    // The input word is not null
    // Time O(1)
    // Space O(1)
    public boolean allUnique(String word) {
        // each int value can represent 32 bit, so we need 8 ints to represent 256 bits
        int[] vec = new int[8];
        for (int i = 0; i < word.length(); i++) {
            // for a value c in the range of 0-255,
            // it is actually mapped to the int value at c/32 as index,
            // and the c%32'th bit of the int value
            char c = word.charAt(i);
            if ((vec[c / 32] >>> (c % 32) & 1) != 0) {
                return false;
            }
            vec[c / 32] |= 1 << (c % 32);
        }
        return true;
    }
}
