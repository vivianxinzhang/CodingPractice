package com.company;

public class CelebrityProblem {
    public static void main(String[] args) {
        CelebrityProblem s = new CelebrityProblem();
        int[][] matrix = {
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 0}};
        System.out.println(s.celebrity(matrix));    // 1

        matrix = new int[][]{
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 0}};
        System.out.println(s.celebrity(matrix));    // 1
    }

    // Determine if there is one celebrity among all N persons, a celebrity is defined as:
    // 1. He does not know any other person.
    // 2. All the other persons know him.
    // Assumptions:
    // The given matrix is not null and N >= 2.
    // matrix[i][j] = 1 represents person i knows person j
    // matrix[j][i] = 1 represents person j knows person i
    // a celebrity does not know any other person, so for a celebrity c:
    // 1. matrix[c][j] == 0 for all j, entire row are 0s
    // 2. matrix[i][c] == 1 for all i except i == c, entire col are 1s
    // Time O(n^2)
    // Space O(1)
    public int celebrity(int[][] matrix) {
        for (int c = 0; c < matrix.length; c++) {
            if (othersKnowCelebrity(matrix, c) && celebrityNotKnowOthers(matrix, c)) {
                return c;
            }
        }
        return -1;
    }

    // check col, to make sure all matrix[i][celebrity] == 1
    private boolean othersKnowCelebrity(int[][] matrix, int celebrity) {
        for (int i = 0; i < matrix.length; i++) {
            if (i == celebrity) {
                continue;
            } else if (matrix[i][celebrity] == 0) {
                return false;
            }
        }
        return true;
    }

    // check row, to make sure all matrix[celebrity][i] == 0
    private boolean celebrityNotKnowOthers(int[][] matrix, int celebrity) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (i == celebrity) {
                continue;
            } else if (matrix[celebrity][i] == 1) {
                return false;
            }
        }
        return true;
    }
}
