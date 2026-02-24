import java.util.*;
import java.io.*;

public class Main {

	static Scanner sc = new Scanner((System.in));
	static ArrayList<Integer>[] arr;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();

		arr = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a].add(b);
			arr[b].add(a);
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(arr[i]);
		}
		visit = new boolean[n + 1];
		dfs(v);
		System.out.println();
		visit = new boolean[n + 1];
		bfs(v);
	}

	static void dfs(int now) {

		System.out.print(now + " ");

		visit[now] = true;

		for (int next : arr[now]) {

			if (visit[next])
				continue;

			dfs(next);
		}

	}

	static void bfs(int now) {

		Queue<Integer> q = new LinkedList<>();
		visit[now] = true;
		q.add(now);
		
		while (q.size() > 0) {
			
			int n = q.poll();
			System.out.print(n + " ");
			for(int next : arr[n]) {
				if(!visit[next]) {
					q.add(next);
					visit[next] = true;
				}
			}
		}
	}

}
