import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static List<Pos> house;
	static class Pos {
		int x, y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int getCost(int k) {
	    return k * k + (k - 1) * (k - 1);
	}
	
	static int mahatten(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1 - y2);
	}
	
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    T = Integer.parseInt(br.readLine());

	    for (int t = 1; t <= T; t++) {
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        house = new ArrayList<>();

	        for (int i = 0; i < N; i++) {
	            st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < N; j++) {
	                int c = Integer.parseInt(st.nextToken());
	                if (c == 1) house.add(new Pos(i, j));
	            }
	        }

	        int answer = 0;

	        for (int cx = 0; cx < N; cx++) {
	            for (int cy = 0; cy < N; cy++) {
	                for (int k = 1; k <= N + 1; k++) {
	                    int cnt = 0;
	                    for (Pos h : house) {
	                        if (mahatten(cx, cy, h.x, h.y) < k) {
	                            cnt++;
	                        }
	                    }
	                    if (cnt * M >= getCost(k)) {
	                        answer = Math.max(answer, cnt);
	                    }
	                }
	            }
	        }
	        sb.append("#").append(t).append(" ").append(answer).append("\n");
	    }
	    System.out.print(sb);
	}

}
