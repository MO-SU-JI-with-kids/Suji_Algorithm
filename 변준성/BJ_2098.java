import java.util.*;
import java.io.*;

public class BJ_2098 {
	static int N, res;
	static int[][] W;
	static int[][] dp;
	
	//top-down 풀이
	static int dfs(int mask, int cur) {
		if(mask == (1<<N)-1) {
			if(W[cur][0] == 0) return 1_000_000_000;
			return W[cur][0];
		}
		if(dp[mask][cur] != -1) return dp[mask][cur];
		
		int min = 1_000_000_000;
		
		for(int next = 1;next<N;next++) {
			if((mask & (1<<next)) != 0) continue;
			if(W[cur][next] == 0) continue;
			min = Math.min(min, dfs(mask | (1<<next), next) + W[cur][next]);
		}
		
		return dp[mask][cur]=min;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[1<<N][N];
		for(int i=0;i<(1<<N);i++) Arrays.fill(dp[i], -1);
		
		//bottom-up 풀이
//		for(int i=0;i<(1<<N);i++) Arrays.fill(dp[i], 1_000_000_000);
//		dp[1<<0][0] = 0;
//		
//		for(int mask = 1;mask<(1<<N);mask++) {
//			for(int cur=1;cur<N;cur++) {
//				if((mask & (1<<cur)) == 0) continue;
//				int prevMask = mask ^ (1<<cur);
//				
//				if(prevMask == 0) continue;
//				for(int prev = 0;prev<N;prev++) {
//					if((prevMask & (1<<prev)) == 0 || W[prev][cur] == 0) continue;
//					dp[mask][cur] = Math.min(dp[mask][cur], dp[prevMask][prev] + W[prev][cur]);
//				}
//			}
//		}
//				
//		int res = 1_000_000_000;
//		
//		for(int i=1;i<N;i++) {
//			if(W[i][0] == 0) continue;
//			res = Math.min(res, dp[(1<<N)-1][i] + W[i][0]);
//		}
//		
//		System.out.println(res);
		
		res = 1_000_000_000;
		
		System.out.println(dfs(1,0));
	}
}
