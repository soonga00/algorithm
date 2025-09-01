import java.io.*;
import java.util.*;

public class Solution {
    static class Node {
        int v; long w;
        Node(int v, long w){ this.v = v; this.w = w; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());


            ArrayList<Node>[] g = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) g[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                g[a].add(new Node(b, w));
                g[b].add(new Node(a, w));
            }

            boolean[] visited = new boolean[V+1];
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));

            visited[1] = true;
            for (Node nxt : g[1]) pq.offer(nxt);

            long mst = 0;
            int picked = 1;

            while (!pq.isEmpty() && picked < V) {
                Node cur = pq.poll();
                if (visited[cur.v]) continue;
                visited[cur.v] = true;
                mst += cur.w;
                picked++;

                for (Node nxt : g[cur.v]) {
                    if (!visited[nxt.v]) pq.offer(nxt);
                }
            }

            out.append('#').append(tc).append(' ').append(mst).append('\n');
        }
        System.out.print(out);
    }
}
