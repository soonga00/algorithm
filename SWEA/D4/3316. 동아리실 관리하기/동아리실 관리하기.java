import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static final long MOD = 1_000_000_007L;
    static final int A = 1<<3, B = 1<<2, C = 1<<1, D = 1;

    static int reqMask(char ch) {
        if (ch == 'A') return A;
        if (ch == 'B') return B;
        if (ch == 'C') return C;
        return D;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String line = br.readLine().trim();
            int N = line.length();

            long[][] dp = new long[N][16];

            int req0 = reqMask(line.charAt(0));
            for (int mask = 1; mask < 16; mask++) {
                if ((mask & A) != 0 && (mask & req0) != 0) {
                    dp[0][mask] = 1;
                }
            }


            for (int day = 1; day < N; day++) {
                int req = reqMask(line.charAt(day));
                for (int mask = 1; mask < 16; mask++) {
                    if ((mask & req) == 0) continue;
                    long sum = 0;
                    for (int prev = 1; prev < 16; prev++) {
                        if (dp[day-1][prev] == 0) continue;
                        if ((prev & mask) == 0) continue;
                        sum += dp[day-1][prev];
                        if (sum >= MOD) sum -= MOD;
                    }
                    dp[day][mask] = sum;
                }
            }

            long ans = 0;
            for (int mask = 1; mask < 16; mask++) {
                ans += dp[N-1][mask];
                if (ans >= MOD) ans -= MOD;
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}
