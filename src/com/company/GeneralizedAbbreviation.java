package com.company;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    public static void main(String[] args) {
        GeneralizedAbbreviation s = new GeneralizedAbbreviation();

        System.out.println(s.generateAbbreviations("word"));
        // [word, wor1, wo1d, wo2, w1rd, w1r1, w2d, w3, 1ord, 1or1, 1o1d, 1o2, 2rd, 2r1, 3d, 4]
    }

    // Time O(n!*n)
    // Space O(n)
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if (word == null) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        dfs(word, 0, sb, res);
        return res;
    }

    private void dfs(String word, int index, StringBuilder sb, List<String> res) {
        if (index == word.length()) {
            res.add(sb.toString());
            return;
        }
        // do not abbreviate char at current index
        sb.append(word.charAt(index));
        dfs(word, index + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
        if (sb.length() != 0 && Character.isDigit(sb.charAt(sb.length() - 1))) {
            return;
        }
        // abbreviate [index, i)
        int preLength = sb.length();
        for (int i = index + 1; i <= word.length(); i++) {
            String count = getCount(i - index);
            sb.append(count);
            dfs(word, i, sb, res);
            sb.setLength(preLength);
        }
    }

    private String getCount(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append((char) (num % 10 + '0'));
            num /= 10;
        }
        return sb.reverse().toString();
    }
}
