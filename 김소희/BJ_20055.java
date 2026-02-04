package algorithm;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class 컨베이어벨트위의로봇_20055 {

	static class Belt {
		int belt;
		boolean robot;
		
		public Belt(int belt, boolean robot) {
			this.belt = belt;
			this.robot = robot;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		
//		1번 칸이 있는 위치를 "올리는 위치", N번 칸이 있는 위치를 "내리는 위치"

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]), K = Integer.parseInt(input[1]);
		int L = 2*N;
		
		Belt[] C_Belt = new Belt[N*2]; 
		int index = 0;
		for (String s : br.readLine().split(" "))
			C_Belt[index++] = new Belt(Integer.parseInt(s), false);

		int count = 0, point = 0, step = 0;
		while (K > 0) {
			
			step++;
				
			// Step1 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			point--;
			if (point < 0) point = 2*N-1;
			C_Belt[((point+N)%L)].robot = false;	// 가장 끝의 로봇 내보내기
			
			// Step2 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 다음 칸의 내구도가 0이라면 가만히 있는다.
			C_Belt[((point+N-1)%L)].robot = false;	// 가장 끝의 로봇 내보내기
			for (int i = N-1; i > 0; i--) {				 
				if (C_Belt[((point+i)%L)].belt > 0 && C_Belt[((point+i)%L)].robot == false && C_Belt[((point+i-1)%L)].robot == true) {
					C_Belt[((point+i)%L)].robot = true;
					C_Belt[((point+i-1)%L)].robot = false;
					if (--C_Belt[((point+i)%L)].belt == 0) count++;  					
				}
			}
			
			// Step3 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (C_Belt[point].belt > 0) {
				if (--C_Belt[point].belt == 0) count++;  	
				C_Belt[point].robot = true;
			}
			
			// Step4 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if (count >= K) break;				

		}
		
//		종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자.
		System.out.println(step);	
	
	}
	
}
