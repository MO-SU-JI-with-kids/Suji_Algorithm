package test;

import java.util.*;
import java.io.*;

public class BJ_2239 {	
	
	
	static int[][] map;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[9][9];
		

		for(int i=0;i<9;i++) {
			String s = br.readLine();
			for(int j=0;j<9;j++) {
				int k = s.charAt(j) - '0';
				map[i][j] = k;
			}
		}
		
		dfs(0,0);
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}
	
	static void dfs(int x, int y) {
		if(flag) return;
		if (y == 9) {
	        dfs(x + 1, 0); 
	        return;
	    }
	    if (x == 9) {
	        flag = true;
	        return;
	    }
	    
	    if(map[x][y] != 0) {
	    	dfs(x,y+1);
	    	return;
	    }
		
		for(int i=1;i<=9;i++) {
			boolean check = false;
			for(int j=0;j<9;j++) {
				if(map[x][j] == i || map[j][y] == i) {
					check = true;
					break;
				}
			}
			
			for(int j=x/3*3;j<x/3*3+3;j++) {
				for(int k=y/3*3;k<y/3*3+3;k++) {
					if(map[j][k] == i) {
						check = true;
						break;
					}
				}
				if(check) break;
			}
			
			if(!check) {
				map[x][y] = i;
				dfs(x,y+1);
				if(flag) return;
				map[x][y] = 0;
			}
		}
	}
}
