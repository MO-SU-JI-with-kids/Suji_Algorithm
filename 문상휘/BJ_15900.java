import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Integer>[] tree;
	static int depthSum = 0;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];

		// init
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}

		// input
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			tree[n1].add(n2);
			tree[n2].add(n1);
		}

		countDepth(1, 0, 0);
		
		if(depthSum % 2 == 0) {
			System.out.println("No");
		} else {
			System.out.println("Yes");
		}
	}

	private static void countDepth(int x, int prev, int depth) {
		boolean isLast = true;

		for (int element : tree[x]) {
			if (element == prev) {
				continue;
			}

			isLast = false;
			countDepth(element, x, depth + 1);
		}

		if (isLast) {
			depthSum += depth;
		}
	}
}
