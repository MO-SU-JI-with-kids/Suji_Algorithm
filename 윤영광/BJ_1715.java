import java.util.*;
import java.io.*;

public class BJ_1715 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, t;

	public static void main(String[] args) {

		n = sc.nextInt();

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			pq.add(sc.nextInt());
		}

		int ret = 0;

	
		while (pq.size() >= 2) {

			int a = pq.poll();
			int b = pq.poll();

			ret += a + b;

			pq.add(a + b);

		}
		
		System.out.println(ret);
	}

}
