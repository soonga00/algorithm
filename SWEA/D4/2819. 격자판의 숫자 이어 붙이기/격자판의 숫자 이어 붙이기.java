import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static String[][] g;
	static HashSet<String> set;
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1,};

	static class Node {
		int x, y;
		String v;

		Node(int x, int y, String v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	static void find(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offer(new Node(x, y, g[x][y]));
		while(!q.isEmpty()) {
			Node n = q.poll();
			if (n.v.length() == 7) {
				set.add(n.v);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
				q.offer(new Node(nx, ny, n.v+g[nx][ny]));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			g = new String[4][4];
			set = new HashSet<>();
			for (int i =0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 4; j++) {
					g[i][j] = st.nextToken();
				}
			}
			
			for (int i =0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					find(i, j);
				}
			}
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(set.size());
            System.out.println(sb);
		}
	}

}
