import java.util.*;
// [모수지] week2 BJ_17069

public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	static int n;
	static long cnt;
	static int[][] info;
	static long[][][] dp;
	
    public static void main(String[] args)  {
    	n = sc.nextInt();
    	info = new int[n][n];
    	dp = new long[n][n][3]; // [][][k] : k는 방향 0이면 왼, 1이면 아래, 2이면 대각선 방향으로부터 받은 값.
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) info[i][j] = sc.nextInt();
    	}

    	dp[0][1][0] = 1; // 시작점은 왼쪽으로 향해 있다.
    	cal();
    	System.out.println((dp[n-1][n-1][0] + dp[n-1][n-1][1] +  dp[n-1][n-1][2]));

    }
    
    public static void cal() {
    	
    	for(int i=0; i<n; i++) {
    		for(int j=1; j<n; j++) {
    			
    			if(info[i][j] == 1) continue;
    			
    			// 위 검사 : [i-1][j]가 유요한 인덱스이면서 dp[i-1][j][1] 값을 더하기
    			if((i-1) <n && (i-1)>=0) dp[i][j][1] += (dp[i-1][j][1] + dp[i-1][j][2]);
    			
    			// 왼쪽 검사 : [i][j-1] 이 유효한 인덱스이면서 dp[i][j-1][0] 값을 더하기
    			if((j-1) <n && (j-1)>=0) dp[i][j][0] +=(dp[i][j-1][0] + dp[i][j-1][2]);
  
    			// 대각선 검사 : [i-1][j-1]이 유효한 인덱스이면서 dp[i-1][j-1][2] 값을 더하기 
    			if((i-1) <n && (i-1)>=0 && (j-1)<n  && (j-1)>=0) {
    				// 대각선 방향 이동 위해서 검사
        			if(info[i-1][j] != 1 && info[i][j-1] != 1) {
        				dp[i][j][2] += ( dp[i-1][j-1][0]+ dp[i-1][j-1][2] + dp[i-1][j-1][1]);
        			}
    			}
    		}
    	}
    }
}
