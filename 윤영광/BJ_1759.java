import java.util.*;
import java.io.*;

public class BJ_1759 {

	static Scanner sc = new Scanner((System.in));
	static int l, c;
	static ArrayList<Character> list, temp;

	public static void main(String[] args) throws IOException {

		l = sc.nextInt();
		c = sc.nextInt();

		list = new ArrayList<>();
		temp = new ArrayList<>();

		for (int i = 0; i < c; i++) {
			list.add(sc.next().charAt(0));
		}

		Collections.sort(list);

		combi(0);
	}

	static void combi(int start) {
		if (temp.size() == l) {

			if (!check())
				return;

			StringBuffer sb = new StringBuffer();
			for (char ch : temp) {
				sb.append(ch);
			}

			System.out.println(sb);

			return;
		}

		for (int i = start; i < c; i++) {

			temp.add(list.get(i));
			combi(i + 1);
			temp.remove(temp.size() - 1);

		}

	}

	static boolean check() {

		int vCnt = 0; // 모음 개수 (vowel)
		int cCnt = 0; // 자음 개수 (consonant)

		for (char ch : temp) {
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				vCnt++;
			} else {
				cCnt++;
			}
		}

		return vCnt >= 1 && cCnt >= 2;
	}
}
