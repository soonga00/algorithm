import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static int[] parent;
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		if (x > y) {int t = x; x = y; y = t;}
		parent[y] = x;
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
			parent = new int[N+1]; for (int i =0; i < N+1; i++) parent[i] = i;
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ');
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (c == 0) {
					union(a,b);
				}else if(find(a) == find(b)) sb.append(1);
				else sb.append(0);
			}
			System.out.println(sb);
		}
		

	}

}
