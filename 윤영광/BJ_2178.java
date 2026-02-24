import java.util.*;
import java.io.*;

class pair {
	int y, x;

	public pair(int y, int x) {
		this.y = y;
		this.x = x;

	}

	public pair() {

	}
}

public class BJ_2178 {

	static Scanner sc = new Scanner((System.in));
	static ArrayList<Integer>[] arr;
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		int n = sc.nextInt();
		int m = sc.nextInt();

		int[][] arr = new int[n][m];
		visit = new int[n][m];

		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		Queue<pair> q = new LinkedList<>();
		q.add(new pair(0, 0));
		visit[0][0] = 1;
		while (q.size() > 0) {

			pair p = q.poll();
			int y = p.y;
			int x = p.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				if (visit[ny][nx] > 0 || arr[ny][nx] == 0)
					continue;
				
				q.add(new pair(ny,nx));
				visit[ny][nx] = visit[y][x] +1;
			}
		}
		
		System.out.println(visit[n-1][m-1]);

	}

}
