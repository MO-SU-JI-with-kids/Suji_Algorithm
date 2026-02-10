package test;

import java.io.*;
import java.util.*;

public class BJ_1799 {
	static class Point {
		int x,y;
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N;
	static List<Point> black, white;
	static boolean[] du, dd;
	static int[] max;
	static int[][] map;
	
	static void dfs(int dep, int cnt, List<Point> tmp, int color) {
		if(dep==tmp.size()) {
			max[color] = Math.max(max[color], cnt);
			return;
		}
		if(max[color] >= cnt+tmp.size()-dep) return;
		
		dfs(dep+1, cnt, tmp, color);
		
		Point p = tmp.get(dep);
		if(!du[p.x+p.y] && !dd[p.x-p.y+N-1]) {
			du[p.x+p.y] = true;
			dd[p.x-p.y+N-1] = true;
			dfs(dep+1, cnt+1, tmp, color);
			du[p.x+p.y] = false;
			dd[p.x-p.y+N-1] = false;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		
		black = new ArrayList<>();
		white = new ArrayList<>();
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k==1) {
					if((i+j)%2==0) black.add(new Point(i,j));
					else white.add(new Point(i,j));
				}
				
			}
		}
		
		du = new boolean[2*N+1];
		dd = new boolean[2*N+1];
		max = new int[]{-1, -1};
		
		dfs(0,0,white,0);
		dfs(0,0,black,1);
		
		System.out.println(max[0] + max[1]);
		
	}
}