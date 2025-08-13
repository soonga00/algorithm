import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, B, answer;
	static int[] S, dp;
	static void comb(int cnt, int sum) {
		if (sum >= B) {
			answer = Math.min(answer, sum - B);
			return;
		}
		if (cnt == N) {
			return;
		}
		if (sum + dp[cnt] < B)
			return;
		if (answer != Integer.MAX_VALUE && sum - B >= answer)
			return;
		comb(cnt + 1, sum + S[cnt]);
		comb(cnt + 1, sum);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			S = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			dp = new int[N + 1];
			for (int i = N - 1; i > -1; i--)
				dp[i] = dp[i + 1] + S[i];
			answer = Integer.MAX_VALUE;
			comb(0, 0);
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}