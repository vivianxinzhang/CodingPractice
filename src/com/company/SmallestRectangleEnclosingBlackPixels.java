package com.company;

public class SmallestRectangleEnclosingBlackPixels {
    public static void main(String[] args) {
        SmallestRectangleEnclosingBlackPixels s = new SmallestRectangleEnclosingBlackPixels();
        int[][] matrix = null;
        System.out.println(s.minArea(matrix, -1, -1));    // 0

        matrix = new int[][]{{1}, {1}};
        Printer.printMatrix(matrix);
        System.out.println(s.minArea(matrix, 1, 0));      // 2

        matrix = new int[][]{{0, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        Printer.printMatrix(matrix);
        System.out.println(s.minArea(matrix, 0, 2));      // 6
    }

    // There is only one black region. Pixels are connected horizontally and vertically.
    // Method 2: binary search
    // Time O(mlogn + nlogm)
    // Space O(1)
    public int minArea(int[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int left = binarySearchLeft(image, 0, y);
        int right = binarySearchRight(image, y, image[0].length - 1);
        int top = binarySearchTop(image, 0, x);
        int bottom = binarySearchBottom(image, x, image.length - 1);
        return (right - left + 1) * (bottom - top + 1);
    }

    // O(nlogm)
    private int binarySearchTop(int[][] image, int top, int bottom) {
        while (top < bottom - 1) {
            int mid = top + (bottom - top) / 2;
            if (containsOneInRow(image, mid)) {
                bottom = mid;
            } else {
                top = mid;
            }
        }
        if (containsOneInRow(image, top)) {
            return top;
        }
        return bottom;
    }

    // O(nlogm)
    private int binarySearchBottom(int[][] image, int top, int bottom) {
        while (top < bottom - 1) {
            int mid = top + (bottom - top) / 2;
            if (containsOneInRow(image, mid)) {
                top = mid;
            } else {
                bottom = mid;
            }
        }
        if (containsOneInRow(image, bottom)) {
            return bottom;
        }
        return top;
    }

    // O(n)
    private boolean containsOneInRow(int[][] image, int row) {
        for (int col = 0; col < image[0].length; col++) {
            if (image[row][col] == 1) {
                return true;
            }
        }
        return false;
    }

    // O(mlogn)
    private int binarySearchRight(int[][] image, int left, int right) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (containsOneInCol(image, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (containsOneInCol(image, right)) {
            return right;
        }
        return left;
    }

    // O(mlogn)
    private int binarySearchLeft(int[][] image, int left, int right) {
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (containsOneInCol(image, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (containsOneInCol(image, left)) {
            return left;
        }
        return right;
    }

    // O(m)
    private boolean containsOneInCol(int[][] image, int mid) {
        for (int row = 0; row < image.length; row++) {
            if (image[row][mid] == 1) {
                return true;
            }
        }
        return false;
    }

    // Method 2: brute force
    // Time O(mn)
    // Space O(1)
    public int minAreaI(int[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int globalLeft = y;
        int globalRight = y;
        int globalTop = x;
        int globalBottom = x;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] == 1) {
                    globalTop = i < globalTop ? i : globalTop;
                    globalBottom = i > globalBottom ? i : globalBottom;
                    globalLeft = j < globalLeft ? j : globalLeft;
                    globalRight = j > globalRight ? j : globalRight;
                }
            }
        }
        return (globalRight - globalLeft + 1) * (globalBottom - globalTop + 1);
    }
}
