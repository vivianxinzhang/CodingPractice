package FileReadWrite;
import java.io.*;


public class Test {
    public static void main(String args[]) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/Shared/workspace/testdata/input.txt");
        // Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}