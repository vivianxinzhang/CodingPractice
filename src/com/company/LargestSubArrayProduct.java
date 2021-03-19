package com.company;

public class LargestSubArrayProduct {
    public static void main(String[] args) {
        LargestSubArrayProduct s = new LargestSubArrayProduct();
        double[] array = new double[] {-1};
        System.out.println(s.largestProduct(array));   // 216.0

        array = new double[] {4, -2, -3, -2, 3, -1, -2, 6};
        System.out.println(s.largestProduct(array));   // 216.0

        array = new double[] {2.0, -0.1, 4, -2, -1.5};
        System.out.println(s.largestProduct(array));   // 12.0
    }

    // Assumptions:
    // The given array is not null and has length of at least 1.
    // Time: O(n)
    // Space: O(n)
    public double largestProduct(double[] array) {
        double[] min = new double[array.length];
        double[] max = new double[array.length];
        min[0] = array[0];
        max[0] = array[0];
        double globalMaxProd = max[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                min[i] = 1;
                max[i] = 1;
                globalMaxProd = Math.max(array[i], globalMaxProd);
            } else {
                // min of (min[i-1]*array[i], max[i-1]*array[i], array[i])
                min[i] = Math.min(min[i - 1] * array[i], max[i - 1] * array[i]);
                min[i] = Math.min(min[i], array[i]);
                // max of (min[i - 1] * array[i], max[i - 1] * array[i], array[i])
                max[i] = Math.max(min[i - 1] * array[i], max[i - 1] * array[i]);
                max[i] = Math.max(max[i], array[i]);
                globalMaxProd = Math.max(max[i], globalMaxProd);
            }
        }
        return globalMaxProd;
    }

    // Time: O(n)
    // Space: O(1)
    public double largestProductI(double[] array) {
        double currMin = 1;
        double currMax = 1;
        double res = Integer.MIN_VALUE;
        for (double num : array) {
            if (num == 0) {
                currMin = 1;
                currMax = 1;
                res = Math.max(res, num);
                continue;
            }
            double tmp = currMax * num;
            currMax = Math.max(num * currMax, num * currMin);
            currMax = Math.max(currMax, num);
            currMin = Math.min(tmp, num * currMin);
            currMin = Math.min(currMin, num);
            res = Math.max(res, currMax);
        }
        return res;
    }

    // maxProd[i] maximum positive subarray product ending at i - 1;
    // minProd[i] minimum negative subarray product ending at i - 1;
    // case 1: array[i] > 0
    //         dpPositive[i] =  Math.max(dpPositive[i - 1]*array[i], 1) --> follow up如果给你的有小数就不一定了
    //         dpNegative[i] =  dpNegative[i - 1] < 0? dpNegative[i - 1]*array[i] : 1;
    //         为什么这里要赋值成1?
    //         重新开始算在乘除法运算中就应该是重设为1 为了后面继续计算乘积
    // case 2: array[i] < 0
    //         dpPositive[i] =   dpNegative[i - 1] < 0? dpNegative[i - 1]*array[i]: 1
    //         dpNegative[i] =   Math.min(dpPositive[i - 1]*array[i], array[i]) --> 如果能保证是整数那么就不用做这个比较
    //         为什么要和array[i] 而不用 dpNegative[i-1]? 因为我们的dp定义是ending at i 考虑东山再起的case
    //         dpNegative[i-1] 已经被考虑过了！
    // case 3: array[i] == 0:
    //         dpPositive[i] = 1
    //         dpNegative[i] = 1
    // update globalMax
    //        case 1: GlobalMax = Math.max(dpPositive[i],GlobalMax)
    //        case 2: GlobalMax = Math.max(dpNegative[i - 1]*array[i],GlobalMax)
    //        case 3: GlobalMax = Math.max(0,GlobalMax)
    // Time: O(n)
    // Space: O(n)
    public double largestProductII(double[] array) {
        double[] maxProd = new double[array.length + 1];
        double[] minProd = new double[array.length + 1];
        maxProd[0] = 1;
        minProd[0] = 1;
        double globalMax = array[0];
        for(int i = 0; i < array.length; i++) {
            if(array[i] > 0) {
                maxProd[i + 1] = Math.max(maxProd[i], 1) * array[i];
                minProd[i + 1] = minProd[i] < 0 ? minProd[i] * array[i] : 1;
                globalMax = Math.max(globalMax, maxProd[i + 1]);
            } else if (array[i] < 0) {
                maxProd[i + 1] = Math.max(minProd[i] * array[i], 1);
                minProd[i + 1] = Math.min(maxProd[i] * array[i], array[i]);
                globalMax = Math.max(globalMax, minProd[i] * array[i]);
            } else {
                maxProd[i + 1] = minProd[i + 1] = 1;
                globalMax = Math.max(globalMax, 0.0);
            }
        }
        return globalMax;
    }
}
