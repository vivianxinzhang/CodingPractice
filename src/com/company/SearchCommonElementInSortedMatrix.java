package com.company;

public class SearchCommonElementInSortedMatrix {
    public static void main(String[] args) {
        SearchCommonElementInSortedMatrix s = new SearchCommonElementInSortedMatrix();
        int[][] matrix = new int[][] {{0}};
        System.out.println(s.search(matrix));    // -1

        matrix = new int[][] {
                    {1, 2, 3, 4},
                    {4, 5, 6, 7},
                    {2, 3, 4, 8}
                };
        // System.out.println(s.searchI(matrix));   // 4
        System.out.println(s.search(matrix));    // 4

        matrix = new int[][] {
                    {1, 2, 3, 4, 10, 11, 100, 200},
                    {5, 6, 7, 8, 9,  10, 10,  11,  100, 101},
                    {2, 3, 5, 8, 9,  12, 99,  100}
                };
        System.out.println(s.search(matrix));    // 100
    }

    // Method 2:
    // lastCol maintain the col index which is possibly to be common element in each row
    // step 1:
    // in each round, find the min element in lastCol
    // step 2:
    // iterate every other element in lastCol
    //      if element is bigger than min, exclude the element from potential result, decrease index by 1
    //      update count of min if same with min
    // step 3:
    // if count of min is same with total row number, return min as res
    // Time O(m*n)   <--  O(2m * n)
    // Space O(m)
    public int search(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        int m = matrix.length;
        int[] lastCol = new int[m];
        for (int i = 0; i < m; i++) {
            // Initialize current last element of all rows
            lastCol[i] = matrix[i].length - 1;
        }
        // initialize minRow as the first row
        int minRow = 0;
        while (lastCol[minRow] >= 0) {     //  -- O(n)
            int minVal = matrix[minRow][lastCol[minRow]];
            // find the min element      -- O(m)
            for (int i = 0; i < m; i++) {
                int curVal = matrix[i][lastCol[i]];
                if (curVal < minVal) {
                    minVal = curVal;
                }
            }
            // equalCount is count of elements equal to min in current last col
            int equalCount = 0;
            // Traverse current lastCol elements again to update equalCount     -- O(m)
            for (int i = 0; i < m; i++) {
                int curVal = matrix[i][lastCol[i]];
                // decrease lastCol index of a row whose value is more than min

                if (curVal == minVal) {
                    equalCount++;
                } else {
                    // reduce lastCol index by 1
                    lastCol[i] -= 1;
                    if (lastCol[i] == -1) {
                        return -1;
                    }
                }
            }
            // all elements equals to min
            if (equalCount == m) {
                return minVal;
            }
        }
        return -1;
    }

    // Method 1:
    // for each element in row 0, do a binary search in following rows
    // Time O(n * m * logn)
    // Space O(1)
    public int searchI(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        for(int i = 0; i < matrix[0].length; i++) {
            int target = matrix[0][i];
            int row = 1;
            // check if target exist in all following rows
            while (row < matrix.length) {
                if (binarySearch(matrix[row], target)) {
                    row++;
                } else {
                    // target does not exist in row
                    break;
                }
            }
            // target exist in all rows
            if (row == matrix.length) {
                return target;
            }
        }
        return -1;
    }

    private boolean binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
