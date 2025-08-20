import java.io.*;
import java.util.*;

class Node {
	int x, y, t;
	Node(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class Solution {
	static int T, N, M;
	static char[][]g;
	static int[][] devilArea;
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
	static int go(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		v[x][y] = true;
		q.offer(new Node(x, y, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (g[n.x][n.y] == 'D') {
				return n.t;
			}
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (v[nx][ny] || g[nx][ny] == 'X') continue;
				if (devilArea[nx][ny] != -1 && devilArea[nx][ny] <= n.t + 1) continue;

				v[nx][ny] = true;
				q.offer(new Node(nx, ny, n.t+1));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d5_7793_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			g = new char[N][M];
			devilArea = new int[N][M];
			for (int i = 0; i < N; i++) Arrays.fill(devilArea[i], -1);
			int x = 0, y = 0;
			List<int[]> devil = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (line[j] == 'S') {
						x = i; y = j;
					} else if (line[j] == '*') {
						devil.add(new int[] {i, j});
					}
					g[i][j] = line[j];
				}
			}
			ArrayDeque<Node> q = new ArrayDeque<>();
			for (int[] d : devil) {
			    devilArea[d[0]][d[1]] = 0;
			    q.offer(new Node(d[0], d[1], 0));
			}

			while (!q.isEmpty()) {
			    Node n = q.poll();
			    for (int i = 0; i < 4; i++) {
			        int nx = n.x + dx[i];
			        int ny = n.y + dy[i];
			        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			        if (g[nx][ny] == 'X' || g[nx][ny] == 'D') continue;
			        if (devilArea[nx][ny] != -1) continue;

			        devilArea[nx][ny] = n.t + 1;
			        q.offer(new Node(nx, ny, n.t + 1));
			    }
			}
			int time = go(x, y);
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(time == -1 ? "GAME OVER" : time);
			System.out.println(sb);
		}

	}

}
