package Contest;

import com.company.Solution;

import java.util.*;

public class TupleWithSameProduct {
    public static void main(String[] args) {
        TupleWithSameProduct s = new TupleWithSameProduct();
        int[] array = new int[] {1,2,4,5,10};
        System.out.println(s.tupleSameProductI(array)); // 16
        System.out.println(s.tupleSameProduct(array)); // 16

        array = new int[] {2, 3, 4, 6};
        System.out.println(s.tupleSameProductI(array)); // 8
        System.out.println(s.tupleSameProduct(array)); // 8

        array = new int[] {2, 3, 4, 6, 8, 12};
        System.out.println(s.tupleSameProductI(array)); // 40
        System.out.println(s.tupleSameProduct(array)); // 40

        array = new int[] {20,10,6,24,15,5,4,30};
        System.out.println(s.tupleSameProduct(array)); // 48
        System.out.println(s.tupleSameProductI(array)); // 48

        array = new int[] {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};
        System.out.println(s.tupleSameProduct(array)); // 1288
        System.out.println(s.tupleSameProductI(array)); // 1288

        array = new int[] {8,448,264,525,435,486,378,308,144,75,196,110,231,120,39,288,50,616,140,261,272,783,225,552,598,30,128,570,322,77,340,19,72,224,294,390,276,87,238,180,80,33,68,210,725,243,696,198,208,46,21,58,360,170,190,510,375,551,348,396,377,69,84,300,572,468,160,24,34,667,29,64,253,115,690,100,870,754,102,1,11,312,609,161,493,450,342,133,588,48,152,10,42,273,440,728,65,98,5,23,250,242,38,182,26,648,99,357,400,275,187,483,414,323,408,105,230,520,750,4,500,32,286,418,189,638,528,234,315,96,352,812,232,40,3,130,184,17,15,324,240,392,7,174,270,416,513,25,203,221,399,475,9,54,476,442,406,840,12,504,114,675,624,621,56,405,125,119,136,506,702,364,70,60,228,20,85,575,135,117,78,171,156,55,299,462,116,780,52,432,165,88,325,338,391,546,522,209,176,108};
        System.out.println(s.tupleSameProduct(array)); // 251424
        System.out.println(s.tupleSameProductI(array)); // 251424
    }

    public int tupleSameProduct(int[] nums) {
        // key: product, value: combo words to get same product(no duplicate)
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int prod = nums[i] * nums[j];
                List<List<Integer>> pairs = map.getOrDefault(prod, new ArrayList<>());
                // iterate through pairs and only add if no duplicate
                if (!containsSameValue(pairs, nums[i], nums[j])) {
                    List<Integer> newPair = Arrays.asList(nums[i], nums[j]);
                    pairs.add(newPair);
                    map.put(prod, pairs);
                }
            }
        }
        int count = 0;
        for (List<List<Integer>> pairs: map.values()) {
            int numOfPairs = pairs.size();
            if (numOfPairs == 2) {
                count += 8;
            } else if (numOfPairs > 2) {
                int combos =  getCombos(numOfPairs);
                count += (8 * combos);
            }
        }
        return count;
    }

    private boolean containsSameValue(List<List<Integer>> pairs, int num1, int num2) {
        for (List<Integer> pair : pairs) {
            if (num1 == pair.get(0) || num1 == pair.get(1)
                    || num2 == pair.get(0) || num2 == pair.get(1)) {
                return true;
            }
        }
        return false;
    }

    private int getCombos(int value) {
        long upper = 1;
        for (int i = 1; i <= value; i++) {
            upper *= i;
        }
        long lower = 1;
        for (int i = 1; i <= (value - 2); i++)  {
            lower *= i;
        }
        return (int)(upper / (2 * lower));
    }

    // all possible product
    // 2*6 12   3*4
    // 3*6
    // 4*6
    public int tupleSameProductI(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i< nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] * nums[l] == nums[j] * nums[k]
                                || nums[i] * nums[k] == nums[j] * nums[l]) {
                            count += 8;
                        }
                    }
                }
            }
        }
        return count;
    }
}
