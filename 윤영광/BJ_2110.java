import java.util.*;
import java.awt.desktop.AboutEvent;
import java.io.*;

public class BJ_2110 {

	static Scanner sc = new Scanner((System.in));
	static int n, c, ret;
	static int[] home;

	public static void main(String[] args) throws IOException {

		n = sc.nextInt();
		c = sc.nextInt();

		int left = 0, right = Integer.MAX_VALUE;

		home = new int[n];
		for (int i = 0; i < n; i++) {
			home[i] = sc.nextInt();
		}
		Arrays.sort(home);

		int ret = 0;
		while (left <= right) {

			// 두 집사이의 거리
			int mid = (left + right) / 2;

			// 가능하면 거리를 더 늘려야함
			if (check(mid)) {
				left = mid + 1;
				ret = Math.max(ret, mid);
			} else {
				right = mid - 1;
			}

		}

		System.out.println(ret);
	}

	private static boolean check(int mid) {

		int now = home[0];
		int cnt = 1;
		for (int i = 1; i < n; i++) {

			int len = home[i] - now;

			if (len >= mid) {
				cnt++;
				now = home[i];
			}

		}

		return cnt >= c;
	}
}
