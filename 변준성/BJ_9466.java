import java.util.*;
import java.io.*;

public class BJ_9466 {
	static int N, count;
	static int[] team;
	static boolean[] visited, finished;
	
	static void dfs (int cur) {
		int next = team[cur];
		visited[cur] = true;
		
		if(!visited[next]) {
			dfs(next);
		} else if(!finished[next]) {
			for(int tmp = next;tmp!=cur;tmp=team[tmp]) {
				count++;
			}
			count++;
		}
		finished[cur] = true;
		
	}
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <=T;tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			team = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1;i<=N;i++) {
				team[i] = Integer.parseInt(st.nextToken());
			}
			
			count = 0;
			for(int i=1;i<=N;i
					++) {
				if(visited[i]) continue;
				dfs(i);
			}
			
			System.out.println(N-count);
		}
		
	}
}