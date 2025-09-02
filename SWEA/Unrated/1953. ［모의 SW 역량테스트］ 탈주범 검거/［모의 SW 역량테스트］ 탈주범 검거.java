import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, R, C, L;
    static int[][] g;
    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    static final boolean[][] OPEN = new boolean[][]{
        {false,false,false,false},
        {true, true, true, true},
        {true, true, false, false},
        {false, false, true, true},
        {false, true, true, false},
        {true, false, true, false},
        {true, false, false, true},
        {false, true, false, true}
    };

    static int opp(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            g = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) g[i][j] = Integer.parseInt(st.nextToken());
            }

            boolean[][] v = new boolean[N][M];
            ArrayDeque<int[]> q = new ArrayDeque<>();

            if (g[R][C] == 0) {
                System.out.println("#" + t + " 0");
                continue;
            }

            v[R][C] = true;
            q.offer(new int[]{R, C, 1});
            int cnt = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1], time = cur[2];
                cnt++;

                if (time == L) continue;

                int typeHere = g[x][y];
                for (int d = 0; d < 4; d++) {
                    if (!OPEN[typeHere][d]) continue;

                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (v[nx][ny]) continue;
                    if (g[nx][ny] == 0) continue;

                    int typeNext = g[nx][ny];
                    if (!OPEN[typeNext][opp(d)]) continue;

                    v[nx][ny] = true;
                    q.offer(new int[]{nx, ny, time + 1});
                }
            }

            System.out.println("#" + t + " " + cnt);
        }
    }
}
