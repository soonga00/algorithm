import java.io.*;
import java.util.*;

public class Solution{
	static int T, N, answer;
	static int[] mountain;
	
	static int getSubComb(int i) {
		int leftLen = 0;
		for (int k = i-1; k >= 0 && mountain[k] < mountain[k+1]; k--) leftLen++;

		int rightLen = 0;
		for (int k = i+1; k < N && mountain[k] < mountain[k-1]; k++) rightLen++;

		return leftLen * rightLen;
		
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			mountain = new int[N];
			for (int i = 0; i < N; i++) mountain[i] = sc.nextInt();
			answer = 0;
			for (int i = 1; i <N-1; i++) {
				if (mountain[i] > mountain[i-1] && mountain[i] > mountain[i+1]) {
					answer += getSubComb(i);
				}
			}
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}