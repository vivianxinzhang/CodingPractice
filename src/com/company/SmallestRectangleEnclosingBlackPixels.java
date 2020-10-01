package com.company;

public class SmallestRectangleEnclosingBlackPixels {
    // Time O(mn)
    // Space O(1)
    public int minArea(int[][] image, int x, int y) {
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
