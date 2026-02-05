import java.util.*;
import java.io.*;

public class BJ_17144 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		board = new int[r][c];

		int[] machineTop = new int[2];
		int[] machineBot = new int[2];

		boolean checkedMachine = false;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < c; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == -1 && !checkedMachine) {
					machineTop[0] = i;
					machineTop[1] = j;
					machineBot[0] = i + 1;
					machineBot[1] = j;
					checkedMachine = true;
				}

				board[i][j] = n;
			}
		}

		int[][] dList = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

		for (int k = 0; k < t; k++) {
			// 확산

			int[][] newBoard = new int[r][c];
			boolean[][] visited = new boolean[r][c];

			newBoard[machineTop[0]][machineTop[1]] = -1;
			newBoard[machineBot[0]][machineBot[1]] = -1;

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (board[i][j] > 0) {
						int diffusionValue = (int) Math.floor(board[i][j] / 5);
						int remainValue = board[i][j];

						for (int[] d : dList) {
							int nx = i + d[0];
							int ny = j + d[1];

							if (diffusionValue == 0)
								break;

							if (nx < 0 || nx >= r || ny < 0 || ny >= c)
								continue;
							if (board[nx][ny] == -1)
								continue;

							newBoard[nx][ny] += diffusionValue;
							remainValue -= diffusionValue;
						}
						// 음수 방지 && 빼기
						if (remainValue <= 0) {
							remainValue = 0;
						}
						newBoard[i][j] += remainValue;
					}
				}
			}

			// 청소
			// 위
			// 위-왼쪽
			for (int j = machineTop[0] - 1; j > 0; j--)
				newBoard[j][0] = newBoard[j - 1][0];
			// 위-위
			for (int j = 0; j < c - 1; j++)
				newBoard[0][j] = newBoard[0][j + 1];

			for (int j = 0; j < machineTop[0]; j++)
				newBoard[j][c - 1] = newBoard[j + 1][c - 1];

			for (int j = c - 1; j > 1; j--)
				newBoard[machineTop[0]][j] = newBoard[machineTop[0]][j - 1];

			newBoard[machineTop[0]][1] = 0;
			// 아래
			// 아래 왼쪽
			for (int j = machineBot[0] + 1; j < r - 1; j++)
				newBoard[j][0] = newBoard[j + 1][0];
			// 아래-아래
			for (int j = 0; j < c - 1; j++)
				newBoard[r - 1][j] = newBoard[r - 1][j + 1];

			for (int j = r - 1; j > machineBot[0]; j--)
				newBoard[j][c - 1] = newBoard[j - 1][c - 1];

			for (int j = c - 1; j > 1; j--)
				newBoard[machineBot[0]][j] = newBoard[machineBot[0]][j - 1];

			newBoard[machineBot[0]][1] = 0;
			board = newBoard;
		}

		int result = 2;
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				result += board[i][j];

		System.out.println(result);

	}
}
