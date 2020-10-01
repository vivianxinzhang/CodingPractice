package com.company;

public class SmallestRectangleEnclosingBlackPixels {
    public static void main(String[] args) {
        SmallestRectangleEnclosingBlackPixels s = new SmallestRectangleEnclosingBlackPixels();
        int[][] matrix = null;
        System.out.println(s.minArea(matrix, -1, -1));

        matrix = new int[][]{{1}, {1}};
        System.out.println(s.minArea(matrix, 1, 0));

        matrix = new int[][]{{0, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        System.out.println(s.minArea(matrix, 0, 2));
    }

    // Time O(mlogn + nlogm)
    // Space O(1)
    public int minArea(int[][] image, int x, int y) {
        // Write your solution here
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int left = searchLeftCol(image, 0, y);
        int right = searchRightCol(image, y, image[0].length - 1);
        int top = searchTopRow(image, 0, x);
        int bottom = searchBottomRow(image, x, image.length - 1);
        return (right - left + 1) * (bottom - top + 1);
    }

    private int searchTopRow(int[][] image, int top, int bottom) {
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

    private int searchBottomRow(int[][] image, int top, int bottom) {
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

    private boolean containsOneInRow(int[][] image, int row) {
        for (int col = 0; col < image[0].length; col++) {
            if (image[row][col] == 1) {
                return true;
            }
        }
        return false;
    }

    private int searchRightCol(int[][] image, int left, int right) {
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

    private int searchLeftCol(int[][] image, int left, int right) {
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

    private boolean containsOneInCol(int[][] image, int mid) {
        for (int row = 0; row < image.length; row++) {
            if (image[row][mid] == 1) {
                return true;
            }
        }
        return false;
    }

    // Time O(mn)
    // Space O(1)
    public int minAreaI(int[][] image, int x, int y) {
        // Write your solution here
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int globalLeft = y;
        int globalRight = y;
        int globalTop = x;
        int globalBottom = x;
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[0].length; col++) {
                if (image[row][col] == 1) {
                    globalTop = row < globalTop ? row : globalTop;
                    globalBottom = row > globalBottom ? row : globalBottom;
                    globalLeft = col < globalLeft ? col : globalLeft;
                    globalRight = col > globalRight ? col : globalRight;
                }
            }
        }
        return (globalRight - globalLeft + 1) * (globalBottom - globalTop + 1);
    }
}
