import java.util.*;
import java.io.*;

public class BJ_10775 {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine().trim());
		
		int P = Integer.parseInt(br.readLine().trim());
		
		int[] gi = new int[P];
		
		for(int i=0;i<P;i++) {
			gi[i] = Integer.parseInt(br.readLine().trim());
			
		}
		
		parent = new int[G+1];
		for(int i=1;i<=G;i++) parent[i] = i;
		
		int cnt = 0;
		for(int i:gi) {
			int gate = find(i);
			if(gate==0) break;
			cnt++;
			union(gate, gate-1);
		}
		
		System.out.println(cnt);
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		
		return parent[n] = find(parent[n]);
	}
	
	static void union(int i, int j) {
		if(find(i) != find(j)) parent[find(i)] = find(j);
	}
}
