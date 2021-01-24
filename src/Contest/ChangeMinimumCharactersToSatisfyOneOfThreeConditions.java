package Contest;

public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {
    public static void main(String[] args) {
        ChangeMinimumCharactersToSatisfyOneOfThreeConditions s = new ChangeMinimumCharactersToSatisfyOneOfThreeConditions();

        System.out.println(s.minCharacters("bddae", "abbb"));  // 2

        System.out.println(s.minCharacters("dabadd", "cda"));  // 3

        System.out.println(s.minCharacters("acac", "bd"));  // 1

        System.out.println(s.minCharacters("aba", "caa"));  // 2

    }

    // s1   s2
    // aba  caa
    // a:2 b:1  c:1 a:2
    // operation 1: aba  ccc  2
    // operation 2: bbb  aaa  2+1 = 3
    // operation 3: aaa  aaa  1+1 = 3
    // dabadd   cda
    // dabadd   eee   3
    // range of chars in s1
    // range of chars in s2
    // which is the most common char shared by s1 and s2
    // Time O(m + n)
    // Space O(1)
    public int minCharacters(String a, String b) {
        int[] countA = new int[26];
        int[] countB = new int[26];
        int lowA = Integer.MAX_VALUE;
        int highA = Integer.MIN_VALUE;
        for (int i = 0; i < a.length(); i++) {
            int idx = a.charAt(i) - 'a';
            countA[idx]++;
            lowA = Math.min(lowA, idx);
            highA = Math.max(highA, idx);
        }
        int lowB = Integer.MAX_VALUE;
        int highB = Integer.MIN_VALUE;
        for (int i = 0; i < b.length(); i++) {
            int idx = b.charAt(i) - 'a';
            countB[idx]++;
            lowB = Math.min(lowB, idx);
            highB = Math.max(highB, idx);
        }
        // Case 1: need to low end of count B, and # of letters b/t low end of countB and highend of countA
        // Case 2: need to low end of count B
        // computer # of chars in overlap
        // lowa    higha    lowb   highb
        // lowb   highb    lowa    higha
        if (highA < lowB || highB < lowA) {
            return 0;
        }
        // lowa   lowb higha  highb
        // lowb   lowa highb  higha
        // lowa   lowb  highb   higha
        // lowb   lowa  higha   highb
        // Case 3: need to know the most common char shared by s1 and s2
        int ops1 = Integer.MAX_VALUE;
        int moveA = a.length();
        int moveB = 0;
        // try all borders  a to z
        // a 小 b 大
        for (int i = 0; i < 25; i++) {
            moveA -= countA[i];
            moveB += countB[i];
            ops1 = Math.min(ops1, moveA + moveB);
        }
        int ops2 = Integer.MAX_VALUE;
        moveA = 0;
        moveB = b.length();
        // b 小 a 大
        // try all borders  a to z
        for (int i = 0; i < 25; i++) {
            moveA += countA[i];
            moveB -= countB[i];
            ops2 = Math.min(ops2,  moveA + moveB);
        }
        int mostCommon = 0;
        for (int i = 0; i < 26; i++) {
            int curr = countA[i] + countB[i];
            mostCommon = Math.max(mostCommon, curr);
        }
        int ops3 = a.length() + b.length() - mostCommon;
        return Math.min(Math.min(ops1, ops2), ops3);
    }
}
