import java.io.*;
import java.util.*;

public class Solution {
	static int T = 10, N, S;
	static HashSet<Integer>[] g;
	static TreeMap<Integer, TreeSet<Integer>> contact;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			g = new HashSet[101]; for (int i = 0; i < 101; i++) g[i] = new HashSet<>();
			contact = new TreeMap<>();
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				g[from].add(to);
			}
			ArrayDeque<int[]> q = new ArrayDeque<>();
			boolean[] v = new boolean[101];
			v[S] = true;
			contact.computeIfAbsent(0, k -> new TreeSet<>()).add(S);
			q.offer(new int[] {S, 0});
			while (!q.isEmpty()) {
				int[] n = q.poll();
				for(int i : g[n[0]]) {
					if(v[i]) continue;
					v[i] = true;
					q.offer(new int[] {i, n[1]+1});
					contact.computeIfAbsent(n[1]+1, k -> new TreeSet<>()).add(i);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(contact.lastEntry().getValue().last());
            System.out.println(sb);
		}
	}

}