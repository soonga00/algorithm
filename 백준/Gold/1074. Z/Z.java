import java.io.*;
import java.util.*;

public class Main {
    static int N, R, C;

    static int solve(int n, int r, int c) {
        if (n == 1) return 0;
        int half = n / 2;
        if (r < half && c < half) {
            return solve(half, r, c);
        } else if (r < half && c >= half) {
            return half * half + solve(half, r, c - half);
        } else if (r >= half && c < half) {
            return 2 * half * half + solve(half, r - half, c);
        } else {
            return 3 * half * half + solve(half, r - half, c - half);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(solve((int)Math.pow(2, N), R, C));
    }
}
