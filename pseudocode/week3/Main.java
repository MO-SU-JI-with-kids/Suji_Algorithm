package test;

import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int x, y, dist, j;
		public Point (int x, int y, int dist, int j) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.j = j;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.dist - p.dist;
		}
	}
	
	static int N;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][][] dist;
	static char[][] map;
	
	static void bfs(Point start, Point end) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Arrays.fill(dist[i][j], 1_000_000_000);
			}
		}
		dist[start.x][start.y][1] = 0;
		pq.add(start); 
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			if(dist[now.x][now.y][now.j] > now.dist) continue;
			
			for(int i=0;i<4;i++) {
				for(int k=1;k<=5;k++) {
					int nx = now.x + dx[i]*k;
					int ny = now.y + dy[i]*k;
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 'S') continue;
					if(map[nx][ny] == '#') break;
					
					int nextDist = now.dist+1;
					
					if(k<now.j) {
						nextDist += 1;
					} 
					else if(k>now.j) {
						for(int h = now.j+1;h<=k;h++) nextDist += (int) Math.powExact(h, 2);
					}
			
					
					if(nextDist < dist[nx][ny][k]) {
						dist[nx][ny][k] = nextDist;
						pq.add(new Point(nx, ny, nextDist, k));
					}
					
				}
			}
		}
		
		int res = Integer.MAX_VALUE;
		for(int i=0;i<6;i++) {
			res = Math.min(res, dist[end.x][end.y][i]);
		}
		
		if(res == 1_000_000_000) res = -1;
		System.out.println(res);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new char[N][N];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		dist = new int[N][N][6];
		int Q = Integer.parseInt(br.readLine().trim());
		
		for(int i=0;i<Q;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			
			bfs(new Point(r1, c1, 0, 1), new Point(r2, c2, 0, 0));
		}
	}
}
