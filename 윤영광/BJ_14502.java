import java.util.*;
import java.io.*;

public class BJ_14502 {

	static Scanner sc = new Scanner((System.in));
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] arr;
	static int n, m, ret = 0;
	static Queue<pair> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		m = sc.nextInt();

		arr = new int[n][m];
		visit = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 2)
					q.add(new pair(i, j));
			}
		}

		dfs(0);

		System.out.println(ret);
	}

	static void dfs(int cnt) {

		if (cnt == 3) {
			spread();
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(cnt + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	static void spread() {

		int[][] arrT = new int[n][m];
		for (int i = 0; i < n; i++) {
			arrT[i] = arr[i].clone();
		}

		Queue<pair> temp = new LinkedList<>();
		for (pair p : q) {
			temp.add(p);
		}
		boolean[][] visit = new boolean[n][m];

		while (temp.size() > 0) {

			pair p = temp.poll();
			int y = p.y, x = p.x;
			visit[y][x] = true;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				if (visit[ny][nx] || arrT[ny][nx] == 1 || arrT[ny][nx] == 2)
					continue;

				arrT[ny][nx] = 2;
				temp.add(new pair(ny, nx));
				visit[ny][nx] = true;

			}
		}
		count(arrT);
	}

	static void count(int[][] arrT) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arrT[i][j] == 0)
					cnt++;
			}
		}

		ret = Math.max(ret, cnt);
	}

}
