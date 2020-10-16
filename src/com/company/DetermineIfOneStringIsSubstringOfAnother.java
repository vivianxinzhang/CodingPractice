package com.company;

public class DetermineIfOneStringIsSubstringOfAnother {
    // Determine if a small string is a substring of another large string
    // Return the index of the first occurrence of the small string in the large string
    // Return -1 if the small string is not a substring of the large string

    public static void main(String[] args) {
        System.out.println("main");
        DetermineIfOneStringIsSubstringOfAnother s = new DetermineIfOneStringIsSubstringOfAnother();
        String large = "bcabc";
        String small = "ab";
        System.out.println(s.strstr(large, small));

        large = "bcabc";
        small = "bcd";
        System.out.println(s.strstr(large, small));

        large = "bcabc";
        small = "";
        System.out.println(s.strstr(large, small));
    }

    // Method 1: Naive solution
    // Time O(mn)
    // Space O(1)
    public int strstr(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        if (small.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(String large, int index, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(index + i) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Notice:
    // 1. There is no assumption about the charset used in the String,
    //    so that we can not assume we are using 26 lower case characters
    // 2. This is the most correct version of RabinKarp in computer programming,
    //    we need to handle:
    //    2.1 we could use arbitrary charset,
    //    2.2 the overflow case, by taking the module operation on a very large number
    // 3. You probably do not need to write this kind of solution to handle above two cases,
    //    if you are in an interview. But it is still necessary to understand the reason
    //    behind it.
    // Method 2: RabinKarp
}
