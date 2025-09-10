import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[][] g;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			g = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
			    Arrays.fill(g[i], Integer.MAX_VALUE);
			    g[i][i] = 0; // 자기 자신은 0
			}

			for (int i = 1; i <= N; i++) {
			    for (int j = 1; j <= N; j++) {
			        if (Integer.parseInt(st.nextToken()) == 1) {
			            g[i][j] = 1; // 단방향만
			        }
			    }
			}

			for (int k = 1; k <= N; k++) {
			    for (int i = 1; i <= N; i++) {
			        for (int j = 1; j <= N; j++) {
			            if (g[i][k] != Integer.MAX_VALUE && g[k][j] != Integer.MAX_VALUE) {
			                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
			            }
			        }
			    }
			}

			int[] dist = new int[N+1];
			int answer = Integer.MAX_VALUE;
			for (int i = 1; i < N + 1; i++) {
				int cc = 0;
				for (int j = 1; j < N + 1; j++) {
					cc += g[i][j];
				}
				dist[i] = cc;
				answer = Math.min(answer, cc);
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

}
