import java.util.*;
import java.io.*;

public class BJ_15649 {

	static Scanner sc = new Scanner((System.in));
	static int n, m;
	static boolean[] visit;
	static ArrayList<Integer> list;

	public static void main(String[] args) {

		n = sc.nextInt();
		m = sc.nextInt();
		visit = new boolean[n + 1];
		list = new ArrayList<>();
		perm();
	}

	static void perm() {
		if (list.size() == m) {

			for (int k : list) {
				System.out.print(k + " ");
			}
			System.out.println();

			return;
		}

		for (int i = 1; i <= n; i++) {
			if (visit[i])
				continue;
			list.add(i);
			visit[i] = true;
			perm();
			visit[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
