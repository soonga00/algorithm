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
		if (x == y) return;
		if (x > y) {int t = x; x = y; y = t;}
		parent[y] = x;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N+1]; for(int i = 0; i < N+1; i++) parent[i] = i;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			HashSet<Integer> set = new HashSet<>();
			for (int i = 1; i < N+1; i++) {
				find(i);
				set.add(parent[i]);
			}
			System.out.printf("#%d %d\n", t, set.size());
		}

	}

}
