import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, K;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static class Cell implements Comparable<Cell> {
        int x, y;
        int life;
        int birth;
        int active;
        int die;

        Cell(int x, int y, int life, int birth) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.birth = birth;
            this.active = birth + life;
            this.die = birth + 2 * life;
        }

        @Override
        public int compareTo(Cell o) {
            if (this.active != o.active) return this.active - o.active;
            return o.life - this.life;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("res/d9_5653_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int size = N + 2 * K;
            boolean[][] visited = new boolean[size][size];
            PriorityQueue<Cell> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v > 0) {
                        int x = i + K;
                        int y = j + K;
                        visited[x][y] = true;
                        pq.add(new Cell(x, y, v, 0));
                    }
                }
            }

            int time = 0;
            while (time <= K) {
                List<Cell> tmp = new ArrayList<>();

                while (!pq.isEmpty()) {
                    Cell c = pq.peek();
                    if (c.active > time) break;
                    c = pq.poll();

                    if (c.active == time) {
                        for (int d = 0; d < 4; d++) {
                            int nx = c.x + dx[d];
                            int ny = c.y + dy[d];
                            if (visited[nx][ny]) continue;
                            visited[nx][ny] = true;
                            tmp.add(new Cell(nx, ny, c.life, time+1));
                        }
                    }

                    if (c.die > time) {
                        tmp.add(c);
                    }
                }
                for (Cell nc : tmp) pq.add(nc);
                time++;
            }

            int cnt = 0;
            for (Cell c : pq) {
                if (c.die > K && c.birth <= K) cnt++;
            }
            sb.append('#').append(t).append(' ').append(cnt).append('\n');
        }

        System.out.print(sb);
    }
}
