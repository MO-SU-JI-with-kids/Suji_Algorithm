package test;

import java.util.*;
import java.io.*;
public class BJ_2342 {
	
	
	static int moving(int a, int b) {
		if(a==0) return 2;
		if(a==b) return 1;
		if(Math.abs(a-b)==2) return 4;
		return 3;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<Integer> move = new ArrayList<>();
		
		while(true) {
			int k = Integer.parseInt(st.nextToken());
			if(k==0) break;
			move.add(k);
		}
		
		int[][][] dp = new int[move.size()+1][5][5];
		for(int i=0;i<=move.size();i++) {
			for(int j=0;j<5;j++) {
				Arrays.fill(dp[i][j], 400001);
			}
		}
		dp[0][0][0] = 0;
		
		for(int i=1;i<=move.size();i++) {
			int next = move.get(i-1);
			for(int j=0;j<5;j++) {
				for(int k=0;k<5;k++) {
					if(dp[i-1][j][k] == 400001) continue;
					
					dp[i][next][k] = Math.min(dp[i][next][k], dp[i-1][j][k] + moving(j, next));
					dp[i][j][next] = Math.min(dp[i][j][next], dp[i-1][j][k] + moving(k, next));
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				ans = Math.min(ans, dp[move.size()][i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
