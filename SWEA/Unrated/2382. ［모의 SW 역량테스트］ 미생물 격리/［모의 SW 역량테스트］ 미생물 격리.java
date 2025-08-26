import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K, M, answer;
	static final int[] dx = {0, -1, 1, 0, 0};
	static final int[] dy = {0, 0, 0, -1, 1};
	static int[][][]g;
	
	static int changeDir(int i) {
		if (i == 1) {
			return 2;
		} else if (i == 2) {
			return 1;
		} else if (i == 3) {
			return 4;
		} else {
			return 3;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			g = new int[N][N][2];
			answer = 0;
			ArrayDeque<int[]> q = new ArrayDeque<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				answer += c;
				g[x][y][0] = c;
				g[x][y][1] = d;
				q.offer(new int[] {x, y});
			}
			
			while (M-- > 0) {
			    int[][] sum = new int[N][N];
			    int[][] maxCnt = new int[N][N];
			    int[][] dir = new int[N][N];
			    boolean[][] inQ = new boolean[N][N];

			    int num = q.size();
			    for (int i = 0; i < num; i++) {
			        int[] now = q.poll();
			        int x = now[0], y = now[1];
			        int c = g[x][y][0];
			        int d = g[x][y][1];

			        g[x][y][0] = 0; g[x][y][1] = 0;

			        int nx = x + dx[d];
			        int ny = y + dy[d];

			        if (nx == 0 || ny == 0 || nx == N-1 || ny == N-1) {
			            c = c / 2;
			            if (c == 0) continue;
			            d = changeDir(d);
			        }

			        sum[nx][ny] += c;
			        if (c > maxCnt[nx][ny]) {
			            maxCnt[nx][ny] = c;
			            dir[nx][ny] = d;
			        }

			        if (!inQ[nx][ny]) {
			            q.offer(new int[]{nx, ny});
			            inQ[nx][ny] = true;
			        }
			    }

			    answer = 0;
			    int qsz = q.size();
			    for (int i = 0; i < qsz; i++) {
			        int[] pos = q.poll();
			        int x = pos[0], y = pos[1];
			        if (sum[x][y] == 0) continue;
			        g[x][y][0] = sum[x][y];
			        g[x][y][1] = dir[x][y];
			        answer += sum[x][y];
			        q.offer(new int[]{x, y});
			    }
			}
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(answer);
			System.out.println(sb);
		}
	}

}
