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

public class Main {

	static Scanner sc = new Scanner((System.in));

	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 1, 0 };
	static int n, m, ret = 0;
	static boolean[][] visit;
	static int[][] arr;

	public static void BJ_17070(String[] args) throws IOException {
		n = sc.nextInt();
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		dfs(0, 1, 0);
		System.out.println(ret);
	}

	static void dfs(int y, int x, int dir) {

		if (y == n - 1 && x == n - 1) {
			ret++;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if ((dir == 0 && i == 2) || (dir == 2 && i == 0))
				continue;

			if (ny < 0 || nx < 0 || ny >= n || nx >= n)
				continue;

			if (i == 1) {
				if (arr[y + 1][x] == 1 || arr[y][x + 1] == 1)
					continue;
			}

			if (arr[ny][nx] == 1)
				continue;

			dfs(ny, nx, i);
		}

	}
}
