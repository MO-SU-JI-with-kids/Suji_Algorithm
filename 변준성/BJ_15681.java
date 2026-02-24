import java.util.*;
import java.io.*;

public class BJ_15681 {
	static int N, R, Q;
	static int[] count;
	static boolean[] visited;
	static int[][] adj;
	
	static int dfs(int cur) {
		visited[cur] = true;
		int cnt = 1;
		
		for(int i : adj[cur]) {
			if(visited[i]) continue;
			cnt += dfs(i);
		}
		
		return count[cur] = cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int[][] tmp = new int[N][2];
		int[] cnt = new int[N+1];
		
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			tmp[i][0] = Integer.parseInt(st.nextToken());
			tmp[i][1] = Integer.parseInt(st.nextToken());
			
			cnt[tmp[i][0]]++;
			cnt[tmp[i][1]]++;
		}
		
		count = new int[N+1];
		adj = new int[N+1][];
		
		for(int i=1;i<=N;i++) adj[i] = new int[cnt[i]];
		
		int[] pointer = new int[N+1];
		
		for(int i=0;i<N-1;i++) {
			int s = tmp[i][0];
			int e = tmp[i][1];
			
			adj[s][pointer[s]++] = e;
			adj[e][pointer[e]++] = s;
		}
		
		visited = new boolean[N+1];
		
		dfs(R);
		
		for(int i=0;i<Q;i++) {
			int t = Integer.parseInt(br.readLine());
			
			System.out.println(count[t]);
		}
	}
}