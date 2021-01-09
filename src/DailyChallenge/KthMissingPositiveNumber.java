package DailyChallenge;

public class KthMissingPositiveNumber {
    public static void main(String[] args) {
        KthMissingPositiveNumber s = new KthMissingPositiveNumber();
        int[] array = new int[] {2, 3};
        System.out.println(s.findKthPositive(array, 1));

        array = new int[] {1, 2, 3, 4};
        System.out.println(s.findKthPositive(array, 2));

        array = new int[] {2, 3, 4, 7, 11};
        System.out.println(s.findKthPositive(array, 5));
    }

    // 2 3 4 7 11
    // 1 2 3 4 5      num - (index + 1) is the # of missing number
    // 0 1 2 3 4
    // binary search
    // largest (num - (index + 1)) smaller than k
    // Time O(logn)
    // Space O(1)
    public int findKthPositive(int[] array, int k) {
        int largestSmallerIdx = binarySearch(array, k);
        // already >= k elements missing at position 0
        if (largestSmallerIdx == -1) {
            return k;
        }
        // calculate how many elements missing at position largestSmallerIdx
        int count = array[largestSmallerIdx] - (largestSmallerIdx + 1);
        return array[largestSmallerIdx] + (k - count);
    }

    private int binarySearch(int[] array, int k) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left);
            if (array[mid] - (mid + 1)>= k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (array[right] - (right + 1) < k) {
            return right;
        }
        if (array[left] - (left + 1) < k) {
            return left;
        }
        return -1;
    }
}
