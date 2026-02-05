import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_17144 {

	static Scanner sc = new Scanner((System.in));

	static int[][] arr;
	static int r, c, t;
	static int mechine1y, mechine2y;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		r = sc.nextInt();
		c = sc.nextInt();
		t = sc.nextInt();

		arr = new int[r][c];

		List<Integer> a = new ArrayList<>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == -1)
					a.add(i);
			}
		}

		mechine1y = a.get(0);
		mechine2y = a.get(1);
		while (t-- > 0) {
			spread();
		}

		int ret = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] != -1) {
					ret += arr[i][j];
				}
			}

		}
		System.out.println(ret);

	}

	static void spread() {
		int[][] temp = new int[r][c];
		temp[mechine1y][0] = -1;
		temp[mechine2y][0] = -1;

		// 미세먼지 확산
		for (int y = 0; y < r; y++) {
			for (int x = 0; x < c; x++) {
				if (arr[y][x] < 0)
					continue;

				int spread_cnt = 0;
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny < 0 || ny >= r || nx < 0 || nx >= c || arr[ny][nx] == -1) {
						continue;
					}

					spread_cnt++;
					temp[ny][nx] += arr[y][x] / 5;
				}
				temp[y][x] += arr[y][x] - (arr[y][x] / 5) * spread_cnt;
			}
		}

		move(temp);
	}

	static void move(int[][] temp) {

		int Y1 = mechine1y;
		int Y2 = mechine2y;

		// 윗쪽 순회
		for (int i = Y1; i > 0; i--) {
			temp[i][0] = temp[i - 1][0];
		}
		for (int i = 0; i < c - 1; i++) {
			temp[0][i] = temp[0][i + 1];
		}
		for (int i = 0; i < Y1; i++) {
			temp[i][c - 1] = temp[i + 1][c - 1];
		}
		for (int i = c - 1; i > 1; i--) {
			temp[Y1][i] = temp[Y1][i - 1];
		}

		// 아랫쪽 순회
		for (int i = Y2; i < r - 1; i++) {
			temp[i][0] = temp[i + 1][0];
		}
		for (int i = 0; i < c - 1; i++) {
			temp[r - 1][i] = temp[r - 1][i + 1];
		}
		for (int i = r - 1; i > Y2; i--) {
			temp[i][c - 1] = temp[i - 1][c - 1];
		}
		for (int i = c - 1; i > 1; i--) {
			temp[Y2][i] = temp[Y2][i - 1];
		}

		temp[Y1][0] = -1;
		temp[Y2][0] = -1;
		temp[Y1][1] = 0;
		temp[Y2][1] = 0;

		arr = temp;
	}

}
