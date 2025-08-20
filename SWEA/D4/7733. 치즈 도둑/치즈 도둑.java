import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[][] g;
	static boolean[][] v;
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
	static int getCnt(int day) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (v[i][j] || g[i][j] <= day)
					continue;
				v[i][j] = true;
				ArrayDeque<int[]> q = new ArrayDeque<>();
				q.offer(new int[] {i, j});
				cnt++;
				while(!q.isEmpty()) {
					int[] n = q.poll();
					for (int k = 0; k < 4; k++) {
						int nx = n[0] + dx[k];
						int ny = n[1] + dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny] || g[nx][ny] <= day) continue;
						v[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			g = new int[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int v = Integer.parseInt(st.nextToken());
					max = Math.max(max, v);
					g[i][j] = v;
				}
			}
			int answer = 1;
			for (int i = 1; i < max; i++) {
				v = new boolean[N][N];
				int cnt = getCnt(i);
				answer = Math.max(answer, cnt);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(answer);
			System.out.println(sb);

		}

	}

}
