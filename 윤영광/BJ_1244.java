import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1244 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// 남학생
			if (Integer.parseInt(st.nextToken()) == 1) {
				int div = Integer.parseInt(st.nextToken());
				for (int j = 1; j <= n; j++) {
					if (j % div == 0) {
						arr[j] = arr[j] == 1 ? 0 : 1;
					}
				}
			}
			// 여학생
			else {
				int start = Integer.parseInt(st.nextToken());
				int left = start;
				int right = start;

				arr[start] = arr[start] == 1 ? 0 : 1;
				while ((left > 0 && right <= n) && arr[left] == arr[right]) {
					arr[left] = arr[left] == 1 ? 0 : 1;
					arr[right] = arr[right] == 1 ? 0 : 1;
					left--;
					right++;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(arr[i] + " ");
			if (i % 20 == 0) {
				System.out.println();
			}
		}

	}

}
