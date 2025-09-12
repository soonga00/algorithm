import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static long answer;
    static int[][] worms;
    static long sumX, sumY;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            worms = new int[N][2];
            sumX = sumY = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                worms[i][0] = Integer.parseInt(st.nextToken());
                worms[i][1] = Integer.parseInt(st.nextToken());
                sumX += worms[i][0];
                sumY += worms[i][1];
            }

            answer = Long.MAX_VALUE;
            dfs(0, 0, 0, 0);
            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int cnt, long sx, long sy) {
        if (cnt == N / 2) {
            long vx = 2 * sx - sumX;
            long vy = 2 * sy - sumY;
            answer = Math.min(answer, vx * vx + vy * vy);
            return;
        }
        if (idx >= N) return;

        dfs(idx + 1, cnt + 1, sx + worms[idx][0], sy + worms[idx][1]);
        dfs(idx + 1, cnt, sx, sy);
    }
}
