import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int[][] board;
    static final int EMPTY = 0, CORE = 1, LINE = 2;

    static List<int[]> cores = new ArrayList<>();
    static int bestConnect, bestLen;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static void dfs(int idx, int connected, int length) {
        int remain = cores.size() - idx;

        if (connected + remain < bestConnect) return;

        if (connected + remain == bestConnect && length >= bestLen) return;

        if (idx == cores.size()) {
            if (connected > bestConnect || (connected == bestConnect && length < bestLen)) {
                bestConnect = connected;
                bestLen = length;
            }
            return;
        }
        dfs(idx + 1, connected, length);

        int x = cores.get(idx)[0];
        int y = cores.get(idx)[1];


        for (int d = 0; d < 4; d++) {
            List<int[]> path = new ArrayList<>();
            int len = tryLay(x, y, d, path);
            if (len == -1) continue;

            for (int[] p : path) board[p[0]][p[1]] = LINE;

            dfs(idx + 1, connected + 1, length + len);

            for (int[] p : path) board[p[0]][p[1]] = EMPTY;
        }
    }

    static int tryLay(int x, int y, int d, List<int[]> path) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        while (0 <= nx && nx < N && 0 <= ny && ny < N) {
            if (board[nx][ny] != EMPTY) return -1;
            path.add(new int[]{nx, ny});
            nx += dx[d];
            ny += dy[d];
        }
        return path.size();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            board = new int[N][N];
            cores.clear();
            bestConnect = 0;
            bestLen = Integer.MAX_VALUE;

            int alreadyConnected = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    board[i][j] = v;
                    if (v == CORE) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                            alreadyConnected++;
                        } else {
                            cores.add(new int[]{i, j});
                        }
                    }
                }
            }

            dfs(0, alreadyConnected, 0);
            sb.append('#').append(tc).append(' ').append(bestLen).append('\n');
        }
        System.out.print(sb.toString());
    }
}
