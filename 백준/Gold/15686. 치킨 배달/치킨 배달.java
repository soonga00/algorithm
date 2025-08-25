import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<int[]> chicken, house;
	static List<List<int[]>> selectedChicken;
	
	static void comb(int cnt, int start, List<int[]> chickens) {
		if (cnt == M) {
			selectedChicken.add(new ArrayList<>(chickens));
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			chickens.add(chicken.get(i));
			comb(cnt+1, i+1, chickens);
			chickens.remove(cnt);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		selectedChicken = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 1) house.add(new int[] {i, j});
				else if(v == 2) chicken.add(new int[] {i, j});
			}
		}
		int result = Integer.MAX_VALUE;
		comb(0, 0, new ArrayList<>());
		for (List<int[]> c : selectedChicken) {
			int totalChickenLen = 0;
			for (int[] h : house) {
				int chickenLen = Integer.MAX_VALUE;
				for (int[] p : c) {
					chickenLen = Math.min(chickenLen, Math.abs(h[0] - p[0]) + Math.abs(h[1] - p[1]));
				}
				totalChickenLen += chickenLen;
			}
			result = Math.min(totalChickenLen, result);
		}
		System.out.println(result);
	}

}
