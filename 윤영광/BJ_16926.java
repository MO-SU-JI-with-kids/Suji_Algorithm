import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16926 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		solve();
	}

	static int[][] arr = new int[1000][1000];
	static int n, m, r;

	static void solve() throws IOException {
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);

		// 입력 받기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < r; i++) {
			rotate();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	}

	static void rotate() {

		int deep = Math.min(n, m) / 2;

		for (int lv = 0; lv < deep; lv++) {
			int rowLen = n - 2 * lv;
			int colLen = m - 2 * lv;

			int temp = arr[lv][lv];

			for (int c = lv; c < lv + colLen - 1; c++) {
				arr[lv][c] = arr[lv][c + 1];
			}

			for (int r = lv; r < lv + rowLen - 1; r++) {
				arr[r][lv + colLen - 1] = arr[r + 1][lv + colLen - 1];
			}

			for (int c = lv + colLen - 1; c > lv; c--) {
				arr[lv + rowLen - 1][c] = arr[lv + rowLen - 1][c - 1];
			}

			for (int r = lv + rowLen - 1; r > lv + 1; r--) {
				arr[r][lv] = arr[r - 1][lv];
			}

			arr[lv + 1][lv] = temp;
		}

	}
}
