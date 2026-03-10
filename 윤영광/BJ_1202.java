import java.util.*;
import java.io.*;

class pair {

	int m, v;

	pair(int m, int v) {
		this.m = m;
		this.v = v;
	}

}

public class BJ_1202 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		n = sc.nextInt();
		k = sc.nextInt();
		int[] C = new int[k];

		PriorityQueue<Integer> Result = new PriorityQueue<>(Collections.reverseOrder());
		
		pair[] jew = new pair[n];
		
		
		for (int i = 0; i < n; i++) {
			jew[i] = new pair(sc.nextInt(), sc.nextInt());
		}

		for (int i = 0; i < k; i++) {
			C[i] = sc.nextInt();
		}

		Arrays.sort(C);
		Arrays.sort(jew, (o1, o2) -> {
			return o1.m - o2.m;
		});
		
		long ret = 0;

		int idx = 0;

		for (int i = 0; i < k; i++) {
			while(idx < n && jew[idx].m <= C[i]) {
				Result.add(jew[idx].v);
				idx++;
			}
			
			if (!Result.isEmpty()) {
		        ret += Result.poll();
		    }
		}

		System.out.println(ret);
	}

}
