package Contest;
import java.util.Arrays;

public class DecodeXORedArray {
    public static void main(String[] args) {
        DecodeXORedArray s = new DecodeXORedArray();
        int[] encoded = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.decode(encoded, 1)));  // [1, 0, 2, 1]

        encoded = new int[] {6, 2, 7, 3};
        System.out.println(Arrays.toString(s.decode(encoded, 4)));  // [4, 2, 0, 7, 4]
    }

    // Time O(n)
    // Space O(1)
    public int[] decode(int[] encoded, int first) {
        int[] result = new int[encoded.length + 1];
        result[0] = first;
        for (int i = 1; i < result.length; i++) {
            result[i] = encoded[i - 1] ^ result[i - 1];
        }
        return result;
    }
}
