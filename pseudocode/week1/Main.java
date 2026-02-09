package test;

import java.io.*;
import java.util.*;

public class Main {
	static class Post implements Comparable<Post> {
		int id, h, w, x, y;

		public Post(int id, int h, int w, int x, int y) {
			this.id = id;
			this.h = h;
			this.w = w;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Post o) {
			return this.id - o.id;
		}
	}

	static int N, M;
	static Map<Integer, Post> list;
	static int[] height;
	static int[][] map;
	static StringBuilder sb;

	static void view() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j] +"\t");
			}
			System.out.println();
		}
		System.out.println("-------------");

	}

	static void left() {
		
		List<Post> tmp = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					Post p = list.get(map[i][j]);
					if(tmp.contains(p)) break;
 					boolean flag = false;
					for(int k=p.x;k<p.x+p.h;k++) {
						for(int h=p.y-1;h>=0;h--) {
							if(map[k][h] != 0) {
								flag = true;
								break;
							}
						}
						if(flag) break;
					}

					if(flag) break;
					tmp.add(p);
					break;
				}
			}
		}

		Collections.sort(tmp);
		if(tmp.isEmpty()) return;
		remove(tmp.get(0).id);
	}

	static void right() {
		List<Post> tmp = new ArrayList<>();
		for(int i=0;i<N;i++) {
			for(int j=N-1;j>=0;j--) {
				if(map[i][j] != 0) {
					Post p = list.get(map[i][j]);
					if(tmp.contains(p)) break;
					boolean flag = false;
					for(int k=p.x;k<p.x+p.h;k++) {
						for(int h=p.y+p.w;h<N;h++) {
							if(map[k][h] != 0) {
								flag = true;
								break;
							}
						}
						if(flag) break;
					}

					if(flag) break;
					tmp.add(p);
					break;
				}
			}
		}
		
		Collections.sort(tmp);
		if(tmp.isEmpty()) return;
		remove(tmp.get(0).id);
	}

	static void remove(int id) {
		Post p = list.remove(id);
		
		sb.append(p.id + "\n");
		for (int i = p.y; i < p.y + p.w; i++) {
			for (int j = p.x; j < p.x + p.h; j++)
				map[j][i] = 0;
		}
		Queue<Post> q = new LinkedList<>();

		q.add(p);
		boolean[] visited = new boolean[101];
		while (!q.isEmpty()) {
			Post ps = q.poll();
			for (int i = ps.y; i < ps.y + ps.w; i++) {
				for (int j = ps.x + ps.h; j < N; j++) {
					if (map[j][i] != 0) {
						
						Post pt = list.get(map[j][i]);
						if (gravity(pt.id)) {
							q.add(pt);
						}
						break;
					}
				}
			}
		}
	}

	static boolean gravity(int id) {
		Post p = list.get(id);
		int min = N;
		for (int i = p.y; i < p.y + p.w; i++) {
			int cnt = 0;
			for (int j = p.x - 1; j >=0; j--) {
				if(map[j][i] != 0) break;
				cnt++;
			}
				
			min = Math.min(cnt, min);
		}

		if (min == 0)
			return false;
		for (int i = p.y; i < p.y + p.w; i++) {
			for (int j = p.x; j < p.x + p.h; j++) {
				map[j - min][i] = map[j][i];
				map[j][i] = 0;
			}
		}
		p.x -= min;
		return true;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		height = new int[N];

		list = new HashMap<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;

			int max = 0;

			for (int j = c; j < c + w; j++) {
				max = Math.max(max, height[j]);
			}

			for (int k = c; k < c + w; k++) {
				for (int j = max; j < max + h; j++) {
					map[j][k] = id;
				}
				height[k] = max + h;
			}

			list.put(id, new Post(id, h, w, max, c));
		}

		sb = new StringBuilder();

		while (list.size() != 0) {
			left();

			right();

		}
		
		System.out.println(sb);
	}
}
