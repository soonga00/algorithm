import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] parent, depth;
    static List<Integer>[] children;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];
            depth = new int[N + 1];
            children = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) children[i] = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 2; i <= N; i++) {
                int p = Integer.parseInt(st.nextToken());
                parent[i] = p;
                children[p].add(i);
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(1);
            depth[1] = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int c : children[cur]) {
                    depth[c] = depth[cur] + 1;
                    q.add(c);
                }
            }

            List<Integer> order = new ArrayList<>();
            ArrayDeque<Integer> q2 = new ArrayDeque<>();
            q2.add(1);
            while (!q2.isEmpty()) {
                int cur = q2.poll();
                order.add(cur);
                for (int c : children[cur]) q2.add(c);
            }

            long answer = 0;
            for (int i = 0; i < order.size() - 1; i++) {
                int u = order.get(i);
                int v = order.get(i + 1);
                int lca = findLCA(u, v);
                answer += depth[u] + depth[v] - 2 * depth[lca];
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static int findLCA(int a, int b) {
        while (depth[a] > depth[b]) a = parent[a];
        while (depth[b] > depth[a]) b = parent[b];
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
