import java.io.*;
import java.util.*;

class BC {
    int x, y, c, p;
    BC(int x, int y, int c, int p) {
        this.x = x; this.y = y; this.c = c; this.p = p;
    }
}

public class Solution{
    static final int N = 0, U = 1, R = 2, D = 3, L = 4;
    static int T, M, A;
    static int[] mA, mB;
    static BC[] bc;

    static final int[] dx = {0, 0, 1, 0, -1};
    static final int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            mA = new int[M + 1];
            mB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) mA[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) mB[i] = Integer.parseInt(st.nextToken());

            bc = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bc[i] = new BC(x, y, c, p);
            }

            int xA = 1, yA = 1;
            int xB = 10, yB = 10;
            int total = 0;

            for (int t = 0; t <= M; t++) {
                xA += dx[mA[t]];
                yA += dy[mA[t]];
                xB += dx[mB[t]];
                yB += dy[mB[t]];

                total += bestCharge(xA, yA, xB, yB);
            }

            out.append('#').append(tc).append(' ').append(total).append('\n');
        }
        System.out.print(out);
    }


    static int bestCharge(int xA, int yA, int xB, int yB) {
        List<Integer> LA = new ArrayList<>();
        List<Integer> LB = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            if (manhattan(xA, yA, bc[i].x, bc[i].y) <= bc[i].c) LA.add(i);
            if (manhattan(xB, yB, bc[i].x, bc[i].y) <= bc[i].c) LB.add(i);
        }

        int best = 0;

        if (LA.isEmpty() && LB.isEmpty()) return 0;
        if (LA.isEmpty()) {
            for (int j : LB) best = Math.max(best, bc[j].p);
            return best;
        }
        if (LB.isEmpty()) {
            for (int i : LA) best = Math.max(best, bc[i].p);
            return best;
        }

        for (int i : LA) {
            for (int j : LB) {
                if (i == j) best = Math.max(best, bc[i].p);
                else        best = Math.max(best, bc[i].p + bc[j].p);
            }
        }
        return best;
    }

    static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
