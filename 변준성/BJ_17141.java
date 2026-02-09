package test;

import java.io.*;
import java.util.*;

public class BJ_17141 {
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static List<Point> virus;
	static List<int[]> comb;
	static int min = 1_000_000_000;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] map;
	
	static void combi(int dep, int cnt, int[] num) {
		
		if(cnt==M) {
			comb.add(num.clone());
			return;
		}
		for(int i=dep;i<virus.size();i++) {
			num[cnt] = i;
			combi(i+1, cnt+1, num);
		}
	}
	
	static void bfs(int[] start) {
		int[][] dist = new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(dist[i], 1_000_000_000);
		}
		Queue<Point> q = new LinkedList<>();
		for(int i : start) {
			Point p = virus.get(i);
			dist[p.x][p.y] = 0;
			q.add(p);
		}
		
		while(!q.isEmpty()) {
			Point pt = q.poll();
			
			for(int i=0;i<4;i++) {
				int nx = pt.x+dx[i];
				int ny = pt.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] == 1) continue;
				int nDist = dist[pt.x][pt.y] + 1;
				if(dist[nx][ny] > nDist) {
					dist[nx][ny] = nDist;
					q.add(new Point(nx, ny));
				}
			}
		}
		
		int max = -1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {

				if(map[i][j] == 1) continue;
				max = Math.max(dist[i][j], max);
			}
		}
		
		min = Math.min(min, max);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		comb = new ArrayList<>();
		
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int k = Integer.parseInt(st.nextToken());
				map[i][j] = k;
				
				if(k==2) {
					virus.add(new Point(i,j));
				}
			}
		}
		
		combi(0,0,new int[M]);
		for(int[] n : comb) bfs(n);
		if(min==1000000000) min = -1;
		System.out.println(min);
	}
}
