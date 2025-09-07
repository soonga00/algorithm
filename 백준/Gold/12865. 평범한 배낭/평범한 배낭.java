import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] item = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			item[i][0] = Integer.parseInt(st.nextToken());
			item[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[K+1];
		for(int i = 0; i < N; i++) {
			for (int j = K; j > item[i][0] - 1; j--) {
				dp[j] = Math.max(dp[j], dp[j-item[i][0]] + item[i][1]);
			}
		}
		System.out.println(dp[K]);
	}

}
