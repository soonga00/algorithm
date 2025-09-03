import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, L;
    static int[] score, cal;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            cal = new int[N];
            dp = new int[L + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                for (int j = L; j >= cal[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - cal[i]] + score[i]);
                }
            }

            System.out.printf("#%d %d\n", t, dp[L]);
        }
    }
}
