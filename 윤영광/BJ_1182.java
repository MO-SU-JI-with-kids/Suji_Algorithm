import java.util.*;
import java.io.*;

public class BJ_1182 {

	static Scanner sc = new Scanner((System.in));
	static int n, s;
	static boolean[] visit;
	static ArrayList<Integer> list;

	public static void main(String[] args) {

		n = sc.nextInt();
		s = sc.nextInt();

		int[] lst = new int[n];

		for (int i = 0; i < n; i++) {
			lst[i] = sc.nextInt();
		}

		Arrays.sort(lst);

		int ret =0;
		for (int i = 1; i < (1 << n); i++) {

			int sum = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0)
					sum += lst[j];
			}
			
			if(sum == s) ret++;
		}

		System.out.println(ret);
	}

}
