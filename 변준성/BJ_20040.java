// 백준, 코드트리

import java.io.*;
import java.util.*;

public class BJ_20040 {
	
	static int N, M;
	static int[] parent;
	
	static int find(int k) {
		if(parent[k] == k) return k;
		return parent[k] = find(parent[k]);
	}
	
	static void union(int k, int h) {
		if(find(k) != find(h)) {
			parent[find(k)] = find(h);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		for(int i=0;i<N;i++) parent[i] = i;
		
		int res = 0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(find(s) == find(e)) {
				System.out.println(i+1);
				return;
			}
			union(s, e);
		}
		System.out.println(0);
	}
}

