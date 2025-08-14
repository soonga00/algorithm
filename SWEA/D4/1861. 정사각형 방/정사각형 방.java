import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, minNum, maxCnt;
	static int[][] g, dist;
	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, -1, 0, 1};
	
	static void goRoom(int x, int y) {
		if (dist[x][y] != -1) return;
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if (nx < 0 || nx>= N || ny <0 || ny >= N) continue;
			if (g[x][y] - g[nx][ny] == 1 && dist[nx][ny] != -1) {
				dist[x][y] = dist[nx][ny] - 1;
				return;
			} else if (g[nx][ny] - g[x][y] == 1 && dist[nx][ny] != -1) {
				dist[x][y] = dist[nx][ny] + 1;
				if (maxCnt < dist[x][y]) {
					maxCnt = dist[x][y];
					minNum = g[x][y];
				}
				return;
			}
		}
		int cnt = 1;
		int sx = x, sy = y;
		while (true) {
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx < 0 || nx>= N || ny <0 || ny >= N) continue;
				if (g[nx][ny] - g[x][y] == 1) {
					cnt++;
					flag = true;
					x = nx; y = ny;
				}
			}
			if (!flag) {
				dist[sx][sy] = cnt;
				break;
			}
		}
		if (maxCnt < dist[sx][sy]) {
			maxCnt = dist[sx][sy];
			minNum = g[sx][sy];
		}
//		System.out.println(minNum +" " + maxCnt );
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			g = new int[N][N];
			dist = new int[N][N];
			
			for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					g[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			minNum = -1;
			maxCnt = -1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					goRoom(i, j);
//					for (int k = 0; k < N; k++) System.out.println(Arrays.toString(dist[k]));
//					System.out.println();
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dist[i][j] == maxCnt && g[i][j] < minNum) {
						minNum = g[i][j];

					}
				}
			}
			System.out.printf("#%d %d %d\n", t, minNum, maxCnt);
		}

	}

}
