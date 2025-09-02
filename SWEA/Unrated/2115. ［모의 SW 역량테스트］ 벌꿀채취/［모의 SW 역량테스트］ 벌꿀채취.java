import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, C;
    static int[][] g;

    static class Seg {
        int r, c, val;
        Seg(int r, int c, int val) { this.r = r; this.c = c; this.val = val; }
    }

    
    static int bestProfitForSegment(int r, int c) {
        int[] arr = new int[M];
        for (int k = 0; k < M; k++) arr[k] = g[r][c + k];

        int best = 0;
        for (int mask = 1; mask < (1 << M); mask++) {
            int sum = 0, sq = 0;
            for (int k = 0; k < M; k++) {
                if ((mask & (1 << k)) != 0) {
                    sum += arr[k];
                    if (sum > C) break;
                    sq += arr[k] * arr[k];
                }
            }
            if (sum <= C) best = Math.max(best, sq);
        }
        return best;
    }


    static boolean overlap(Seg a, Seg b) {
        if (a.r != b.r) return false;
        int aL = a.c, aR = a.c + M - 1;
        int bL = b.c, bR = b.c + M - 1;
        return !(aR < bL || bR < aL);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            g = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) g[i][j] = Integer.parseInt(st.nextToken());
            }

            List<Seg> segs = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j + M - 1 < N; j++) {
                    int val = bestProfitForSegment(i, j);
                    segs.add(new Seg(i, j, val));
                }
            }

            int ans = 0;
            for (int i = 0; i < segs.size(); i++) {
                for (int j = i + 1; j < segs.size(); j++) {
                    if (overlap(segs.get(i), segs.get(j))) continue;
                    ans = Math.max(ans, segs.get(i).val + segs.get(j).val);
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}
