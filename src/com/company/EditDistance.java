package com.company;

public class EditDistance {
    public static void main(String[] args) {
        EditDistance s = new EditDistance();
        String one = "";
        String two = "";
        System.out.println(s.editDistance(one, two));

        one = "sigh";
        two = "asith";
        System.out.println(s.editDistance(one, two));
    }

    // Method 2: DP
    // M[i][j] represents the minimum edit distance needed to transform
    // first i characters from one(substring(0, i)) into first j characters
    // into first j chars of two(substring(0, j))
    // if last character same M[i][j] = M[i-1][j-1]
    // if last character different M[i][j] =
    //                              Replace  M[i-1][j-1]+1
    //                              Delete   M[i-1][j]+1
    //                              Insert   M[i][j-1]+1
    // M[i][j] represents minimum editing distance between first i chars of one [0 ... i - 1]
    // and first j chars of two substring [0 ... j - 1]
    // f(i,j) = min{f(i-1, j-1) + (W1[i-1] == W[j-1]), f(i-1, j)+1, f(i,j-1)+1}
    // f(0,0) = 0
    // f(0,j) = j, for all j > 0
    // f(i,0) = i, for all i > 0
    // Time O(mn)
    // Space O(mn)
    public int editDistance(String one, String two) {
        int[][] M = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                // Base case:
                // M[i][0] = i and M[0][j] = j
                if (i == 0) {
                    M[i][j] = j;
                } else if (j == 0) {
                    M[i][j] = i;
                } else if (one.charAt(i - 1) == two.charAt(j - 1)){
                    // Case 1: if s1[i-1] = s2[j-1], then M[i][j] = M[i-1][j-1]
                    M[i][j] = M[i - 1][j - 1];
                } else {
                    // Case 2: if s1[i-1] != s2[j-1],
                    //         Option 1: replace, then M[i][j] = 1 + M[i-1][j-1]
                    //         Option 2: delete, then M[i][j] = 1 + M[i-1][j]
                    //         Option 3: insert, then M[i][j] = 1 + M[i][j-1]
                    //         M[i][j] = min(Option 1, Option 2, Option 3)
                    M[i][j] = Math.min(M[i - 1][j], M[i][j - 1]) + 1;
                    M[i][j] = Math.min(M[i][j], M[i - 1][j - 1] + 1);
                }
            }
        }
        return M[one.length()][two.length()];
    }

    // Method 1: Recursion
    // Assumptions: one, two are not null
    // There are at most m+n levels in the recursion tree, and there are at most 3 branches in each node. Thus
    //        递归树 最多m+n层  每个点最好情况下一个叉  最坏情况下三个叉
    //        replace每次循环m和n各减去1
    //        delete 每次循环m减1  insert 每次循环n减1  问题规模m+n-1
    // Time = O(3^(m+n)) + substring each time takes O(n) --> O(max(m, n)*3^(m+n))
    // Space O(m+n) + substring each time takes O(n) --> O(max(m, n)*3^(m+n))
    public int editDistanceI(String one, String two) {
        if (one.length() == 0) {
            return two.length();
        }
        if (two.length() == 0) {
            return one.length();
        }
        // (a) Check what the distance is if the character[0] are identical
        // and we do nothing first 第一个字母直接匹配上了
        if (one.charAt(0) == two.charAt(0)) {
            return editDistance(one.substring(1), two.substring(1));
        }
        // (b) Check what the distance is if we do a Replace first?
        // replace每次循环m和n各减去1 delete
        int replace = 1 + editDistance(one.substring(1), two.substring(1));
        // (b) Check what the distance is if we do a delete first?
        //  delete 每次循环m减1  问题规模m+n-1
        int delete = 1 + editDistance(one.substring(1), two);
        // (b) Check what the distance is if we do an insert first?
        // insert 每次循环n减1  问题规模m+n-1
        int insert = 1 + editDistance(one, two.substring(1));
        // Return the best solution
        return Math.min(Math.min(replace, delete), insert);
    }
}
