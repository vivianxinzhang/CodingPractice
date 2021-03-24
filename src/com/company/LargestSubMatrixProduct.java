package com.company;

public class LargestSubMatrixProduct {
    public static void main(String[] args) {
        LargestSubMatrixProduct s = new LargestSubMatrixProduct();
        System.out.println();
        double[][] matrix = new double[][] { {1.0}, {2.0}};
        System.out.println(s.largest(matrix));

        matrix = new double[][]{{2,-1,0.5,1,-3},{0,-2,-1,2,0.1},{3,0.2,1,-3,-2}};
        System.out.println(s.largest(matrix));
    }

    // Assumptions:
    // The given double matrix is not null and has size of M * N, where M >= 1 and N >= 1
    // Time O(m^3*n)
    // Space O(n)
    public double largest(double[][] matrix) {
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
}
