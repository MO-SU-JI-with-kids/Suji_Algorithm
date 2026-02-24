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

public class BJ_2667 {

	static Scanner sc = new Scanner((System.in));

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int n, m;
	static boolean[][] visit;
	static int[][] arr;
	static ArrayList<Integer> ret = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();

		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		visit = new boolean[n][n];

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1 && !visit[i][j]) {
					ret.add(bfs(i, j));
					cnt++;
				}
			}
		}
		Collections.sort(ret);
		System.out.println(cnt);
		for(int a : ret) {
			System.out.println(a);
		}
	}

	static int bfs(int Y, int X) {

		int cnt = 1;
		Queue<pair> q = new LinkedList<>();
		q.add(new pair(Y, X));
		visit[Y][X] = true;

		while (q.size() > 0) {
			pair p = q.poll();

			int y = p.y, x = p.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i], nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= n)
					continue;
				if (visit[ny][nx] || arr[ny][nx] == 0)
					continue;

				q.add(new pair(ny, nx));
				visit[ny][nx] = true;
				cnt++;

			}
		}

		return cnt;
	}
}
