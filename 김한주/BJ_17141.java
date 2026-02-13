import java.util.*;
import java.io.*;

public class BJ_17141 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;

	static int[][] dList = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N;
	static int M;
	static int[] selected;
	static int[] visited;
	static List<int[]> virusPos;
	static int minTime = -1;
	static int[][] tempBoard;
	static int total;

	static int bfs() {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(result[i], -1);
		Deque<int[]> q = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			int[] vp = virusPos.get(selected[i]);
			q.add(vp);
			result[vp[0]][vp[1]] = 0;
		}

		int curTotal = total;
		if (total == 0)
			return 0;
		
		int maxTime = 0; // 이 BFS 회차에서 걸린 시간

		while (!q.isEmpty()) {
			int[] cur = q.pop();
			int x = cur[0];
			int y = cur[1];

			for (int[] d : dList) {
				int nx = x + d[0];
				int ny = y + d[1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (board[nx][ny] == 1)
					continue;

				if (result[nx][ny] != -1)
					continue;

				result[nx][ny] = result[x][y] + 1;
				curTotal--;
				maxTime = Math.max(maxTime, result[nx][ny]);
				if (curTotal == 0) return maxTime;

				q.add(new int[] { nx, ny });
			}
		}
		return -1;
	}

	static void dfs(int n, int idx) {
		if (n == M) { // 자리중에 M개를 뽑았으면
			// 바이러스 다 놓음.
			// 확산 해보기
			int temp = bfs();
			if (temp != -1) { // 성공했을 때만
				if (minTime == -1 || temp < minTime) {
					minTime = temp;
				}
			}
			return;
		}

		for (int i = idx; i < virusPos.size(); i++) {
			selected[n] = i;
			dfs(n + 1, i + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		virusPos = new ArrayList<>();

		Deque<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				board[i][j] = num;

				if (num == 2) {
					virusPos.add(new int[] { i, j });
				}
				if (num != 1)
					total++;
			}
		}
		selected = new int[M];
		total -= M;
		// 바이러스를 놓아야함.
		dfs(0, 0);
		System.out.println(minTime);
	}
}
