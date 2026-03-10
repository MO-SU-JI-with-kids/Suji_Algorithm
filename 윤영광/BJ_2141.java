import java.util.*;
import java.io.*;

class pair implements Comparable<pair> {
	long x, a;

	pair(long x, long a) {
		this.x = x;
		this.a = a;
	}

	@Override
	public int compareTo(pair o) {

		return Long.compare(this.x, o.x);
	}

}

public class BJ_2141 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		n = sc.nextInt();
		pair[] village = new pair[n];

		for (int i = 0; i < n; i++) {
			village[i] = new pair(sc.nextLong(), sc.nextLong());
		}

		Arrays.sort(village);

		long sumPeople = 0;

		for (int i = 0; i < n; i++) {
			sumPeople += village[i].a;
		}

		long tempSum = 0;
		for (int idx = 0; idx < n; idx++) {
			tempSum += village[idx].a;

			if (tempSum >= (sumPeople + 1) / 2) {
				System.out.println(village[idx].x);
				return;
			}
		}
	}

}
