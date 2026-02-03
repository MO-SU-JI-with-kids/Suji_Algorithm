import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2563 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());

		int len = 10;
		int[][] arr = new int[105][105];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			for (int row = q; row < q + 10; row++) {
				for (int col = p; col < p + 10; col++) {
					arr[row][col] = 1;
				}
			}
		}

		int ret =0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(arr[i][j] ==1) ret++;
			}
		}

		System.out.println(ret);
	}

}
