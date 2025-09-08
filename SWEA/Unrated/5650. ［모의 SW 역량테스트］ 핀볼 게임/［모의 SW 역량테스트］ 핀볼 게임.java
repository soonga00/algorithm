import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, ans;
    static int[][] board, linkX, linkY;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static final int[][] changeD = {
            {2, 0, 3, 1},
            {2, 3, 1, 0},
            {1, 3, 0, 2},
            {3, 2, 0, 1}
    };

    static int run(int sx, int sy, int d) {
        int score = 0;
        int x = sx, y = sy;
        while (true) {
            x += dx[d];
            y += dy[d];
            int v = board[x][y];

            if (v == -1 || (x == sx && y == sy)) return score;

            if (v >= 1 && v <= 5) {
                if (v == 5) d = (d + 2) % 4;
                else d = changeD[v - 1][d];
                score++;
            } else if (v >= 6) {
                int nx = linkX[x][y];
                int ny = linkY[x][y];
                x = nx;
                y = ny;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N + 2][N + 2];
            linkX = new int[N + 2][N + 2];
            linkY = new int[N + 2][N + 2];
            List<int[]>[] hole = new List[11];
            for (int i = 6; i <= 10; i++) hole[i] = new ArrayList<>();

            for (int i = 0; i < N + 2; i++) {
                board[0][i] = 5;
                board[N + 1][i] = 5;
                board[i][0] = 5;
                board[i][N + 1] = 5;
            }

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    board[i][j] = v;
                    if (v >= 6) hole[v].add(new int[]{i, j});
                }
            }

            for (int v = 6; v <= 10; v++) {
                if (hole[v].size() == 2) {
                    int[] a = hole[v].get(0);
                    int[] b = hole[v].get(1);
                    linkX[a[0]][a[1]] = b[0];
                    linkY[a[0]][a[1]] = b[1];
                    linkX[b[0]][b[1]] = a[0];
                    linkY[b[0]][b[1]] = a[1];
                }
            }

            ans = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (board[i][j] != 0) continue;
                    for (int d = 0; d < 4; d++) {
                        ans = Math.max(ans, run(i, j, d));
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, ans);
        }
    }
}
