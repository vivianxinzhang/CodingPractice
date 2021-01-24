package Contest;

public class LatestTimeByReplacingHiddenDigits {
    public static void main(String[] args) {
        LatestTimeByReplacingHiddenDigits s = new LatestTimeByReplacingHiddenDigits();
        System.out.println(s.maximumTime("??:??"));     // 23:59

        System.out.println(s.maximumTime("1?:22"));     // 19:22

        System.out.println(s.maximumTime("0?:3?"));     // 09:39
    }

    // The valid times are those inclusively between 00:00 and 23:59.
    // Return the latest valid time you can get from time by replacing the hidden digits.
    // Case 1: :
    // Case 2: number
    // Case 3: ? replace
    // ??:??
    // Time O(n)
    // Space O(n)
    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        char[] array = time.toCharArray();
        int i = 0;
        while (i < array.length) {
            if (array[i] == '?') {
                if (i == 0) {
                    if (array[i + 1] == '?') {   // "?0:15"
                        array[i] = '2';
                        array[i + 1] = '3';
                    } else if (array[i + 1] <= '3') {
                        array[i] = '2';
                    } else {
                        array[i] = '1';
                    }
                    i += 3;
                } else if (i == 1) { // 2?:?0
                    if (array[i - 1] == '2') {
                        array[i] = '3';
                    } else {
                        array[i] = '9';
                    }
                    i += 2;
                }  else if (i == 3) { // 23:??
                    array[i] = '5';
                    i++;
                } else if (i == 4) {
                    array[i] = '9';
                    i++;
                }
            } else {
                i++;
            }
        }
        return new String(array);
    }
}
