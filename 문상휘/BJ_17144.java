import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static int[][] spreadMap;

	public static void main(String[] args) throws IOException {
		// setting
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int[] air = new int[2];
		int k = 0;

		for (int y = 0; y < R; y++) {
			st = new StringTokenizer(br.readLine());

			for (int x = 0; x < C; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());

				if (map[y][x] == -1) {
					air[k] = y;
					k++;
				}
			}
		}

		// do
		for (int t = 0; t < T; t++) {
			spread(R, C);
			cleanUp(air[0], R, C);
			cleanDown(air[1], R, C);
		}

		// sum
		int sum = 0;

		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {
				if(map[y][x] > 0) {
					sum += map[y][x];
				}
			}
		}
		
		System.out.println(sum);
	}

	private static void spread(int R, int C) {
		spreadMap = new int[R][C];

		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {
				if (map[y][x] > 0) {
					int spreadCount = 0;

					for (int i = 0; i < 4; i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						int spread = map[y][x] / 5;

						if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1) { // 전파 범위 아님
							continue;
						}

						spreadMap[ny][nx] += spread;
						spreadCount++;
					}

					map[y][x] -= (map[y][x] / 5) * spreadCount;
				}
			}
		}

		// 확산된 미세먼지 합치기
		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {
				map[y][x] += spreadMap[y][x];
			}
		}
	}

	private static void cleanUp(int top, int R, int C) {
		for (int i = top - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}

		for (int i = 0; i < top; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[top][i] = map[top][i - 1];
		}

		map[top][1] = 0;
	}

	private static void cleanDown(int bottom, int R, int C) {
		for (int i = bottom + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}

		for (int i = R - 1; i > bottom; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[bottom][i] = map[bottom][i - 1];
		}

		map[bottom][1] = 0;
	}
}
