package com.company;
import java.util.List;

public class LongestSubarrayContainsOnlyOnes {
    public static void main(String[] args) {
        LongestSubarrayContainsOnlyOnes s = new LongestSubarrayContainsOnlyOnes();

        int[] array = new int[] {0,0};
        System.out.println(s.longestConsecutiveOnes(array, 2));

        array = new int[] {1,1,0,0,1,1,1,0,0,0};
        System.out.println(s.longestConsecutiveOnes(array, 0));
    }
    // while循环 右边界fast一定要往前走 分三种情况
    // 1. 如果右边是1 直接往前走: fast++
    // 2. 如果右边是0 但k没有满 也可以往前走: fast++, k--
    // 3. 右边是0 且 k满了 必须收左边界（找到左边的0踢出去 来给右边腾位置 然后右边才可把0放进来往前走）
    //    第三步没有处理好的话 右边没有办法往前走 就会出现死循环 跳不出来了
    //    3.1 左边是0: slow++, k++
    //    3.2 左边是1: slow++
    // Time O(n)
    // Space O(1)
    // [slow, fast]
    public int longestConsecutiveOnes(int[] array, int k) {
        // Write your solution here
        int slow = 0;
        int fast = 0;
        int count = 0;
        int maxLength = 0;
        while (fast < array.length) {
            if (array[fast] == 1) {
                maxLength = Math.max(fast - slow + 1, maxLength);
                fast++;
            } else if (count < k) {
                maxLength = Math.max(fast - slow + 1, maxLength);
                fast++;
                count++;
            } else if (array[slow] == 0){
                slow++;
                count--;
            } else {
                slow++;
            }
        }
        return maxLength;
    }
}
