import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	
	static int manhatten(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			int[] x = new int[N+2];
			int[] y = new int[N+2];
			st = new StringTokenizer(br.readLine(), " ");
			x[0] = Integer.parseInt(st.nextToken());
			y[0] = Integer.parseInt(st.nextToken());
			for (int i = 1; i < N+1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			x[N+1] = Integer.parseInt(st.nextToken());
			y[N+1] = Integer.parseInt(st.nextToken());
			Queue<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N+2];

			q.add(0);
			visited[0] = true;
			boolean flag = false;
			while (!q.isEmpty()) {
			    int cur = q.poll();
			    if (cur == N+1) {
			        flag = true;
			        break;
			    }
			    for (int next = 0; next < N+2; next++) {
			        if (!visited[next] && manhatten(x[cur], y[cur], x[next], y[next]) <= 1000) {
			            visited[next] = true;
			            q.add(next);
			        }
			    }
			}
			System.out.println(flag ? "happy" : "sad");
		}
		
		

	}

}
