package com.company;

import java.util.*;

public class ReplaceWordWithShortestPrefix {
    public static void main(String[] args) {
        ReplaceWordWithShortestPrefix s = new ReplaceWordWithShortestPrefix();

        List<String> prefixes = Arrays.asList("pro", "program", "de", "re");
        String sentence = "if debugging is the process of removing bugs programming must be the process of putting them in";
        // System.out.println(s.toSet(prefixes));
        System.out.println(s.replaceWords(prefixes, sentence));
        // "if de is the pro of re bugs pro must be the pro of putting them in"
    }

    // Assumptions:
    // The given sentence only contains space and lower case characters.
    // The given list of prefix only contains lower case characters.
    // There is no duplicates in the list of prefix.
    // Method 1: brute force using StringBuilder
    // Time O(n*word.length + m*word.length + mlogm)
    // Space O(m)
    public String replaceWords(List<String> prefixes, String sentence) {
        Set<String> set = toSet(prefixes);
        StringBuilder sb = new StringBuilder();
        for (String word : sentence.split(" ")) {
            // System.out.println(word);
            for (int i = 0; i < word.length(); i++) {
                String prefix = word.substring(0, i + 1);
                if (set.contains(prefix)) {
                    sb.append(prefix);
                    sb.append(" ");
                    break;
                }
                if (i == word.length() - 1) {
                    sb.append(prefix);
                    sb.append(" ");
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    // Time O(mlogm) + O(m* word.length())
    private Set<String> toSet(List<String> prefixes) {
        Collections.sort(prefixes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return 0;
                }
                return o1.length() < o2.length() ? -1 : 1;
            }
        });
        Set<String> set = new HashSet<>();
        for (String prefix : prefixes) {
            for (int i = 0; i < prefix.length(); i++) {
                if (set.contains(prefix.substring(0, i + 1))) {
                    break;
                }
                if (i == prefix.length() - 1) {
                    set.add(prefix);
                }
            }
        }
        return set;
    }
}
