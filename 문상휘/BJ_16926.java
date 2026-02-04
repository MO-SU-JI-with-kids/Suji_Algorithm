import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// setting
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());

			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		// do
		int layerCount = Math.min(N, M) / 2;

		for (int i = 0; i < layerCount; i++) {
			int minY = i;
			int maxY = N - i - 1;
			int minX = i;
			int maxX = M - i - 1;

			List<Integer> list = new ArrayList<>();
			int len = (maxX - minX + 1) * 2 + (maxY - minY - 1) * 2;
			int y = minY;
			int x = minX;

			// 추출
			for (int j = 0; j < len; j++) {
				list.add(map[y][x]);

				if (y == minY && x < maxX) {
					x++;
					continue;
				}

				if (x == maxX && y < maxY) {
					y++;
					continue;
				}

				if (y == maxY && x > minX) {
					x--;
					continue;
				}

				if (x == minX && y > minY) {
					y--;
					continue;
				}
			}

			// 위치 옮기기
			int r = R % list.size();
			List<Integer> changed = new ArrayList<>();
			changed.addAll(list.subList(r, list.size()));
			changed.addAll(list.subList(0, r));

			y = minY;
			x = minX;

			// 재배치
			for (int j = 0; j < len; j++) {
				map[y][x] = changed.get(j);

				if (y == minY && x < maxX) {
					x++;
					continue;
				}

				if (x == maxX && y < maxY) {
					y++;
					continue;
				}

				if (y == maxY && x > minX) {
					x--;
					continue;
				}

				if (x == minX && y > minY) {
					y--;
					continue;
				}
			}
		}

		// 출력
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				System.out.print(map[y][x] + " ");
			}

			System.out.println();
		}
	}
}
