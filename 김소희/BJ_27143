package algorithm;

import java.util.*;
import java.io.*;

public class 낚시왕_27143 {

	static class fish {		
		int velocity;
		int dirct;
		int size;
		
		public fish (int v, int d, int s) {
			this.velocity = v;
			this.dirct = d;
			this.size = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int KING = -1;
		int SIZE_SUM = 0;
		
//		d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
		int[] dr = new int[] {0, -1, 1, 0, 0};
		int[] dc = new int[] {0, 0, 0, 1, -1};
		
		
		// 입력
		fish[][] sea = new fish[R][C];
		for (int m = 0; m < M; m++) {
			List<Integer> input = new ArrayList<>();
			for(String f: br.readLine().split(" ")) {
				input.add(Integer.parseInt(f));
			}
			fish Fish = new fish(input.get(2), input.get(3), input.get(4));
			sea[input.get(0)-1][input.get(1)-1] = Fish;
		}

		
		// 구현
		while(KING < C-1) {
			
			// Step1 낚시왕이 오른쪽으로 한 칸 이동한다.
			KING++;
			
			
			// Step2 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			for (int r = 0; r < R; r++) {
				if (sea[r][KING] != null) {					
					SIZE_SUM += sea[r][KING].size;
					sea[r][KING] = null;
					break;
				}
			}
			
			// Step3 상어가 이동한다.
			fish[][] copySea = new fish[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (sea[r][c] != null) {		
						fish FISH = sea[r][c];
						int toDir = FISH.dirct;
						// 상어 경로 설정
						int toR = r;
						if (toDir == 1 || toDir == 2) {
							int toMoveVR = FISH.velocity; // % (2*R);
							for (int cr = 1 ; cr <= toMoveVR; cr++) {
								if (toR == 0) FISH.dirct = 2;
								if (toR == R-1) FISH.dirct = 1;
								toR += dr[FISH.dirct];
							}							
						}
						int toC = c;
						if (toDir == 3 || toDir == 4) {
							int toMoveVC = FISH.velocity; // % (2*C);
							for (int cc = 1 ; cc <= toMoveVC; cc++) {
								if (toC == 0) FISH.dirct = 3;
								if (toC == C-1) FISH.dirct = 4;
								toC += dc[FISH.dirct];
							}
						}						
						// 상어 배치 or 잡아먹기
						if (copySea[toR][toC] == null) { copySea[toR][toC] = FISH; }
						else { 
							fish ohno = copySea[toR][toC];
							copySea[toR][toC] = (ohno.size > FISH.size ? ohno : FISH); 
						}						
					}
				}
			}
			
			sea = copySea;
			
		}
		
		System.out.println(SIZE_SUM);
		
	}
}
