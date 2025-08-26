import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static long[] X, Y;
    static double E;

    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("res/re_sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            X = new long[N]; Y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) X[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) Y[i] = Long.parseLong(st.nextToken());

            E = Double.parseDouble(br.readLine().trim());

            boolean[] used = new boolean[N];
            double[] dist = new double[N];
            Arrays.fill(dist, Double.POSITIVE_INFINITY);
            dist[0] = 0.0;

            double total = 0.0;

            for (int i = 0; i < N; i++) {
                int u = -1;
                double best = Double.POSITIVE_INFINITY;
                for (int v = 0; v < N; v++) {
                    if (!used[v] && dist[v] < best) {
                        best = dist[v];
                        u = v;
                    }
                }
                used[u] = true;
                total += best;
                for (int v = 0; v < N; v++) {
                    if (used[v]) continue;
                    long dx = X[u] - X[v];
                    long dy = Y[u] - Y[v];
                    double cost = E * (dx*dx + dy*dy);
                    if (cost < dist[v]) dist[v] = cost;
                }
            }

            out.append('#').append(tc).append(' ').append(Math.round(total)).append('\n');
        }
        System.out.print(out);
    }
}
