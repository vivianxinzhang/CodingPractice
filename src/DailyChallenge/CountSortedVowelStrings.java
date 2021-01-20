package DailyChallenge;
import java.util.*;

public class CountSortedVowelStrings {
    public static void main(String[] args) {
        CountSortedVowelStrings s = new CountSortedVowelStrings();
        System.out.println(s.countVowelStrings(1));     // 5
        System.out.println(s.countVowelStrings(2));     // 15
        System.out.println(s.countVowelStrings(33));    // 66045
    }

    // The problem is a variant of finding Combinations.
    // Mathematically, the problem can be described as, given 5 vowels (let k = 5k=5),
    // we want to find the number of combinations using only n vowels.
    // Also, we can repeat each of those vowels multiple times.
    // Time O(1)
    // Space O(1)

    public int countVowelStringsIII(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        int ans = 0;
        for(int i = 2; i <= n; i++) {
            for(int j = 3; j >= 0; j--){
                dp[j] = dp[j] + dp[j+1];
            }
        }

        for(int ele : dp){
            ans+=ele;
        }
        return ans;
    }

    // dp: M[n][k] represents number of strings of length n that consist first k vowels (a, e, i, o, u) and are lexicographically sorted.
    // M[n][k] = M[n-1][k] + M[n][k-1]
    // Time O(n)
    // Space O(n)
    public int countVowelStrings(int n) {
        int[][] M = new int[n + 1][6];
        for (int i = 1; i < M.length; i++) {
            for (int j = 1; j < M[0].length; j++) {
                if (i == 1) {
                    M[i][j] = j;
                } else if (j == 1) {
                    M[i][j] = 1;
                } else {
                    M[i][j] = M[i - 1][j] + M[i][j - 1];
                }
            }
        }
        return M[n][5];
    }

    // Algorithm
    // 1. As we start with the first vowel a then e and so on, we need a way to determine the current vowel in a recursive function.
    // We use an integer variable vowel for that purpose, where 1 denotes a, 2 denotes e, and so on.
    // 2. We begin with n positions and first vowel a(1) and use method countVowelStringUtil to recursively compute all the combinations.
    // 3. On every recursion, we decrement n by 1 as we have used up that position and continue passing the same vowel. On the backtrack,
    // we move on to the next vowel.
    // Base Case:
    // If n = 0n=0, we have reached the end of the string and found one combination. Hence, we return 1 and backtrack.
    // a, e ,i ,o ,u
    // Method 1: backtracking
    // Time O(n^5)  n levels, branching factor 5
    // Space O(n)
    public int countVowelStringsI(int n) {
        return countVowelStringUtil(n, 1);
    }

    int countVowelStringUtil(int n, int vowels) {
        if (n == 0) {
            return 1;
        }
        int result = 0;
        for (int i = vowels; i <= 5; i++) {
            result += countVowelStringUtil(n - 1, i);
        }
        return result;
    }
}
