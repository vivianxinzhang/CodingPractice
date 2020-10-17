package com.company;
import java.util.*;

// Assumptions: there could be duplicated elements in the given arrays
public class CommonNumbersOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println("main");
        CommonNumbersOfTwoSortedArrays s = new CommonNumbersOfTwoSortedArrays();

        int[] A = new int[] {1,8,9,10,11,13,13,15,17,17,17,18,19,20,21,22,22,23,24,24,26};
        int[] B = new int[] {0,1,1,2,5,6,7,7,9,12,14,15,15,18,18,20,20,21,21,24,26};
        System.out.println(s.commonII(A, B));

        A = new int[] {2,2,3,11,11,12,13,15,16,18,18,20,20,20,21,21,21,22,22,23,23};
        B = new int[] {0,0,1,1,4,6,10,11,12,13,14,14,17,19,22,22,23,23,24,24,26};
        System.out.println(s.commonII(A, B));
    }

    // Method 2: use HashMap
    // Implementation 2.1
    // Time = O(M + N) average, worst O(M^2 + N*M)
    // Extra Space O(M)
    public List<Integer> commonIII(int[] A, int[] B) {
        // Write your solution here
        List<Integer> common = new ArrayList<Integer>();
        // Step 1: insert all elements from array a（smaller array） into the hash set	=> {2, 3, 4}
        HashMap<Integer, Integer> countA = new HashMap<Integer, Integer>();
        for (int num : A) {
            Integer ct = countA.get(num);
            if (ct != null) {
                countA.put(num, ct + 1);
            } else {
                countA.put(num, 1);
            }
        }
        // Step 2: for each element in array b, check if it’s in the HashSet or not
        // 查找平均时间是O(1)    worst case是hashset的size
        for (int num : B) {
            Integer ct = countA.get(num);
            if (ct != null && ct > 0) {
                common.add(num);
                countA.put(num, ct - 1);
            }
        }
        return common;
    }

    // Implementation 2.2
    // Time = O(2m + n) average, worst O(m^2 + n^2 + mn)
    // Extra Space O(m + n)
    public List<Integer> commonII(int[] A, int[] B) {
        List<Integer> common = new ArrayList<>();
        // average O(m) worst case O(m^2)
        HashMap<Integer, Integer> countA = new HashMap<>();
        for (int num : A) {
            Integer ct = countA.get(num);
            if (ct != null) {
                countA.put(num, ct + 1);
            } else {
                countA.put(num, 1);
            }
        }
        // average O(n) worst case O(n^2)
        HashMap<Integer, Integer> countB = new HashMap<>();
        for (int num : B) {
            Integer ct = countB.get(num);
            if (ct != null) {
                countB.put(num, ct + 1);
            } else {
                countB.put(num, 1);
            }
        }
        // average O(m) worst case O(mn)
        for (Map.Entry<Integer, Integer> entry : countA.entrySet()) {
            Integer ctInB = countB.get(entry.getKey());
            if (ctInB != null) {
                int appear = Math.min(entry.getValue(), ctInB);
                for (int i = 0; i < appear; i++) {
                    common.add(entry.getKey());
                }
            }
        }
        // Time O(nlogn)
        Collections.sort(common);
        return common;
    }

    // Method 1: two pointers
    // Assumptions: a, b is not null
    // Time O(m + n)
    // Space O(1)
    public List<Integer> commonI(int[] A, int[] B) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                result.add(A[i]);
                i++;
                j++;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Method 3: binary search
    // Time = O(m*logn)
    // Extra Space O(1)
    public List<Integer> common(int[] A, int[] B) {
        // Write your solution here
        List<Integer> common = new ArrayList<Integer>();
        // For each element in array a, run a binary search in array b
        // Assume A.length <<<<< B.length
        int left = 0;    // we do binary search start from index left in B
        int i = -1;      // initialize i
        for (int ele : A) {
            // i is the index in B returned if we find a common element, if no such element i = -1
            i = firstOccur(B, ele, left);
            if (i != -1) {
                common.add(ele);
                // if find element, reset left to i +1 and continue binary search
                // 因为两个array都是有序的，所以每次找的candidate一定不会出现在上一个candidate之前
                left = i + 1;
                // if left if already out of bound, break the loop and don't need to search anymore
                if (left == B.length) {
                    break;
                }
            }
        }
        return common;
    }

    public int firstOccur(int[] array, int target, int left) {
        // Time O(log n) Space O(1)
        if (array == null || array.length == 0) {
            return -1;
        }
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (target <= array[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        }
        if (array[right] == target) {
            return right;
        }
        return -1;
    }
}
