import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {0, 1, 1};
    static final int[] dy = {1, 0, 1};
    static int N;
    static int[][] g;
    static int[][][] dp;

    static void move(int x, int y, int inD, int outD) {
        int nx = x + dx[outD];
        int ny = y + dy[outD];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return;
        if (g[nx][ny] == 1) return;

        if (outD == 2) {
            if (g[x + 1][y] == 1 || g[x][y + 1] == 1 || g[x + 1][y + 1] == 1) return;
        }

        dp[nx][ny][outD] += dp[x][y][inD];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        g = new int[N][N];
        dp = new int[N][N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    if (dp[i][j][k] == 0) continue;

                    if (k == 0) {
                        move(i, j, k, 0);
                        move(i, j, k, 2);
                    } else if (k == 1) {
                        move(i, j, k, 1);
                        move(i, j, k, 2);
                    } else {
                        move(i, j, k, 0);
                        move(i, j, k, 1);
                        move(i, j, k, 2);
                    }
                }
            }
        }

        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }
}
