package test;


import java.util.*;
import java.io.*;
	
public class BJ_11049 {
	
	static int N;
	static int[][] matrix, dp;

	static int solve(int L, int R) {
		if(L==R) return dp[L][R] = 0;
		if(L==R-1) return dp[L][R] = matrix[L][0] * matrix[L][1] * matrix[R][1];
		if(dp[L][R] != -1) return dp[L][R];
		
		int min = Integer.MAX_VALUE;
		
		for(int i=L;i<R;i++) {
			int cur = solve(L, i) + solve(i+1, R) + matrix[L][0] * matrix[i][1] * matrix[R][1];
			min = Math.min(min, cur);
		}
		
		return dp[L][R] = min;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		matrix = new int[N][2];
		dp = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(dp[i], -1);
		}
		
		
		
		System.out.println(solve(0, N-1));

	}
	
}
