import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int count = 0;
	static boolean[] visitedX;
	static boolean[] visitedUp;
	static boolean[] visitedDown;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		visitedX = new boolean[N];
		visitedUp = new boolean[2 * N - 1];
		visitedDown = new boolean[2 * N - 1];

		btk(N, 0);
		System.out.println(count);
	}

	private static void btk(int N, int depth) {
		if (depth == N) {
			count++;
			return;
		}

		for (int x = 0; x < N; x++) {
			int up = depth + x;
			int down = depth - x + N - 1;
			
			if(!visitedX[x] && !visitedUp[up] && !visitedDown[down]) {
				visitedX[x] = true;
				visitedUp[up] = true;
				visitedDown[down] = true;
				btk(N, depth + 1);
				visitedX[x] = false;
				visitedUp[up] = false;
				visitedDown[down] = false;
			}
		}
	}
}
