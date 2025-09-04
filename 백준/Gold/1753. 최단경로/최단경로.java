import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static List<int[]>[]g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		g = new List[V+1]; for (int i = 0; i < V+1; i++) g[i] = new ArrayList<>();
		int start = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			g[u].add(new int[] {v, w});
		}
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] v = new boolean[V+1];
		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {start, dist[start]});
		while(!pq.isEmpty()) {
			int[] n = pq.poll();
			int minVertex = n[0];
			int min = n[1];
			if (v[minVertex]) continue;
			v[minVertex] = true;
			for (int[] jc : g[minVertex]) {
				if (!v[jc[0]] && dist[jc[0]] > min + jc[1]) {
					dist[jc[0]] = min + jc[1];
					pq.offer(new int[] {jc[0], dist[jc[0]]});
				}
			}
		}
		for (int i = 1; i < V+1; i++) {
			System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
		}
	}

}
