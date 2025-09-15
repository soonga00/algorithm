import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] adj;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int v) {
        visited[v] = true;
        dp[v][0] = 0;
        dp[v][1] = 1;

        for (int c : adj[v]) {
            if (!visited[c]) {
                dfs(c);

                dp[v][0] += dp[c][1];
                dp[v][1] += Math.min(dp[c][0], dp[c][1]);
            }
        }
    }
}