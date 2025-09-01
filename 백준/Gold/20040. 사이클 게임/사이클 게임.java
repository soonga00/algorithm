import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;
	
	static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return;
		if (a > b) {int T = a; a = b; b = T;}
		parent[b] = a;
	}
	
	public static void main(String[] args) throws Exception {
		//--------------솔루션 코드를 작성하세요.---------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N]; for (int i =0; i < N; i++) parent[i] = i;
		int cnt = 0;
		boolean flag = false;
		for (int i =0; i < M; i++) {
			cnt++;
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (find(a) == find(b)) {
				flag = true;
				break;
			}
			union(a, b);
		}
		System.out.println(flag ? cnt : 0);
	}

}
