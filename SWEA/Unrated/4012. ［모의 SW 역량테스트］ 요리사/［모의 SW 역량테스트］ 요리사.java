import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, answer;
	static int[][] g;
	static boolean[] sel;

	static void comb(int idx, int cnt) {
		if (cnt == N / 2) {
			calc();
			return;
		}
		if (idx == N)
			return;

		sel[idx] = true;
		comb(idx + 1, cnt + 1);
		sel[idx] = false;
		comb(idx + 1, cnt);
	}

	static void calc() {
		int sumA = 0, sumB = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (sel[i] && sel[j])
					sumA += g[i][j] + g[j][i];
				else if (!sel[i] && !sel[j])
					sumB += g[i][j] + g[j][i];
			}
		}
		answer = Math.min(answer, Math.abs(sumA - sumB));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			g = new int[N][N];
			sel = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					g[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer = Integer.MAX_VALUE;
			comb(0, 0);

			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
