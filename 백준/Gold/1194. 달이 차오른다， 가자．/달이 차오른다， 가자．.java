import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y, keys, dist;
        Node(int x, int y, int keys, int dist) {
            this.x = x; this.y = y; this.keys = keys; this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        int sx = -1, sy = -1;
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = line[j];
                if (c == '0') { sx = i; sy = j; c = '.'; }
                map[i][j] = c;
            }
        }

        boolean[][][] visited = new boolean[N][M][64];
        ArrayDeque<Node> q = new ArrayDeque<>();
        visited[sx][sy][0] = true;
        q.offer(new Node(sx, sy, 0, 0));

        int answer = -1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                int nkeys = cur.keys;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                char c = map[nx][ny];
                if (c == '#') continue;

                if (c == '1') {
                    answer = cur.dist + 1;
                    System.out.println(answer);
                    return;
                }

                if ('A' <= c && c <= 'F') {
                    int need = 1 << (c - 'A');
                    if ((nkeys & need) == 0) continue;
                }

                if ('a' <= c && c <= 'f') {
                    nkeys = nkeys | (1 << (c - 'a'));
                }

                if (!visited[nx][ny][nkeys]) {
                    visited[nx][ny][nkeys] = true;
                    q.offer(new Node(nx, ny, nkeys, cur.dist + 1));
                }
            }
        }

        System.out.println(-1);
    }
}
