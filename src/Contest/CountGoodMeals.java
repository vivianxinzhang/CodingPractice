package Contest;
import java.util.*;

public class CountGoodMeals {
    public static void main(String[] args) {
        CountGoodMeals s = new CountGoodMeals();
        int[] array = new int[] {64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64,64};
        System.out.println(s.countPairs(array));    // 528
        System.out.println(s.countPairsI(array));    // 528

        array = new int[] {1, 3, 5, 7, 9};
        System.out.println(s.countPairs(array));    // 4
        System.out.println(s.countPairsI(array));    // 4

        array = new int[] {1, 1, 1, 3, 3, 3, 7};
        System.out.println(s.countPairs(array));    // 15
        System.out.println(s.countPairsI(array));    // 15
    }

    public int countPairsI(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>(); // key: num, val: ocurrence
        int answer = 0;
        int MOD = 1000000007;
        for (int num : deliciousness) {
            int sum = 1;
            for (int i = 0; i <= 21; i++) {// 21 because 2^20 + 2^20 = 2^21, this is largest sum we can obtain in this problem.
                if (sum >= num && map.containsKey(sum - num)) {
                    answer += map.get(sum - num);
                    answer %= MOD;
                }
                sum *= 2;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return (int)answer;
    }

    public int countPairs(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (int num : array) {
            for (int i = 0; i <= 21; i++) {
                int target = (1 << i) - num;
                // x = 5     (1 << 3) - num = 8 - num = 3
                if (map.containsKey(target)) {
                    total += map.get(target);
                }
            }
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        return total;
    }
}
