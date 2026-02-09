import java.util.*;
import java.io.*;



public class BJ_17141 {

	static Scanner sc = new Scanner((System.in));

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] arr;
	static int[][] visit;
	static int n, m, ret = Integer.MAX_VALUE;
	static ArrayList<pair> list = new ArrayList<>();
	static ArrayList<pair> q = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		m = sc.nextInt();

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 2) {
					arr[i][j] = 0;
					list.add(new pair(i, j));
				}
			}
		}

		dfs(0, 0);

		if (ret == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(ret - 1);
	}

	static void dfs(int cnt, int start) {

		if (cnt == m) {
			spread();
			return;
		}

		for (int i = start; i < list.size(); i++) {

			pair p = list.get(i);

			arr[p.y][p.x] = 2;
			q.add(p);

			dfs(cnt + 1, i + 1);

			arr[p.y][p.x] = 0;
			q.remove(q.size() - 1);

		}
	}

	static void spread() {

		int[][] arrT = new int[n][n];
		for (int i = 0; i < n; i++) {
			arrT[i] = arr[i].clone();
		}
		visit = new int[n][n];
		Queue<pair> temp = new LinkedList<>();
		for (pair p : q) {
			temp.add(p);
			visit[p.y][p.x] = 1;
		}
		

		while (temp.size() > 0) {

			pair p = temp.poll();
			int y = p.y, x = p.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= n)
					continue;
				if (visit[ny][nx] > 0 || arrT[ny][nx] == 1 || arrT[ny][nx] == 2)
					continue;

				arrT[ny][nx] = 2;
				temp.add(new pair(ny, nx));
				visit[ny][nx] = visit[y][x] + 1;
			}
		}
		count(arrT);
	}

	static void count(int[][] arrT) {
		int mx = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == 0 && arrT[i][j] == 0) {
					return;
				}
				mx = Math.max(mx, visit[i][j]);
			}
		}

		ret = Math.min(ret, mx);
	}

}
