package algorithm;

import java.io.*;
import java.util.*;

public class 색종이_2563 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] WhitePaper = new int[110][110];
		
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int C = Integer.parseInt(input[0]);
    		int R = Integer.parseInt(input[1]);
    		
    		for (int r = R; r < R+10; r++) {
    			for (int c = C; c < C+10; c++) {
    				if (WhitePaper[r][c] != 1) {
    					WhitePaper[r][c] = 1;
    					count++;
    				}    				
    			}
    		}
		}
		
		System.out.println(count);
		
	}
	
}
