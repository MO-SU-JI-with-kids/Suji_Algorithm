import java.util.*;
import java.io.*;

public class BJ_2263 {
	
	static StringBuilder sb;
	static int N;
	static int[] inOrd, postOrd, inOrdIdx;
	
	static void solve(int is, int ie, int ps, int pe) {
		if(is>ie || ps>pe) return;
		
		sb.append(postOrd[pe] + " ");
		int rootIdx = inOrdIdx[postOrd[pe]];
		
		
		solve(is, rootIdx-1, ps, ps+rootIdx-is-1);
		solve(rootIdx+1, ie, ps+rootIdx-is, pe-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		inOrd = new int[N];
		postOrd = new int[N];
		
		for(int i=0;i<N;i++) {
			inOrd[i] = Integer.parseInt(st1.nextToken());
			postOrd[i] = Integer.parseInt(st2.nextToken());
		}
		
		inOrdIdx = new int[N+1];
		
		for(int i=0;i<N;i++) {
			inOrdIdx[inOrd[i]] = i;
		}
		
		sb = new StringBuilder();
		
		solve(0, N-1, 0, N-1);
		
		System.out.println(sb);
	}
}