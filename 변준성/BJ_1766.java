package test;

import java.io.*;
import java.util.*;

class BJ_1766 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indg = new int[N+1];
		List<Integer>[] adj = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			indg[e]++;
			adj[s].add(e);
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<>((a,b) -> a-b);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1;i<=N;i++) {
			if(indg[i] == 0) {
				q.add(i);

			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			for(int i:adj[now]) {
				indg[i]--;
				if(indg[i]==0) {
					q.add(i);
					
				}
			}
		}
		
		System.out.println(sb);	
		
		
		
	}
}