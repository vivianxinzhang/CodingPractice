package DailyChallenge;

public class CheckIfAllOnesAreAtLeastKPlacesAway {
    public static void main(String[] args) {
        CheckIfAllOnesAreAtLeastKPlacesAway s = new CheckIfAllOnesAreAtLeastKPlacesAway();
        System.out.println();
        int[] array = new int[] {1,0,0,1,0,1};
        System.out.println(s.kLengthApart(array, 2));

        array = new int[] {1,1,1,1,1};
        System.out.println(s.kLengthApart(array, 0));
    }

    // Time O(n)
    // Space O(1)
    public boolean kLengthApart(int[] nums, int k) {
        int preIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // check validation rule:
                // 1,0,0,0,1
                // 0       4
                // if less than k places, return false
                if (preIdx != -1 && i - preIdx - 1 < k) {
                    return false;
                }
                // update preIdx
                preIdx = i;
            }
        }
        return true;
    }
}
