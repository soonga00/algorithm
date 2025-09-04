import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static String[] arr;
    static int[] left, right;
    
    static void inorder(StringBuilder sb, int n) {
    	if (n > N) return;
    	if (left[n] != 0) inorder(sb, left[n]);
    	sb.append(arr[n]);
    	if (right[n] != 0) inorder(sb, right[n]);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = 10;

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new String[N+1];
            left = new int[N+1];
            right = new int[N+1];
            for (int i = 0; i < N;i ++) {
            	StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
            	int idx = Integer.parseInt(st.nextToken());
            	arr[idx] = st.nextToken();
            	if (st.hasMoreElements()) {
            		left[idx] = Integer.parseInt(st.nextToken());
            	}
            	if (st.hasMoreElements()) {
            		right[idx] = Integer.parseInt(st.nextToken());
            	}
            }
            StringBuilder sb = new StringBuilder();
            inorder(sb, 1);
            System.out.println("#" + t + " "+sb);
        }
    }
}
