import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] g;
	static final int[] dx = {1, 0, 1};
	static final int[] dy = {0, 1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		g = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] dp = new int[N][M];
		dp[0][0] = g[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 3; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					dp[nx][ny] = Math.max(dp[i][j] + g[nx][ny], dp[nx][ny]);
				}

			}
		}
		System.out.println(dp[N-1][M-1]);

	}

}
