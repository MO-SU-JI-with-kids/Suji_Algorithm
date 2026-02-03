package test;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class BJ_17143 {
	static class Shark {
		int x,y,s,d,z;

		public Shark(int x, int y, int s, int d, int z) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static int R,C;
	static List<Shark> map;
	
	static int[] dx = {-1,1,0,0};

	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			map.add(new Shark(x,y,s,d,z));
		}	
		
		int ans = 0;
		for(int i=0;i<C;i++) {
			
			int curC = i;
			ans += map.stream().filter(shark -> shark.y==curC)
						.min(Comparator.comparingInt(shark -> shark.x))
						.map(target -> {
							map.remove(target);
							return target.z;
						})
						.orElse(0);
			
			for (Shark s : map) {
			    int move = s.s;
			    if(s.d == 0 || s.d == 1) {
			    	move %= (R-1) *2; 
			    	
			    	while(move>0) {
			    		int nx = s.x + dx[s.d];
			    		if(nx == R) {
			    			s.d = 0;
			    			nx = R-2;
			    		}
			    		else if(nx == -1) {
			    			s.d = 1;
			    			nx = 1;
			    		}
			    		
			    		s.x = nx;
			    		move--;
			    	}
			    	
			    }
			    
			    else {
			    	move %= (C-1) *2; 	
			    	
			    	while(move>0) {
			    		int ny = s.y + dy[s.d];
			    		if(ny == C) {
			    			s.d = 3;
			    			ny = C-2;
			    			
			    		}
			    		else if(ny == -1) {
			    			s.d = 2;
			    			ny = 1;
			    		}
			    		
			    		s.y = ny;
			    		move--;
			    	}
			    }
			}
			
			map = map.stream()
					 .collect(Collectors.toMap(
							 s -> s.x + "," +s.y,
							 s -> s,
							 (s1, s2) -> s1.z > s2.z ? s1 : s2))
					 .values()
					 .stream()
					 .collect(Collectors.toList());
		}
		
		System.out.println(ans);
	}
	
}
