import java.util.*;
import java.io.*;

public class BJ_1339 {

	static Scanner sc = new Scanner(System.in);
	static int n, m, k, t;

	public static void main(String[] args) {

		solve();

	}

	private static void solve() {
		n = sc.nextInt();

		String[] words = new String[n];

		for (int i = 0; i < n; i++) {
			words[i] = sc.next();
		}

		// 남은 숫자
		int N = 9;

		int[] charArr = new int[100];

		
		for(String word : words) {

			int w = 1;
			for (int i = word.length() - 1; i >= 0; i--) {
				charArr[word.charAt(i)] += w;
				w *= 10;
			}
			
		}
		
		
		
	
		

		Arrays.sort(charArr);

		int ret = 0;
		for (int i = charArr.length - 1; i >= 0; i--) {
			if (charArr[i] == 0)
				break;

			ret += N * charArr[i];
			N--;
		}

		System.out.println(ret);
	}

}
