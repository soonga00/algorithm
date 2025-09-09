import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;

    static int lowerBound(int[] a, int size, int value) {
        int l = 0, r = size; // [l, r)
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= value) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            int N = Integer.parseInt(line);

            int[] arr = new int[N];
            int idx = 0;
            while (idx < N) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens() && idx < N) {
                    arr[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N];
            int size = 0;

            for (int x : arr) {
                if (size == 0 || dp[size - 1] < x) {
                    dp[size++] = x;
                } else {
                    int pos = lowerBound(dp, size, x);
                    dp[pos] = x;
                }
            }

            sb.append(size).append('\n');
        }

        System.out.print(sb.toString());
    }
}
