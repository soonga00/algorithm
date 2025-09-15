import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<int[]>[] g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		g = new List[N+1]; for (int i = 0; i < N+1; i++) g[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			g[u].add(new int[] {v, w});
		}
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		boolean[] visited = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] {start, dist[start]});
		int answer = 0;
		while (!pq.isEmpty()) {
			int[] vw = pq.poll();
			int minVertex = vw[0];
			int min = vw[1];
			if (minVertex == end) {
				answer = min;
				break;
			}
			if (visited[minVertex]) continue;
			visited[minVertex] = true;
			for (int[] jc : g[minVertex]) {
				if (!visited[jc[0]] && dist[jc[0]] > min + jc[1]) {
					dist[jc[0]] = min + jc[1];
					pq.add(new int[] {jc[0], dist[jc[0]]});
				}
			}
		}
		System.out.println(answer);
	}

}
