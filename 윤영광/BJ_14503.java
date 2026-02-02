import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14503 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		solve();
	}

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int direction;
	static int starty;
	static int startx;

	static int cnt = 0;
	static int n, m;

	static void solve() throws IOException {
		// n,m 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 로봇 입력
		st = new StringTokenizer(br.readLine());
		starty = Integer.parseInt(st.nextToken());
		startx = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];

		// 배열 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(starty, startx, direction, arr);

		System.out.println(cnt);
	}

	static void dfs(int Y, int X, int dir, int[][] arr) {
		//  현재 위치가 청소되지 않은 경우 청소
		if (arr[Y][X] == 0) {
			arr[Y][X] = 2; // 청소 표시
			cnt++;
		}

		//주변 4칸 중 청소되지 않은 빈칸이 있는지 확인
		boolean isclean = false;
		for (int i = 0; i < 4; i++) {
			int ny = Y + dy[i];
			int nx = X + dx[i];
			if (arr[ny][nx] == 0) {
				isclean = true;
				break;
			}
		}

		if (isclean) {
			// 청소할 곳이 있는 경우
			for (int i = 0; i < 4; i++) {
				dir = (dir + 3) % 4; 
				int ny = Y + dy[dir];
				int nx = X + dx[dir];
				if (arr[ny][nx] == 0) {
					dfs(ny, nx, dir, arr);
					return;
				}
			}
		} else {
			// 청소할 곳이 없는 경우
			int backDir = (dir + 2) % 4;
			int by = Y + dy[backDir];
			int bx = X + dx[backDir];

			if (arr[by][bx] != 1) {
				dfs(by, bx, dir, arr);
			} else {
				return;
			}
		}
	}

}
