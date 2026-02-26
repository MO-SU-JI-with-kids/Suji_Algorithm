import java.io.*;
import java.util.*;

public class BJ_1562 {
	static int N;
	static int[][][] dp;
	
	static int dfs(int cur, int dep, int mask) {
		if(dep == N) {
			if(mask == (1<<10) - 1) {
				return 1;
			}
			else {
				return 0;
			}
		}
		long res = 0;

		if(dp[cur][dep][mask] != -1) return dp[cur][dep][mask];
		
		if(cur!=9) {
			res += dfs(cur+1, dep+1, mask | (1<<cur+1));
		}
		if(cur!=0) {
			res += dfs(cur-1, dep+1, mask | (1<<cur-1));
		}
		
		return dp[cur][dep][mask] = (int) res % 1_000_000_000;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[10][N+1][1<<10];
		
		long res = 0;
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<=N;j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		for(int i=1;i<=9;i++) {
			res = (res + dfs(i, 1, 1<<i)) % 1_000_000_000;
			
		}
		
		System.out.println(res);
	}
}
