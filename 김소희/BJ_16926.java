//  배열 돌리기 1

import java.util.*;
import java.io.*;

public class Main {

	static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		N, M, R
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); // 회전수 
				

		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			int c = 0;
			for (String num : br.readLine().split(" ")) {
				map[r][c++] = Integer.parseInt(num);
			}
		}

		
		int[][] newMap = new int[N][M];
		
		int Check;
		if (N <= M) Check = N/2;
		else Check = M/2;
		
		
//		row : 작은 수 (짝수) , 세로 / col : 큰 수 (홀수 또는 짝수), 가로
		for (int i = 0; i < Check; i++) {
			int Row = (N - 2*i -1), Col = (M - 2*i -1);
			int Round = Row * 2 + Col * 2;
			int Move = R % Round;
			Node[] MoveList = new Node[Round];
			
			int r1 = 0, r2 = 0, c1 = 0, c2 = 0;
			for (int m = 0; m < Round; m++) {
				if (0 <= m  && m < Row)
					MoveList[m] = new Node(i+r1++, i);
				else if (Row <= m && m < Row + Col) 
					MoveList[m] = new Node(i+Row, i+c1++);
				else if (Row + Col <= m && m < Row*2 + Col) 
					MoveList[m] = new Node(i+Row-r2++, i+Col);
				else if (Row*2 + Col <= m && m < Row*2 + Col*2) 
					MoveList[m] = new Node(i, i+Col-c2++);				
			}
			
			int index = 0;
			for (Node s : MoveList) {
				int new_index = index + Move;
				if (new_index >= MoveList.length)
					new_index = new_index % MoveList.length;
				
				int nr = MoveList[new_index].r;
				int nc = MoveList[new_index].c;
				
				newMap[nr][nc] = map[s.r][s.c];
				index++;
			}
		}			
	

		for (int[] row : newMap) {
			for (int c : row) {				
				System.out.print(c + " ");
			}
			System.out.println();
		}			
	}
}
