package test;

import java.util.*;
import java.io.*;

public class BJ_1762 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] edgeU = new int[M];
        int[] edgeV = new int[M];
        int[] degree = new int[N + 1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edgeU[i] = Integer.parseInt(st.nextToken());
            edgeV[i] = Integer.parseInt(st.nextToken());
            degree[edgeU[i]]++;
            degree[edgeV[i]]++;
        }
        
        int[][] adj = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            adj[i] = new int[degree[i]];
        }
        
        int[] pointer = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int u = edgeU[i];
            int v = edgeV[i];
            adj[u][pointer[u]++] = v;
            adj[v][pointer[v]++] = u;
        }
		
        for (int i = 1; i <= N; i++) {
            Arrays.sort(adj[i]);
        }
		
		long res = 0;
		
		for (int u = 1; u <= N; u++) {
            for (int v : adj[u]) {
                if (u >= v) continue; 

                int[] targetArr = (adj[u].length < adj[v].length) ? adj[u] : adj[v];
                int[] searchArr = (targetArr == adj[u]) ? adj[v] : adj[u];
                
                for (int w : targetArr) {
                    if (w > v) {
                        if (Arrays.binarySearch(searchArr, w) >= 0) {
                            res++;
                        }
                    }
                }
            }
        }
		System.out.println(res);
	}
}
