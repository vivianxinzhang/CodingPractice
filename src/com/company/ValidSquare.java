package com.company;
import java.util.*;

public class ValidSquare {
    public static void main(String[] args) {
        ValidSquare s = new ValidSquare();
        int[] p1 = new int[]{0, 0};
        int[] p2 = new int[]{0, 0};
        int[] p3 = new int[]{0, 0};
        int[] p4 = new int[]{0, 0};
        System.out.println(s.validSquare(p1, p2, p3, p4));

        p1 = new int[]{0, 0};
        p2 = new int[]{1, 1};
        p3 = new int[]{1, 0};
        p4 = new int[]{0, 1};
        System.out.println(s.validSquare(p1, p2, p3, p4));
    }

    // Method 2: sort and then validate
    // Time O(1)
    // Space O(1)
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        // sort the given set of points based on their x-coordinate values, and in the case of a tie,
        // based on their y-coordinate value, we can obtain an arrangement,
        // which directly reflects the arrangement of points on a valid square boundary possible
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        // After sorting
        // p0p1, p1p3, p3p2, p2p0 form the four sides of any valid square.
        // p0p3, p1p2 form the diagonals of the square.
        return dist(p[0], p[1]) != 0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0])   && dist(p[0],p[3])==dist(p[1],p[2]);
    }

    // Method 1: brute force
    // Time O(1)
    // Space O(1)
    public boolean validSquareI(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};
        return checkAllPermutations(p, 0);
    }

    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        // we need to determine if the sides of the qaudrilateral formed by these 4 points are equal.
        // But checking only this won't suffice, since this condition will be satisfied even in the case of a rhombus,
        // (all the four sides are equal but the adjacent sides aren't perpendicular to each other)
        // Thus, we also need to check if the lengths of the diagonals formed between the corners of the quadrilateral are equal.
        return dist(p1, p2) > 0 && dist(p1, p2) == dist(p2, p3) && dist(p2, p3) == dist(p3, p4) && dist(p3, p4) == dist(p4, p1) && dist(p1, p3) == dist(p2, p4);
    }

    boolean checkAllPermutations(int[][] p, int index) {
        if (index == 4) {
            return check(p[0], p[1], p[2], p[3]);
        } else {
            boolean res = false;
            for (int i = index; i < 4; i++) {
                swap(p, index, i);
                res |= checkAllPermutations(p, index + 1);
                swap(p, index, i);
            }
            return res;
        }
    }

    public void swap(int[][] p, int x, int y) {
        int[] temp = p[x];
        p[x] = p[y];
        p[y] = temp;
    }

    // Method 3:
    // only three possible set of edges and diagonals
    // Time O(1)
    // Space O(1)
    public boolean validSquareIII(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};
        return check(p1, p2, p3, p4) || check(p1, p3, p2, p4) || check(p1, p2, p4, p3);
    }
}