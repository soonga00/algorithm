import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] g, dp;
	static final int INF = 1_000_000_000;
	
	static int tsp(int visited, int cur) {
		if (visited == (1 << N) -1) {
			return g[cur][0] == 0 ? INF : g[cur][0];
		}
		if (dp[visited][cur] != -1) return dp[visited][cur];
		
		int ans = INF;
		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) != 0 || g[cur][i] == 0) continue;
			ans = Math.min(ans,  tsp(visited | (1 << i), i) + g[cur][i]);
		}
		return dp[visited][cur] = ans;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		g = new int[N][N];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[1 << N][N];
		for (int[] r : dp) Arrays.fill(r, -1);
		System.out.println(tsp(1, 0));
	}

}
