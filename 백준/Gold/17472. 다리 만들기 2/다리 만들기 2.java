import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] g;
    static boolean[][] v;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static class Edge implements Comparable<Edge>{
        int u, v, w;
        Edge(int u,int v,int w){ this.u=u; this.v=v; this.w=w; }
        public int compareTo(Edge o){ return Integer.compare(this.w, o.w); }
    }

    static void makeIsland(int i, int j, int num) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        g[i][j] = num;
        q.offer(new int[] {i, j});
        v[i][j] = true;
        while (!q.isEmpty()) {
            int[] n = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = n[0] + dx[k];
                int ny = n[1] + dy[k];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (v[nx][ny] || g[nx][ny] == 0) continue;
                q.offer(new int[] {nx, ny});
                g[nx][ny] = num;
                v[nx][ny] = true;
            }
        }
    }


    static int[] parent, rank;
    static int find(int x){ return parent[x]==x ? x : (parent[x]=find(parent[x])); }
    static boolean union(int a, int b){
        a = find(a); b = find(b);
        if (a==b) return false;
        if (rank[a] < rank[b]) { int t=a; a=b; b=t; }
        parent[b] = a;
        if (rank[a]==rank[b]) rank[a]++;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N][M];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && g[i][j] != 0) {
                    makeIsland(i, j, num++);
                }
            }
        }
        int islands = num - 1;
        if (islands <= 1) {
            System.out.println(0);
            return;
        }

        int[][] bridge = new int[num][num];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (g[i][j] == 0) continue;
                int u = g[i][j];

                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x < 0 || x >= N || y < 0 || y >= M) continue;
                    if (g[x][y] != 0) continue;

                    int w = 0;
                    while (true) {
                        if (x < 0 || x >= N || y < 0 || y >= M) {
                            w = 0;
                            break;
                        }
                        if (g[x][y] == u) {
                            w = 0;
                            break;
                        }
                        if (g[x][y] != 0 && g[x][y] != u) {
                            if (w >= 2) {
                                int v = g[x][y];
                                if (bridge[u][v] == 0 || w < bridge[u][v]) {
                                    bridge[u][v] = bridge[v][u] = w;
                                }
                            }
                            break;
                        }
                        x += dx[k];
                        y += dy[k];
                        w++;
                    }
                }
            }
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int a = 1; a <= islands; a++) {
            for (int b2 = a+1; b2 <= islands; b2++) {
                if (bridge[a][b2] != 0) {
                    edges.add(new Edge(a, b2, bridge[a][b2]));
                }
            }
        }

        if (edges.isEmpty()) {
            System.out.println(-1);
            return;
        }

        Collections.sort(edges);

        parent = new int[islands+1];
        rank   = new int[islands+1];
        for (int i = 1; i <= islands; i++) parent[i] = i;

        int used = 0;
        int total = 0;
        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                total += e.w;
                if (++used == islands - 1) break;
            }
        }

        if (used != islands - 1) System.out.println(-1);
        else System.out.println(total);
    }
}
