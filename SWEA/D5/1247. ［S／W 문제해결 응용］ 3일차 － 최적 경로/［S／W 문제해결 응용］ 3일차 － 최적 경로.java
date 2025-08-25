import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, best;
    static int[][] p;
    static int[][] d;
    static boolean[] used;

    static void dfs(int cur, int cnt, int dist){
        if (dist >= best) return;
        if (cnt == N){
            best = Math.min(best, dist + d[cur][1]);
            return;
        }
        for (int i = 2; i < N+2; i++){
            if (used[i]) continue;
            used[i] = true;
            dfs(i, cnt+1, dist + d[cur][i]);
            used[i] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++){
            N = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            p = new int[N+2][2];
            for (int i = 0; i < N+2; i++){
                p[i][0] = Integer.parseInt(st.nextToken());
                p[i][1] = Integer.parseInt(st.nextToken());
            }
            d = new int[N+2][N+2];
            for (int i = 0; i < N+2; i++){
                for (int j = 0; j < N+2; j++){
                    d[i][j] = Math.abs(p[i][0]-p[j][0]) + Math.abs(p[i][1]-p[j][1]);
                }
            }
            best = Integer.MAX_VALUE;
            used = new boolean[N+2];
            dfs(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(best).append('\n');
        }
        System.out.print(sb);
    }
}
