package test;

import java.util.*;
import java.io.*;

public class BJ_1644 {
	static int N;
	static List<Integer> graph;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		graph = new ArrayList<>();
		
		boolean[] num = new boolean[N+1];

		for(int i=2; i<=N;i++) {
			if(num[i]) continue;
			int ind = 1;
			graph.add(i);
			while(i*ind <= N) {
				num[i*ind] = true;
				ind++;
			}
		}
		
		int s = 0;
		int e = 0;
		
		int cnt = 0;
		if(graph.size()==0) {
			System.out.println(0);
			return;
		}
		int sum = graph.get(s);
		

		
		while(true) {
		
			if(sum == N) {
				cnt++;
				e++;
				if(e== graph.size()) break;
				sum += graph.get(e);
				
			}
			else if(sum < N) {
				e++;
				if(e== graph.size()) break;
				sum += graph.get(e);
				
			}
			else {
			
				sum -= graph.get(s);
				s++;
			}
		}
		

		System.out.println(cnt);
	}
}
