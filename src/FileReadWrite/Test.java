package FileReadWrite;
import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String args[]) throws IOException {
        String dirname = "/Users/Shared/workspace/testdata/testdir";
        File d = new File(dirname);

        // Create a directory now
        String[] paths = d.list();
        for (String p : paths) {
            System.out.println(p);
        }
    }
}

class MyBufferedReader {
    private FileInputStream in;
    private StringBuffer buffer;

    public MyBufferedReader(FileInputStream in) {
        this.in = in;
        buffer = new StringBuffer();
    }

    public String nextLine() throws IOException {
        while (true) {
            int c = in.read();
            if (c == -1 || c == '\n') {
                break;
            }
            buffer.append((char)c);
        }
        String output = buffer.toString();
        buffer = new StringBuffer();
        return output;
    }
}