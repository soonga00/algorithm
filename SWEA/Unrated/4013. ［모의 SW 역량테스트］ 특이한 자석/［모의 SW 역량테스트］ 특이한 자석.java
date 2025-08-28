import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, K;
	static boolean[][] magnet;
	static boolean[] v;
	static int[] idxArr;
	
	static void rotate(int idx, int checkIdx, int dir) {
		if (checkIdx < 0 || checkIdx > 3) return;
		if (idx < checkIdx) {
			if (magnet[idx][(idxArr[idx]+2)%8] 
					!= magnet[checkIdx][(idxArr[checkIdx]+6)%8]) {
				v[checkIdx] = true;
				rotate(checkIdx, checkIdx+1, dir*(-1));
			}
			return;
		}else {
			if (magnet[checkIdx][(idxArr[checkIdx]+2)%8] 
					!= magnet[idx][(idxArr[idx]+6)%8]) {
				v[checkIdx] = true;
				rotate(checkIdx, checkIdx-1, dir*(-1));
			}
		}
	}
	
	static void changeIdx(int i, int d) {
		if (d > 0) {
			idxArr[i] = (idxArr[i]+7)%8;
		}else {
			idxArr[i] = (idxArr[i]+1)%8;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			magnet = new boolean[4][8];
			idxArr = new int[4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					int m = Integer.parseInt(st.nextToken());
					if (m == 1) magnet[i][j] = true;
				}
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				v = new boolean[4];
				v[idx] = true;
				if (idx != 0) {
					rotate(idx, idx-1, d);
				}
				if (idx != 3) {
					rotate(idx, idx+1, d);
				}
				for (int j = 0; j < 4; j++) {
					if (!v[j]) continue;
					if ((idx + j) % 2 == 0) {
						changeIdx(j,d);
					}else {
						changeIdx(j, d*(-1));
					}
				}	
			}
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if (magnet[i][idxArr[i]]) answer += Math.pow(2, i);
			}
			sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(answer);
			System.out.println(sb);
		}
	}


}
