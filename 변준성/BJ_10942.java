package test;

import java.io.*;
import java.util.*;

public class BJ_10942 {
	static int N;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) num[i] = Integer.parseInt(st.nextToken());
		
		boolean[][] dp = new boolean[N][N];
		
		for(int i=0;i<N;i++) dp[i][i] = true;
		for(int i=0;i<N-1;i++) if(num[i] == num[i+1]) dp[i][i+1] = true;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<i-1;j++) {
			
				if(num[j]==num[i] && dp[j+1][i-1]) dp[j][i] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			if(dp[s][e]) sb.append(1 + "\n");
			else sb.append(0+ "\n");
		}
		System.out.println(sb);
		
		
	}
}
