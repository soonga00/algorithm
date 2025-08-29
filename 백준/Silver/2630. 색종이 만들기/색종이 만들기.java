import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] g;
    static int white, blue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        g = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) g[i][j] = Integer.parseInt(st.nextToken());
        }

        divide(0, 0, N);

        StringBuilder sb = new StringBuilder();
        sb.append(white).append('\n').append(blue);
        System.out.println(sb);
    }

    static void divide(int x, int y, int n) {
        if (isUniform(x, y, n)) {
            if (g[x][y] == 0) white++;
            else blue++;
            return;
        }
        int m = n / 2;
        divide(x, y, m);
        divide(x, y + m, m);
        divide(x + m, y, m);
        divide(x + m, y + m, m);
    }

    static boolean isUniform(int x, int y, int n) {
        int color = g[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (g[i][j] != color) return false;
            }
        }
        return true;
    }
}
