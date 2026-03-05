import java.util.*;
import java.io.*;

public class BJ_16566 {
	static int N,M,K;
	static int[] card, op, parent;
	
	static int find(int i) {
		if(parent[i] == i) return i;
		return parent[i] = find(parent[i]);
	}
	
	static void union(int i, int j) {
		if(find(i) != find(j)) parent[find(i)] = find(j); 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		op = new int[K];
		card = new int[M];
		parent = new int[M+1];
		parent[M] = M;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			card[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card);
		
		StringBuilder sb = new StringBuilder();
		
		for(int x : op) {
			int low = 0;
			int high = M;
			
			while(low < high) {
				int mid = (low+high)/2;
				
				if(card[mid] <= x) {
					low = mid+1;
					
				}
				else {
					high = mid;
				}
			}
			
			int res = find(low);
			sb.append(card[res]).append("\n");
			union(res , res+1);
		}
		
		System.out.println(sb);
	}
}
