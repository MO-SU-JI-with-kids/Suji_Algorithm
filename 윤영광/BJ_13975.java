import java.util.*;
import java.io.*;

public class BJ_13975 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {
			solve();
		}
	}

	private static void solve() {

		k = sc.nextInt();

		PriorityQueue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < k; i++) {
			pq.add(sc.nextLong());
		}

		long ret =0;
		while (pq.size() > 1) {
			long a = pq.poll();
			long b = pq.poll();
			
			ret += a+b;
			pq.add(a+b);
			
		}
		
		System.out.println(ret);
	}

}
