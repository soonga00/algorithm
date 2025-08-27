import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, W, H, maxBreakCnt, totalBricks;
    static int[][] g;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y, w;
        Node(int x, int y, int w){ this.x=x; this.y=y; this.w=w; }
    }

    static void down(int[][] map) {
        for (int j = 0; j < W; j++) {
            int r = H - 1;
            for (int i = H - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    if (i != r) { map[r][j] = map[i][j]; map[i][j] = 0; }
                    r--;
                }
            }
        }
    }

    static int shoot(int[][] map, int j) {
        int i = 0;
        while (i < H && map[i][j] == 0) i++;
        if (i == H) return 0;

        int destroyed = 0;
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(i, j, map[i][j]));
        map[i][j] = 0; destroyed++;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x, y = cur.y, w = cur.w;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x, ny = y;
                for (int step = 1; step < w; step++) {
                    nx += dx[dir]; ny += dy[dir];
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
                    if (map[nx][ny] == 0) continue;
                    q.offer(new Node(nx, ny, map[nx][ny]));
                    map[nx][ny] = 0; destroyed++;
                }
            }
        }
        return destroyed;
    }

    static int countRemain(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] != 0) cnt++;
        return cnt;
    }

    static void dfs(int depth, int broken, int[][] map) {
        int remain = totalBricks - broken;
        if (remain == 0) {
            maxBreakCnt = totalBricks;
            return;
        }
        if (broken + remain <= maxBreakCnt) return;

        if (depth == N) {
            if (broken > maxBreakCnt) maxBreakCnt = broken;
            return;
        }

        for (int col = 0; col < W; col++) {
            int r = 0;
            while (r < H && map[r][col] == 0) r++;
            if (r == H) continue;

            int topVal = map[r][col];

            int[][] next = new int[H][W];
            for (int i = 0; i < H; i++) System.arraycopy(map[i], 0, next[i], 0, W);

            int got = shoot(next, col);
            if (got > 0) down(next);

            dfs(depth + 1, broken + got, next);

            if (maxBreakCnt == totalBricks) return;
        }

        if (broken > maxBreakCnt) maxBreakCnt = broken;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/d9_5656_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            g = new int[H][W];
            totalBricks = 0; maxBreakCnt = 0;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                    if (g[i][j] != 0) totalBricks++;
                }
            }

            int[][] start = new int[H][W];
            for (int i = 0; i < H; i++) System.arraycopy(g[i], 0, start[i], 0, W);
            dfs(0, 0, start);

            sb.append('#').append(t).append(' ').append(totalBricks - maxBreakCnt).append('\n');
        }
        System.out.print(sb);
    }
}
