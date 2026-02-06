package test;

import java.io.*;
import java.util.*;

public class BJ_7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		
		int[][] memory = new int[N][2];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		int max = 0;
		for(int i=0;i<N;i++) {
			memory[i][0] = Integer.parseInt(st1.nextToken());
			memory[i][1] = Integer.parseInt(st2.nextToken());
			max += memory[i][1];
		}
		
		int[] dp = new int[max+1];
		for(int i=0;i<N;i++) {
			
			for(int j=max;j>=memory[i][1];j--) {
				dp[j] = Math.max(dp[j], dp[j - memory[i][1]] + memory[i][0]);
			}
		}
		
		int ans = 0;
		for(int i=1;i<=max;i++) {
			if(dp[i]>=K) {
				ans = i;
				break;
			}
		}
		
		
		System.out.println(ans);
	}
}
