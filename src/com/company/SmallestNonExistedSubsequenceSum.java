package com.company;

public class SmallestNonExistedSubsequenceSum {
    public static void main(String[] args) {
        SmallestNonExistedSubsequenceSum s = new SmallestNonExistedSubsequenceSum();
        int[] array = new int[] {2};
        System.out.println(s.firstMissing(array));  // 1

        array = new int[] {1, 1, 1, 1};
        System.out.println(s.firstMissing(array));  // 5

        array = new int[] {1, 3, 6, 10, 11, 15};
        System.out.println(s.firstMissing(array));  // 2
    }

    // We can solve this problem in O(n) time using a simple loop.
    // Let the input array be arr[0..n-1].
    // We initialize the result as 1 (smallest possible outcome) and traverse the given array.
    // Let the smallest element that cannot be represented by elements at indexes from 0 to (i-1) be ‘res’,
    // there are following two possibilities when we consider element at index i:
    // 1) If arr[i] is greater than ‘res’:
    //    'res’ is the final result,
    //    we found the gap which is ‘res’ because the elements after arr[i] are also going to be greater than ‘res’.
    //    [0, i-1]  represents -> [0, res-1]
    //    + num     represents -> [0, res-1] ...gap...  [num,res+num-1]
    //    num > res, then there is a gap between res-1 and num, the first missing number is res
    // 2) The value of ‘res’ is incremented after considering arr[i]:
    //    The value of ‘res’ is incremented by arr[i]
    //    (why? If elements from 0 to (i-1) can represent 1 to ‘res-1′, then elements from 0 to i can represent from 1
    //    to ‘res + arr[i] – 1′ be adding ‘arr[i]’ to all subsets that represent 1 to ‘res’)
    // Time O(n)
    // Space O(1)
    public int firstMissing(int[] array) {
        // smallest array[0] largest array[0 ... n-1]
        int sum = 1; // Initialize result
        // Traverse the array and increment 'res' if arr[i] is
        // smaller than or equal to 'res'.
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= sum) {
                sum += array[i];
            }
        }
        return sum;
    }

    // Time O(n)
    // Space O(1)
    public int firstMissingI(int[] array) {
        // Write your solution here
        // smallest array[0] largest array[0 ... n-1]
        int res = 1; // Initialize result
        int n = array.length;
        // Traverse the array and increment 'res' if arr[i] is
        // smaller than or equal to 'res'.
        for (int i = 0; i < n && array[i] <= res; i++) {
            res = res + array[i];
        }
        return res;
    }
}
