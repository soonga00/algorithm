import java.io.*;
import java.util.*;

public class Solution {
	 static int V, E, A, B;
	    static int[] parent;
	    static List<Integer>[] children;

	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuilder sb = new StringBuilder();
	        int T = Integer.parseInt(br.readLine());

	        for (int t = 1; t <= T; t++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            V = Integer.parseInt(st.nextToken());
	            E = Integer.parseInt(st.nextToken());
	            A = Integer.parseInt(st.nextToken());
	            B = Integer.parseInt(st.nextToken());

	            parent = new int[V + 1];
	            children = new ArrayList[V + 1];
	            for (int i = 1; i <= V; i++) children[i] = new ArrayList<>();

	            st = new StringTokenizer(br.readLine());
	            for (int i = 0; i < E; i++) {
	                int p = Integer.parseInt(st.nextToken());
	                int c = Integer.parseInt(st.nextToken());
	                parent[c] = p;
	                children[p].add(c);
	            }

	            int lca = findLCA(A, B);
	            int size = countSubtree(lca);

	            sb.append("#").append(t).append(" ").append(lca).append(" ").append(size).append("\n");
	        }
	        System.out.print(sb);
	    }


	    static int findLCA(int a, int b) {
	        Set<Integer> ancestors = new HashSet<>();
	        while (a != 0) {
	            ancestors.add(a);
	            a = parent[a];
	        }
	        while (b != 0) {
	            if (ancestors.contains(b)) return b;
	            b = parent[b];
	        }
	        return 1;
	    }

	    static int countSubtree(int root) {
	        int cnt = 1;
	        for (int child : children[root]) {
	            cnt += countSubtree(child);
	        }
	        return cnt;
	    }
}
