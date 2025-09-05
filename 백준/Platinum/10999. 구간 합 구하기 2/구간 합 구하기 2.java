import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] arr, tree, lazy;

    static long build(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    static void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;
        int mid = (start + end) / 2;

        tree[node * 2] += (long) (mid - start + 1) * lazy[node];
        lazy[node * 2] += lazy[node];


        tree[node * 2 + 1] += (long) (end - mid) * lazy[node];
        lazy[node * 2 + 1] += lazy[node];

        lazy[node] = 0;
    }

    static void updateRange(int node, int start, int end, int l, int r, long val) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            tree[node] += (long) (end - start + 1) * val;
            lazy[node] += val;
            return;
        }
        pushDown(node, start, end);
        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, l, r, val);
        updateRange(node * 2 + 1, mid + 1, end, l, r, val);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long queryRange(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node]; 
        pushDown(node, start, end);
        int mid = (start + end) / 2;
        return queryRange(node * 2, start, mid, l, r)
             + queryRange(node * 2 + 1, mid + 1, end, l, r);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[4 * N];
        lazy = new long[4 * N];
        build(1, 1, N);

        StringBuilder sb = new StringBuilder();
        int Q = M + K;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                if (b > c) { int tmp = b; b = c; c = tmp; }
                updateRange(1, 1, N, b, c, d);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (b > c) { int tmp = b; b = c; c = tmp; }
                long ans = queryRange(1, 1, N, b, c);
                sb.append(ans).append('\n');
            }
        }
        System.out.print(sb.toString());
    }
}
