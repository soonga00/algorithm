import java.io.*;
import java.util.*;

public class Solution {
    static int T, D, W, K, answer;
    static int[][] g;
    static final int A = 0, B = 1, N = 2;
    static int[] dist;
    static boolean ok;
    static List<Integer> picked = new ArrayList<>();

    static int val(int i, int j) {
        return dist[i] == N ? g[i][j] : dist[i];
    }

    static boolean pass() {
        if (K == 1) return true;
        for (int j = 0; j < W; j++) {
            int prev = val(0, j);
            int run = 1;
            boolean colOK = (run >= K);
            for (int i = 1; i < D && !colOK; i++) {
                int cur = val(i, j);
                if (cur == prev) run++;
                else { prev = cur; run = 1; }
                if (run >= K) colOK = true;
            }
            if (!colOK) return false;
        }
        return true;
    }

    static void assignAB(int idx) {
        if (ok) return;
        if (idx == picked.size()) {
            if (pass()) ok = true;
            return;
        }
        int r = picked.get(idx);
        int backup = dist[r];
        dist[r] = A;
        assignAB(idx + 1);
        if (ok) { dist[r] = backup; return; }
        dist[r] = B;
        assignAB(idx + 1);
        dist[r] = backup;
    }

    static void comb(int start, int chosen, int target) {
        if (ok) return;
        if (chosen == target) {
            Arrays.fill(dist, N);
            assignAB(0);
            return;
        }
        for (int r = start; r < D; r++) {
            picked.add(r);
            comb(r + 1, chosen + 1, target);
            picked.remove(picked.size() - 1);
            if (ok) return;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            g = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist = new int[D];
            Arrays.fill(dist, N);

            if (pass()) {
                out.append("#").append(t).append(" ").append(0).append("\n");
                continue;
            }

            answer = K;
            for (int m = 1; m <= K; m++) {
                ok = false;
                picked.clear();
                comb(0, 0, m);
                if (ok) {
                    answer = m;
                    break;
                }
            }
            out.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(out.toString());
    }
}
