import java.util.*;
import java.io.*;

public class BJ_1509 {
	static int N;
	static char[] c;
	static int[][] isPal;
	
	static int solve(int l, int r) {
		if(l == r) return isPal[l][r] = 1;
		if(l > r) return 1;
		if(isPal[l][r] != -1) return isPal[l][r];
		
		if (c[l] != c[r]) {
	        return isPal[l][r] = 0;
	    }
		
		return isPal[l][r] = solve(l + 1, r - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		c = br.readLine().trim().toCharArray();
		N = c.length;
		
		isPal = new int[N][N];
		for(int i=0;i<N;i++) Arrays.fill(isPal[i], -1);
		
		for(int i=0;i<N;i++) {
			for(int j=i;j<N;j++) {
				solve(i, j);
			}
		}
		
		int[] dp = new int[N];
		
		for(int i=0;i<N;i++) {
			dp[i] = i+1;
			for(int j=0;j<=i;j++) {
				if(isPal[j][i] == 1) {
					if(j==0) {
						dp[i] = 1;
					}
					else {
						dp[i] = Math.min(dp[i],dp[j-1] + 1);
					}
				}
			}
			
		}
		
		System.out.println(dp[N-1]);
	}
}
