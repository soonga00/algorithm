import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, K, answer;
    static int[][] g;
    static boolean[][] v;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static void dfs(int x, int y, boolean cutUsed, int h, int len) {
        answer = Math.max(answer, len);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny]) continue;

            if (g[nx][ny] < h) {
                v[nx][ny] = true;
                dfs(nx, ny, cutUsed, g[nx][ny], len + 1);
                v[nx][ny] = false;
            } else if (!cutUsed && (g[nx][ny] - K) < h) {
                v[nx][ny] = true;
                dfs(nx, ny, true, h - 1, len + 1);
                v[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/d9_1949_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            g = new int[N][N];
            v = new boolean[N][N];
            answer = 0;

            int top = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                    top = Math.max(top, g[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (g[i][j] == top) {
                        v[i][j] = true;
                        dfs(i, j, false, g[i][j], 1);
                        v[i][j] = false;
                    }
                }
            }
			StringBuilder sb = new StringBuilder();
            sb.append('#').append(t).append(' ').append(answer);
            System.out.println(sb);
        }
    }
}
