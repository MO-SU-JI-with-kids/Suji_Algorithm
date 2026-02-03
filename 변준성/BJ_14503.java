package test;

import java.util.*;
import java.io.*;

public class BJ_14503 {
	static int N,M;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int clean(int x, int y, int d) {
		int cnt = 0;
		
		while(true) {

			if(map[x][y] == 0) {
				map[x][y] = 2;
				cnt++;
			}
			
			boolean flag = false;
			for(int i = d+3; i>=d;i-- ) {
				int nx = x + dx[i%4];
				int ny = y + dy[i%4];
				
				if(map[nx][ny] == 0) {
					x = nx;
					y = ny;
					d = i%4;
					flag = true;
					break;
				}
				
			}
			
			if(!flag) {
				x -= dx[d];
				y -= dy[d];
				if(map[x][y] == 1) break;
				
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(clean(x,y,d));
	}
}

