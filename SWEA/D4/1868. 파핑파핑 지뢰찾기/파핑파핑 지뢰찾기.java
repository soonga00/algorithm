import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[][] g;
	static final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static boolean[][] v;
	static int answer;
	
	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, g[x][y]});
		v[x][y] = true;
		while(!q.isEmpty()) {
			int[] n = q.poll();
			if (g[n[0]][n[1]] != 0) continue;

	        for (int i = 0; i < 8; i++) {
	            int nx = n[0] + dx[i];
	            int ny = n[1] + dy[i];
	            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
	            if (g[nx][ny] == -1 || v[nx][ny]) continue;
	            v[nx][ny] = true;
	            q.offer(new int[] {nx, ny});
	        }
		}
	}
	
	static int getBombCnt(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (g[nx][ny] == -1) cnt++;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			g = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					g[i][j] = line[j].equals("*") ? -1 : 0;
				}
			}
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(g[i][j] == -1)v[i][j] = true;
					else g[i][j] = getBombCnt(i, j);
				}
			}
			answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(g[i][j] == 0 && !v[i][j]) {
						answer++;
						bfs(i, j);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j]) {
						v[i][j] = true;
						answer++;
					}
				}
			}
			System.out.printf("#%d %d\n", t, answer);
			
		}

	}

}
