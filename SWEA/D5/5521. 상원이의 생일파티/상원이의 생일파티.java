import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static List<Integer>[] g;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			g = new ArrayList[N+1];
			for (int i = 0; i < N+1; i++) g[i] = new ArrayList<>();
			v = new boolean[N+1];
			int answer = 0;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				g[a].add(b);
				g[b].add(a);
			}
			ArrayDeque<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {1, 0});
			v[1] = true;
			while(!q.isEmpty()) {
				int[] n = q.poll();
				for (int a : g[n[0]]) {
					if (v[a]) continue;
					v[a] = true;
					answer++;
					if (n[1] != 1) {
						q.offer(new int[] {a, n[1]+1});
					}
				}
			}
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(answer);
			System.out.println(sb);
		}

	}

}
