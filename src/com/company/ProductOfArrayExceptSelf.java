package com.company;
import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        ProductOfArrayExceptSelf s = new ProductOfArrayExceptSelf();
        int[] nums = new int[] {0, 0};
        int[] result = s.productExceptSelf(nums);   // [0, 0]
        System.out.println(Arrays.toString(result));

        nums = new int[] {1, 2, 3, 4};
        result = s.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));    // [24, 12, 8, 6]

        nums = new int[] {5, 0, 3};
        result = s.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));    // [0, 15, 0]
    }

    // Follow Up: Could you solve it with constant space complexity?
    // (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
    // Time O(n)
    // Space O(1)
    public int[] productExceptSelf(int[] nums) {
        int[] M = new int[nums.length];
        // left to right, compute the product
        M[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            M[i] = M[i - 1] * nums[i - 1];
        }
        // right to left, multiple in each round
        int R = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            M[i] = M[i] * R;
            R *= nums[i];
        }
        return M;
    }

    // Solve it without division and in O(n).
    // Time O(n)
    // Space O(n)
    public int[] productExceptSelfII(int[] nums) {
        int[] leftProd = getLeftProduct(nums);
        int[] rightProd = getRightProduct(nums);
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = leftProd[i] * rightProd[i];
        }
        return result;
    }

    private int[] getLeftProduct(int[] nums) {
        int[] M = new int[nums.length];
        M[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            M[i] = M[i - 1] * nums[i - 1];
        }
        return M;
    }

    private int[] getRightProduct(int[] nums) {
        int[] M = new int[nums.length];
        M[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            M[i] = M[i + 1] * nums[i + 1];
        }
        return M;
    }

    // Time O(n)
    // Space O(n)
    public int[] productExceptSelfI(int[] nums) {
        int prod = 1;
        int countZero = 0;
        for (int num : nums) {
            if (num == 0) {
                countZero++;
                prod *= 1;
            } else {
                prod *= num;
            }
        }
        int[] result = new int[nums.length];
        if (countZero >= 2) {
            return result;
        }
        if (countZero == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    result[i] = prod;
                }
            }
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = prod / nums[i];
        }
        return result;
    }
}
