import java.io.*;
import java.util.*;

public class Solution {
	static int T = 10, V, E;
	static List<Integer>[] g;
	static int[] indegree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			g = new List[V+1]; for (int i = 0; i < V+1; i++) g[i] = new ArrayList<>();
			indegree = new int[V+1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				indegree[to]++;
				g[from].add(to);
			}
			ArrayDeque<Integer> q = new ArrayDeque<>();
			for (int i = 1; i < V+1; i++) {
				if (indegree[i] == 0) {
					sb.append(i).append(' ');
					q.offer(i);
				}
			}
			while (!q.isEmpty()) {
				int from = q.poll();
				for (int to: g[from]) {
					if (indegree[to] > 0) {
						indegree[to]--;
						if (indegree[to] == 0) {
							sb.append(to).append(' ');
							q.offer(to);
						}
					}
				}
			}
			System.out.println(sb);
		}
		
	}

}
