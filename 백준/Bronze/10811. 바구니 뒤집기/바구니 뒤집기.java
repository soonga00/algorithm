import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) arr[i] = i;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			while (l < r) {
				int t = arr[l];
				arr[l++] = arr[r];
				arr[r--] = t;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}

}
