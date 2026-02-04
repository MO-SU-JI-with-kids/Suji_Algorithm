package test;

import java.util.*;
import java.io.*;

public class BJ_16946 {
	static class Point {
		int x,y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Point point = (Point) o;
	        return x == point.x && y == point.y;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x,y);
		}
	}
	
	static int N, M;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] map;
	static Point[][] res;
	static boolean[][] visited;
	static List<Point> wall;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		res = new Point[N][M];
		visited = new boolean[N][M];
		wall = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				int k = s.charAt(j) - '0';
				if(k==1) wall.add(new Point(i,j));
				map[i][j] = k;
			}
		}
		
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j] && map[i][j]==0) {
					bfs(new Point(i,j), cnt);
					cnt++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1) {
					int sum = 1;
					Set<Point> tp = new HashSet<>();
					for(int k=0;k<4;k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						
						if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 1) continue;
						tp.add(res[nx][ny]);
					}
					
					for(Point p: tp) {
						sum +=p.x;
					}
					sb.append(sum%10);
				}
				else sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	static void bfs(Point start, int ind) {

		Queue<Point> q = new LinkedList<>();
		List<Point> tmp = new ArrayList<>();
		int cnt = 1;
		visited[start.x][start.y] = true;
		q.add(start);
		tmp.add(start);
		while(!q.isEmpty()) {

			Point pt = q.poll();
			for(int i=0;i<4;i++) {
				int nx = pt.x + dx[i];
				int ny = pt.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] == 1 || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				q.add(new Point(nx, ny));
				tmp.add(new Point(nx, ny));
				cnt++;
			}
		}

		for(Point p : tmp) {
			res[p.x][p.y] = new Point(cnt, ind);
		}
	}
}
