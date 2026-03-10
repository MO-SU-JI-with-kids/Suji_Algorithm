import java.util.*;
import java.io.*;

class pair {
	long s, t;

	pair(long s, long t) {
		this.s = s;
		this.t = t;
	}
}

public class BJ_19598 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		n = sc.nextInt();

		pair[] list = new pair[n];

		for (int i = 0; i < n; i++) {
			list[i] = new pair(sc.nextLong(), sc.nextLong());
		}

		Arrays.sort(list, (a, b) -> {

			if (a.s == b.s) {
				return (int) (a.t - b.t);
			}

			return (int) (a.s - b.s);
		});

		long end = list[0].t;

		PriorityQueue<Long> pq = new PriorityQueue<>();
		pq.add(end);

		for (int i = 1; i < n; i++) {
			pair now = list[i];

			if (pq.peek() <= now.s) {
				pq.poll();
			}

			pq.add(now.t);
		}

		System.out.println(pq.size());

	}

}
BJ_11724.java
