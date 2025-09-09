import java.io.*;
import java.util.*;

public class Main {
	static int N, maxDepth;
	static int[][] up;
	static int[] depth;
	static List<Integer>[] g;

	static void dfs(int v, int p) {
		up[0][v] = p;
		depth[v] = (p == 0) ? 0 : depth[p] + 1;
		for (int to : g[v])
			if (to != p)
				dfs(to, v);
	}
	
	static int lca(int u, int v) {
		if (depth[u] < depth[v]) {int t = u; u = v; v = t;}
		
		int diff = depth[u] - depth[v];
		for (int i = 0; i < maxDepth; i++) if (((diff >> i) & 1) == 1) u = up[i][u];
		if (u == v) return u;
		
		for (int k = maxDepth -1; k >= 0; k--) {
			if (up[k][u] != up[k][v]) {
				u = up[k][u];
				v = up[k][v];
			}
		}
		return up[0][u];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		g = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			g[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			g[u].add(v);
			g[v].add(u);
		}

		maxDepth = 1;
		while ((1 << maxDepth) <= N)
			maxDepth++;
		up = new int[maxDepth][N + 1];
		depth = new int[N + 1];
		dfs(1, 0);

		for (int i = 1; i < maxDepth; i++) {
			for (int j = 1; j <= N; j++) {
				int mid = up[i - 1][j];
				up[i][j] = (mid == 0) ? 0 : up[i - 1][mid];
			}
		}
		int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append('\n');
        }
        System.out.print(sb);
	}

}
