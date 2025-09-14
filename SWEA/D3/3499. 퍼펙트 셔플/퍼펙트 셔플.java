import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			ArrayDeque<String> q1 = new ArrayDeque<>();
			ArrayDeque<String> q2 = new ArrayDeque<>();
			int half = N % 2 == 0 ? N/2 : N/2 + 1;
			for (int i = 0; i < N; i++) {
				if (i < half) q1.add(st.nextToken());
				else q2.add(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) sb.append(q1.poll());
				else sb.append(q2.poll());
				sb.append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);

	}

}
