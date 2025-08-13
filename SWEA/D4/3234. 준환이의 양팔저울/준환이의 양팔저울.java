import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, cnt;
    static int[] arr;
    static int totalSum;

    static void dfs(int usedMask, int left, int right, int remain) {
        if (usedMask == (1 << N) - 1) {
            cnt++;
            return;
        }

        if (left >= right + remain) {
            int restCnt = N - Integer.bitCount(usedMask);
            cnt += factorial(restCnt) * (1 << restCnt);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((usedMask & (1 << i)) != 0) continue;

            int nextRemain = remain - arr[i];

            if (left >= right + arr[i])
                dfs(usedMask | (1 << i), left, right + arr[i], nextRemain);

            dfs(usedMask | (1 << i), left + arr[i], right, nextRemain);
        }
    }

    static int factorial(int n) {
        int r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            totalSum = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                totalSum += arr[i];
            }
            cnt = 0;
            dfs(0, 0, 0, totalSum);
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
