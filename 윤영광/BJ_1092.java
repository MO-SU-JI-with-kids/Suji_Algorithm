import java.util.*;
import java.io.*;

public class BJ_1092 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		n = sc.nextInt();
		ArrayList<Integer> crain = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			crain.add(sc.nextInt());
		}
		Collections.sort(crain, Collections.reverseOrder());

		m = sc.nextInt();
		ArrayList<Integer> box = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			box.add(sc.nextInt());
		}

		Collections.sort(box, Collections.reverseOrder());

		// 크레인이 못옮기는게 있으면
		if (crain.get(0) < box.get(0)) {
			System.out.println(-1);
			return;
		}

		int time = 0;
		while (box.size() > 0) {
			time++;
			int crainidx = 0;
			int boxidx = 0;

			while (crainidx < crain.size()) {
				if (boxidx == box.size())
					break;

				// 옮길 수 있으면
				if (crain.get(crainidx) >= box.get(boxidx)) {
					box.remove(boxidx);
					crainidx++;
				}
				// 옮길 수 없으면, 옆 박스로 이동
				else {
					boxidx++;
				}

			}

		}
		
		System.out.println(time);
	}

}
