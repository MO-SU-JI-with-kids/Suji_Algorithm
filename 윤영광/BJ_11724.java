import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

class pair {
	int y, x;

	public pair(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public pair() {
	}
}

public class BJ_11724 {

	static Scanner sc = new Scanner((System.in));

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int n, m;
	static boolean[] visit;
	static ArrayList<Integer>[] arr;

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		m = sc.nextInt();

		arr = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int u = sc.nextInt(), v = sc.nextInt();

			arr[u].add(v);
			arr[v].add(u);
		}

		visit = new boolean[n+1];
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (!visit[i]) {
				bfs(i);
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	static void bfs(int now) {
		Queue<Integer> q = new LinkedList<>();

		q.add(now);
		visit[now] = true;

		while (q.size() > 0) {
			int nw = q.poll();

			for (int next : arr[nw]) {
				if (visit[next])
					continue;

				q.add(next);
				visit[next] = true;
			}
		}

	}
}
