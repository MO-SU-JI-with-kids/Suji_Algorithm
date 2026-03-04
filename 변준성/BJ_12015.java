import java.util.*;
import java.io.*;

public class BJ_12015 {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] num = new int[N];
		
		for(int i=0;i<N;i++) num[i] = Integer.parseInt(st.nextToken());
		
		int res = 0;
		int[] tmp = new int[N];
		
		
		for(int n : num) {
			int low = 0;
			int high = res;
		
			while(low<high) {
				int mid = (low + high) / 2;
				
				
				if(tmp[mid] < n) {
					low = mid+1;
				}
				else {
					high = mid;
				}
			}
			
			tmp[low] = n;
			
			
			if(low == res) {
				res++;
			}
			
		}
		
		System.out.println(res);
	}
}
