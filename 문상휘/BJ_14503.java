import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		// setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int direct = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// do
		int count = 0;

		while (true) {
			if (map[y][x] == 0) {
				map[y][x] = 2;
				count++;
			}
			
			boolean isClear = true;

			for (int i = 1; i <= 4; i++) {
				int j = (direct - i + 4) % 4;
				int ny = y + dy[j];
				int nx = x + dx[j];

				// clear 하지 않은 경우
				if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 0) {
					y = ny;
					x = nx;
					direct = j;
					isClear = false;
					break;
				}
			}
			
			if(!isClear) {
				continue;
			}

			// clear 한 경우
			int back = (direct + 2) % 4;
			y += dy[back];
			x += dx[back];

			if (y < 0 || x < 0 || y >= N || x >= M || map[y][x] == 1) {
				break;
			}
		}

		System.out.println(count);
	}
}
