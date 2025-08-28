import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int[][] g, dist;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static class Node implements Comparable<Node> {
        int x, y, cost;
        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                return cur.cost;
            }

            if (cur.cost > dist[cur.x][cur.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                int nc = cur.cost + g[nx][ny];
                if (nc < dist[nx][ny]) {
                    dist[nx][ny] = nc;
                    pq.offer(new Node(nx, ny, nc));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/d4_1249_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            g = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    g[i][j] = Integer.parseInt(line[j]);
                }
            }

            int answer = dijkstra();
            System.out.println("#" + t + " " + answer);
        }
    }
}
