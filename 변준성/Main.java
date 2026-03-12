import java.util.*;
import java.io.*;

class Main {
	
	static int N, M;
	static int[] elements, maxTree, minTree;
	
	static void init(int node, int start, int end) {
		if(start == end) {
			maxTree[node] = elements[start];
			minTree[node] = elements[start];
			return;
		}
		
		int mid = (start + end) / 2;
		init(node*2, start, mid);
		init(node*2+1, mid+1, end);
		maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2+1]);
		minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
	}
	
	static int queryMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;
		
		if(left <= start && right >= end) return maxTree[node];
		
		int mid = (start + end) / 2;
		return Math.max(queryMax(node*2, start, mid, left, right), queryMax(node*2+1, mid+1, end, left, right));
	}
	
	static int queryMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1_000_000_001;
		
		if(left <= start && right >= end) return minTree[node];
		
		int mid = (start + end) / 2;
		return Math.min(queryMin(node*2, start, mid, left, right), queryMin(node*2+1, mid+1, end, left, right));
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		elements = new int[N];
		maxTree = new int[N*4];
		minTree = new int[N*4];
		
		for(int i=0;i<N;i++) {
			elements[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 0, N-1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			
			sb.append(queryMin(1,0,N-1,l,r)).append(" ").append(queryMax(1,0,N-1,l,r)).append("\n");
		}
		
		System.out.println(sb);
	}
}