package com.company;

public class LargestSubMatrixProduct {
    public static void main(String[] args) {
        LargestSubMatrixProduct s = new LargestSubMatrixProduct();

        double[][] matrix = new double[][] { {1.0}, {2.0}};
        System.out.println(s.largest(matrix));  // 2.0

        matrix = new double[][]{
                {2, -1, 0.5, 1, -3},
                {0, -2, -1, 2, 0.1},
                {3, 0.2, 1, -3, -2}};
        System.out.println(s.largest(matrix));  // 6.0
    }

    // Assumptions:
    // The given double matrix is not null and has size of M * N, where M >= 1 and N >= 1
    // Time O(m^2*n)  <-- O(m*(n+m(*2n)))
    // Space O(n)
    public double largest(double[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        double result = matrix[0][0];
        for (int top = 0; top < m; top++) {     // O(m)
            // initialize col prefix product
            double[] nums = new double[n];
            for (int k = 0; k < n; k++) {   // O(n)
                nums[k] = 1.0;
            }
            for (int bottom = top; bottom < m; bottom++) {  // O(m)
                // Step 1: get col prefix prod for each col
                for (int k = 0; k < n; k++) {       // O(n)
                    nums[k] *= matrix[bottom][k];
                }
                // Step 2: maximum subarray product
                double max = nums[0], min = nums[0], ans = nums[0];
                for(int col = 1; col < n; col++){    // O(n)
                    double temp = max;
                    max = Math.max(Math.max(max * nums[col], min * nums[col]), nums[col]);
                    min = Math.min(Math.min(temp * nums[col], min * nums[col]), nums[col]);
                    ans = Math.max(ans, max);
                }
                result = Math.max(result, ans);
            }
        }
        return result;
    }

    // Method 1:
    // Time O(m^3*n)
    // Space O(n)
    public double largestI(double[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        double globalMax = Double.NEGATIVE_INFINITY;
        for (int top = 0; top < m; top++) {
            for (int bottom = top; bottom < m; bottom++) {
                double[] min = new double[n];
                double[] max = new double[n];
                double num = rangeProd(top, bottom, 0, matrix);
                max[0] = num;
                min[0] = num;
                globalMax = Math.max(globalMax, num);
                for (int i = 1; i < n; i++) {
                    num = rangeProd(top, bottom, i, matrix);
                    // update max -- max of min[i-1]*array[i], max[i-1]*array[i], array[i]
                    max[i] = Math.max(min[i - 1] * num, max[i - 1] * num);
                    max[i] = Math.max(max[i], num);
                    // update min -- min of min[i-1]*array[i], max[i-1]*array[i], array[i]
                    min[i] = Math.min(min[i - 1] * num, max[i - 1] * num);
                    min[i] = Math.min(min[i], num);
                    // update globalMax
                    globalMax = Math.max(globalMax, max[i]);
                }
            }
        }
        return globalMax;
    }

    private double rangeProd(int top, int bottom, int col, double[][] matrix) {
        double prod = 1;
        for (int i = top; i <= bottom; i++) {
            prod *= matrix[i][col];
        }
        return prod;
    }

    private double[][] getColPrefixProd(double[][] matrix, int m, int n) {
        double[][] M = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    M[i][j] = matrix[i][j];
                } else {
                    M[i][j] = M[i - 1][j] * matrix[i][j];
                }
            }
        }
        return M;
    }

    // Method 0: brute force
    // enumerate all the sub-matrices and computer each product
    // Time O((mn)^3)  <-- O(mn*mn*mn)
    // Space O(1)
    public double largestII(double[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        double globalMax = Double.NEGATIVE_INFINITY;
        for (int topLefti = 0; topLefti < m; topLefti++) {
            for (int topLeftj = 0; topLeftj < n; topLeftj++) {
                for (int bottomRighti = topLefti; bottomRighti < m; bottomRighti++) {
                    for (int bottomRightj = topLeftj; bottomRightj < n; bottomRightj++) {
                        double currProd = computeProd(matrix, topLefti, topLeftj, bottomRighti, bottomRightj);
                        globalMax = Math.max(globalMax, currProd);
                    }
                }
            }
        }
        return globalMax;
    }

    private double computeProd(double[][] matrix, int topLefti, int topLeftj, int bottomRighti, int bottomRightj) {
        double prod = 1;
        for (int i = topLefti; i <= bottomRighti; i++) {
            for (int j = topLeftj; j <= bottomRightj; j++) {
                prod *= matrix[i][j];
            }
        }
        return prod;
    }
}
