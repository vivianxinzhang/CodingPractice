package DailyChallenge;

public class CheckIfTwoStringArraysAreEquivalent {
    public static void main(String[] args) {
        CheckIfTwoStringArraysAreEquivalent s = new CheckIfTwoStringArraysAreEquivalent();
        String[] word1 = new String[] {"a", "b", "c"};
        String[] word2 = new String[] {"abc"};
        System.out.println(s.arrayStringsAreEqual(word1, word2));
    }

    // Time O(mn) n is # of words, m is average length of each word
    // Space O(1)
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int count1 = 0;
        int count2 = 0;
        int idx1 = 0;
        int i = 0;
        int idx2 = 0;
        int j = 0;
        while (idx1 < word1.length && i < word1[idx1].length() && idx2 < word2.length && j < word2[idx2].length()) {
            if (word1[idx1].charAt(i) != word2[idx2].charAt(j)) {
                return false;
            }
            i++;
            if (i == word1[idx1].length()) {
                idx1++;
                i = 0;
            }
            j++;
            if (j == word2[idx2].length()) {
                idx2++;
                j = 0;
            }
        }
        return idx1 == word1.length && idx2 == word2.length;
    }
}
