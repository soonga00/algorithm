import java.io.*;
import java.util.*;

public class Main {
    static int N, total, answer;
    static int[] pNum;
    static List<Integer>[] g;
    static boolean[] v;
    static boolean isConnected(boolean type, int cnt) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        int c = 0;
        for (int i = 1; i <= N; i++) {
            if (v[i] == type) {
                visited[i] = true;
                q.offer(i);
                c++;
                break;
            }
        }
        while(!q.isEmpty()) {
            int n = q.poll();
            for (int next: g[n]) {
                if (v[next] == type && !visited[next]) {
                    c++;
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return c == cnt;
    }
    static void subSet(int idx, int sum, int cnt) {
        if (idx == N+1) {
            if (cnt==0 || cnt==N) return;
            if (isConnected(true, cnt) && isConnected(false, N-cnt))  answer = Math.min(answer, Math.abs(total - (2*sum)));
            return;
        }
        v[idx] = true;
        subSet(idx+1, sum + pNum[idx], cnt+1);
        v[idx] = false;
        subSet(idx+1, sum, cnt);

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        pNum = new int[N+1];
        total = 0;
        g = new List[N+1]; for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());
            total += v;
            pNum[i] = v;
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            for (int j = 0; j < c; j++) g[i].add(Integer.parseInt(st.nextToken()));
        }
        v = new boolean[N+1];
        answer = Integer.MAX_VALUE;
        subSet(1, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
