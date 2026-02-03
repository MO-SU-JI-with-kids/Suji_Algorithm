import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());

		int s = 0;
		int e = n - 1;
		
		int count = 0;

		while (s < e) {
			if (nums[e] + nums[s] == x) {
				count++;
				e--;
				s++;
			} else if(nums[e] + nums[s] > x) {
				e--;
			} else {
				s++;
			}
		}

		System.out.println(count);
	}
}
