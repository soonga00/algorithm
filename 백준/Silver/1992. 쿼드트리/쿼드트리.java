import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] g;
	static String answer;
	
	static void divide(int x, int y, int n) {
        if (isUniform(x, y, n)) {
            if (g[x][y] == 0) answer+="0";
            else answer+="1";
            return;
        }
        int m = n / 2;
        answer += "(";
        divide(x, y, m);
        divide(x, y + m, m);
        divide(x + m, y, m);
        divide(x + m, y + m, m);
        answer += ")";
    }

    static boolean isUniform(int x, int y, int n) {
        int color = g[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (g[i][j] != color) return false;
            }
        }
        return true;
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		g = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				g[i][j] = Integer.parseInt(line[j]);
			}
		}
		answer = "";
		divide(0, 0, N);
		System.out.println(answer);
	}

}
