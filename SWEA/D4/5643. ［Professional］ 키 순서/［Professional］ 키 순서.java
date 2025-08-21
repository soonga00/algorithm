import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M;
    static boolean[][] reach;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());
            reach = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                reach[from][to] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (reach[i][k]) {
                        for (int j = 1; j <= N; j++) {
                            if (reach[k][j]) {
                                reach[i][j] = true;
                            }
                        }
                    }
                }
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                int inCnt = 0;
                int outCnt = 0;
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (reach[i][j]) outCnt++;
                    if (reach[j][i]) inCnt++;
                }
                if (inCnt + outCnt + 1 == N) cnt++;
            }

            sb.append('#').append(t).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
