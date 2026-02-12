package test;

import java.util.*;
import java.io.*;

public class BJ_11066 {
	static int N;
	static int[] cost;
	static int[][] dp;
	
	static int solve(int L ,int R) {
		if(L==R) return 0;
		if(dp[L][R] != -1) return dp[L][R];
		
		int min = Integer.MAX_VALUE;
		
		for(int i = L;i<R;i++) {
			min = Math.min(min, summing(L, R) + solve(L, i) + solve(i+1,R));
		}
		
		return dp[L][R] = min;
	}
	
	static int summing(int l, int r) {
		return cost[r] - cost[l-1];
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			cost = new int[N+1];
			dp = new int[N+1][N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			for(int i=1;i<=N;i++) {
				cost[i] = cost[i-1] + Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i], -1);
			}
			
			System.out.println(solve(1,N));
			
			
			
		}
	}
}
