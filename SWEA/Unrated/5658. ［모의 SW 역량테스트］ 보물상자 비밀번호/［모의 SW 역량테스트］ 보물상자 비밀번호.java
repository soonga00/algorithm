import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String s = br.readLine().trim();
            int len = N / 4;

            String ss = s + s;

            HashSet<Long> set = new HashSet<>();

            for (int r = 0; r < len; r++) {
                for (int k = 0; k < 4; k++) {
                    int start = r + k * len;
                    String part = ss.substring(start, start + len);
                    long val = Long.parseLong(part, 16);
                    set.add(val);
                }
            }

            ArrayList<Long> list = new ArrayList<>(set);
            list.sort(Comparator.reverseOrder());
            long ans = list.get(K - 1);

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
