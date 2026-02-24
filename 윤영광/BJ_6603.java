import java.util.*;
import java.io.*;

public class BJ_6603 {

	static Scanner sc = new Scanner((System.in));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, k;
	static ArrayList<Integer> list, temp;

	public static void main(String[] args) throws IOException {

		while (true) {

			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());

			if (k == 0)
				return;

			list = new ArrayList<>();
			temp = new ArrayList<>();

			for (int i = 0; i < k; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			Collections.sort(list);

			combi(0);

			System.out.println();
		}

	}

	static void combi(int start) {

		if (temp.size() == 6) {

			for (int nn : temp) {
				System.out.print(nn + " ");
			}
			System.out.println();

			return;
		}

		for (int i = start; i < k; i++) {
			temp.add(list.get(i));
			combi(i + 1);
			temp.remove(temp.size() - 1);
		}

	}
}
