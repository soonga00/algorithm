import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[] v = new int[N];
			int[] c = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				v[i] = Integer.parseInt(st.nextToken());
				c[i] = Integer.parseInt(st.nextToken());
			}
			int[] dp = new int[K+1];
			for (int i = 0; i < N; i++) {
				for (int j = K; j > v[i]-1; j--) {
					dp[j] = Math.max(dp[j], dp[j - v[i]] + c[i]);
				}
			}
			sb.append(dp[K]).append("\n");
		}
		System.out.println(sb);
	}

}
