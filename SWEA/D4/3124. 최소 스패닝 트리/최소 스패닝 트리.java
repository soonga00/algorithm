import java.io.*;
import java.util.*;

public class Solution {
    static int T, V, E;
    static int[] parent, rank;

    static int find(int x){
        return parent[x]==x ? x : (parent[x]=find(parent[x]));
    }
    static boolean union(int a, int b){
        a = find(a); b = find(b);
        if (a==b) return false;
        if (rank[a] < rank[b]) { int t=a; a=b; b=t; }
        parent[b] = a;
        if (rank[a]==rank[b]) rank[a]++;
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int u, v;
        long w;
        Edge(int u,int v,long w){ this.u=u; this.v=v; this.w=w; }
        public int compareTo(Edge o){ return Long.compare(this.w, o.w); }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[E];
            for (int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(a, b, w);
            }
            Arrays.sort(edges);

            parent = new int[V+1];
            rank   = new int[V+1];
            for (int i = 1; i <= V; i++) parent[i] = i;

            long mst = 0;
            int picked = 0;
            for (Edge e : edges){
                if (union(e.u, e.v)){
                    mst += e.w;
                    if (++picked == V-1) break;
                }
            }
            out.append('#').append(tc).append(' ').append(mst).append('\n');
        }
        System.out.print(out);
    }
}
