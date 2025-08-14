import java.io.*;
import java.util.*;
public class Solution {
	static int T;
	static long getCnt(long n) {
		long cnt = 0;
		while (n != 2) {
			long sqrt =(long) Math.sqrt(n);
			if (sqrt * sqrt == n) {
				n=sqrt;
			}else {
				long next = (sqrt+1) * (sqrt+1);
				cnt += (next - n);
				n = sqrt+1;
			}
			cnt++;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		
		for (int t = 1; t <= T; t++) {
			long n = Long.parseLong(br.readLine().trim());
			System.out.printf("#%d %d\n", t, getCnt(n));
		}
	}

}
