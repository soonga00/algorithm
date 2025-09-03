import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static long[] arr, tree;
	
	static long build(int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];
		int mid = (start+end)/2;
		return tree[node] = build(node*2, start, mid) + build(node*2+1, mid+1, end);
	}
	
	static long query(int node, int start, int end, int l, int r) {
		if (r < start || end < l) return 0;
		if (l <= start && end <= r) return tree[node];
		int mid = (start + end) / 2;
		return query(node*2, start, mid, l, r) +query(node*2+1, mid+1, end, l, r);
	}
	
	static void update(int node, int start, int end, int idx, long val) {
		if (start == end) {
			tree[node] = val;
			return;
		}
		int mid = (start+end) /2;
		if (idx <= mid) update(node*2, start, mid, idx, val);
		else update(node*2+1, mid+1, end, idx, val);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		tree = new long[4 *N];
		
		for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
		
		build(1, 1, N);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				update(1, 1, N, b, c);
			} else {
				sb.append(query(1, 1, N, b, (int)c)).append("\n");
			}
		}
		System.out.println(sb);
	}

}
