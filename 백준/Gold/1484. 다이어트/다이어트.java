import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());

        List<Integer> result = new ArrayList<>();
        int left = 1;
        int right = 2;  // a

        while (right <= 100000) {
            long diff = (long) right * right - (long) left * left;

            if (diff == g) {
                result.add(right);
                right++;
            } else if (diff < g) {
                right++;
            } else {
                left++;
            }
        }

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int x : result) {
                System.out.println(x);
            }
        }
    }
}
