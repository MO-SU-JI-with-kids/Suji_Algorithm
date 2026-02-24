import java.util.*;
import java.io.*;

class pair {
	int y, x;

	public pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class BJ_16236 {

	static Scanner sc = new Scanner(System.in);
	static int n, ret = 0;
	static int[][] map, visit;
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
	static int sharkSize = 2, eatCnt = 0;
	static int sy, sx;
	static boolean flag = true;

	public static void main(String[] args) {
		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					sy = i;
					sx = j;
					map[i][j] = 0;
				}
			}
		}

		while (check() && flag) {
			visit = new int[n][n];
			go(sy, sx);
		}

		System.out.println(ret);

	}

	private static boolean check() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] < sharkSize)
					cnt++;
			}
		}
		return cnt > 0;
	}

	static void go(int y, int x) {


		visit[y][x] = 1;
		Queue<pair> q = new LinkedList<>();
		q.add(new pair(y, x));

		int bestY = n, bestX = n, bestLen = Integer.MAX_VALUE;
		while (!q.isEmpty()) {

			pair now = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];

				if (ny < 0 || nx < 0 || ny == n || nx == n || visit[ny][nx] > 0)
					continue;

				if (map[ny][nx] > sharkSize)
					continue;

				visit[ny][nx] = visit[now.y][now.x] + 1;

				// 먹을 수 있는것중에 우선순위 높은 것 구하기
				if (map[ny][nx] > 0 && map[ny][nx] < sharkSize) {
					if (visit[ny][nx] < bestLen) {
						bestLen = visit[ny][nx];
						bestY = ny;
						bestX = nx;
					}
					if (visit[ny][nx] == bestLen) {
						if (ny < bestY) {
							
							bestY = ny;
							bestX = nx;
						} else if (ny == bestY) {
							if (nx < bestX)
								bestX = nx;
						}

					}

				}

				q.add(new pair(ny, nx));
			}

		}

		// 물고기에 도달 못하면
		if (bestY == n && bestX == n) {
			flag = false;
			return;
		}

		
		//상어 위치 갱신 + 몇초만에 갔는지 갱신
		map[bestY][bestX] = 0;
		sy = bestY;
		sx = bestX;
		ret += bestLen - 1;
		eatCnt++;

		if (eatCnt == sharkSize) {
			sharkSize++;
			eatCnt = 0;
		}

	}

}
