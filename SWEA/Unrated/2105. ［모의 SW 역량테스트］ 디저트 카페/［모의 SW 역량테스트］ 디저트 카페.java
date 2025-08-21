import java.io.*;
import java.util.*;

class Solution {
    static int N, ans;
    static int[][] a;
    static final int[] dx = { 1, 1, -1, -1 };
    static final int[] dy = { 1, -1, -1, 1 };
    static boolean[][] visited;
    static boolean[] used;

    static void dfs(int sx, int sy, int x, int y, int d, int turns, int cnt) {
        for (int turn = 0; turn <= 1; turn++) {
            int nd = d + turn;
            if (nd >= 4) continue;
            int nx = x + dx[nd], ny = y + dy[nd];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (nx == sx && ny == sy) {
                if (turns + turn == 3 && cnt >= 4) ans = Math.max(ans, cnt);
                continue;
            }

            int dessert = a[nx][ny];
            if (visited[nx][ny] || used[dessert]) continue;

            visited[nx][ny] = true;
            used[dessert] = true;
            dfs(sx, sy, nx, ny, nd, turns + turn, cnt + 1);
            used[dessert] = false;
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            a = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) a[i][j] = Integer.parseInt(st.nextToken());
            }

            ans = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[N][N];
                    used = new boolean[101];
                    visited[i][j] = true;
                    used[a[i][j]] = true;
                    dfs(i, j, i, j, 0, 0, 1);
                }
            }

            out.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(out);
    }
}
