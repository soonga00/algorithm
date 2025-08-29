import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, X, answer;
    static int[][] g;

    static boolean check(int[] line) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = line[i+1] - line[i];
            if (diff == 0) continue;

            if (diff == 1) {
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || line[j] != line[i] || used[j]) return false;
                    used[j] = true;
                }
            }
            else if (diff == -1) {
                for (int j = i+1; j <= i+X; j++) {
                    if (j >= N || line[j] != line[i+1] || used[j]) return false;
                    used[j] = true;
                }
            }
            else return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/d9_4014_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            g = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;

            for (int i = 0; i < N; i++) {
                if (check(g[i])) answer++;
            }

            for (int j = 0; j < N; j++) {
                int[] col = new int[N];
                for (int i = 0; i < N; i++) col[i] = g[i][j];
                if (check(col)) answer++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append('#').append(t).append(' ').append(answer);
            System.out.println(sb);
        }
    }
}
