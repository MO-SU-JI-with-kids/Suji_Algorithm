package test;

import java.util.*;
import java.io.*;

public class BJ_17140 {
	static class Point implements Comparable<Point> {
		int n, c;
		public Point (int n, int c) {
			this.n=n;
			this.c=c;
		}
		
		@Override
		public int compareTo(Point o) {
			if(o.c == this.c) return this.n - o.n;
			return this.c - o.c;
		}
	}
	static int R,C;
	static int r,c,k;
	static int[][] map;
	
	static void Rcal () {
		int maxr = -1;
		int[][] temp = new int[100][100];
		for(int i=0;i<R;i++) {
			Map<Integer, Point> tmp = new HashMap<>();
			for(int j=0;j<C;j++) {
				int num = map[i][j];
				if(num==0) continue;
				tmp.compute(num, (k, v) -> {
		            if (v == null) return new Point(k, 1); 
		            v.c++; 
		            return v;
		        });
			}
			PriorityQueue<Point> pq = new PriorityQueue<>(tmp.values());
			int ind = 0;
			while(!pq.isEmpty() && ind<100) {
				Point pt = pq.poll();
				temp[i][ind] = pt.n;
				temp[i][ind+1] = pt.c;
				ind +=2;
			}
			maxr = Math.max(ind, maxr);
		}
		C = maxr;
		map = temp;
	}
	static void Ccal () {
		int maxc=-1;
		int[][] temp = new int[100][100];
		for(int i=0;i<C;i++) {
			Map<Integer, Point> tmp = new HashMap<>();
			for(int j=0;j<R;j++) {
				int num = map[j][i];
				if(num==0) continue;
				tmp.compute(num, (k, v) -> {
		            if (v == null) return new Point(k, 1); 
		            v.c++; 
		            return v;
		        });
			}
			PriorityQueue<Point> pq = new PriorityQueue<>(tmp.values());
			int ind = 0;
			while(!pq.isEmpty() && ind<100) {
				Point pt = pq.poll();
				temp[ind][i] = pt.n;
				temp[ind+1][i] = pt.c;
				ind +=2;
			}
			maxc = Math.max(ind, maxc);
		}
		R = maxc;
		map = temp;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		R = 3;
		C = 3;
		
		map = new int[100][100];
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(time<=100) {
			if( map[r][c] == k) break;
			time++;
			if(R>=C) {
				Rcal();
			}
			else {
				Ccal();
			}
		}
		if(time>100) System.out.println(-1);
		else System.out.println(time);
	}
}
