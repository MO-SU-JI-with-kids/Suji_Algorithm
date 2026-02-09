import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int day = 0;
	static List<int[]> tomatoes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// setting
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					tomatoes.add(new int[] { i, j, 0 });
				}
			}
		}

		// bfs
		bfs(N, M);

		// check
		boolean isFull = true;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0) {
					isFull = false;
				}
			}
		}

		if (isFull) {
			System.out.println(day);
		} else {
			System.out.println(-1);
		}
	}

	private static void bfs(int N, int M) {
		Queue<int[]> queue = new LinkedList<>();

		for (int[] tomato : tomatoes) {
			queue.offer(tomato);
		}

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int y = now[0];
			int x = now[1];
			int time = now[2];
			day = time;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 0) {
					map[ny][nx] = 1;
					queue.offer(new int[] { ny, nx, time + 1 });
				}
			}
		}
	}
}
