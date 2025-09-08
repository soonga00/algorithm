import java.util.*;
import java.io.*;

class Solution {
	static int T, N;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int[] dp = new int[N];
			Arrays.fill(dp, 1);
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] <= arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			int ans = 0;
            for (int x : dp) ans = Math.max(ans, x);
            sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
}