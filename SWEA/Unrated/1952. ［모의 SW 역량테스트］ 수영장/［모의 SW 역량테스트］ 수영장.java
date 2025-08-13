import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int[] fee, plan, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			dp = new int[13];
			fee = new int[4];
			plan = new int[12];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++)
				fee[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++)
				plan[i] = Integer.parseInt(st.nextToken());

			for (int i = 11; i >= 0; i--) {
				int f = plan[i] * fee[0];

				if (f > fee[1])
					f = fee[1];

				dp[i] = dp[i + 1] + f;
				dp[i] = Math.min(dp[i], dp[Math.min(12, i + 3)] + fee[2]);

			}
			StringBuilder sb = new StringBuilder("#");
			sb.append(t).append(" ").append(Math.min(dp[0], fee[3]));
			System.out.println(sb);
		}
	}

}
