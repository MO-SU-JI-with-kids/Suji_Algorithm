import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week2 BJ_2178

class State{
	int y, x, cnt;
	
	State(int y, int x, int cnt){
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}


public class Main {
	
    static int N, M;
    static int[][] info, visited;
    static int[] dy = {0,0,1,-1}, dx = {1,-1,0,0};
    public static void main(String[] args)  {
    	
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt(); M = sc.nextInt();
    	info = new int[N][M]; visited= new int[N][M];
    	for(int i=0; i<N; i++) {
    		String s = sc.next();
    		for(int j=0; j<M; j++) info[i][j] = s.charAt(j)-'0';
    	}
    	
    	Queue<State> q = new LinkedList<>();
    	q.add(new State(0,0,1));
    	visited[0][0] = 1;
    	while(!q.isEmpty()) {
    		State cur = q.poll();
    		if(cur.y == N-1 && cur.x == M-1) {
    			System.out.println(cur.cnt);
    			return;
    		}
    		
    		for(int i=0; i<4; i++) {
    			int ny = cur.y + dy[i], nx = cur.x + dx[i];
    			if(ny<0 || ny >=N || nx <0 || nx>=M) continue;
    			if(visited[ny][nx] == 1 || info[ny][nx] == 0) continue;
    			visited[ny][nx] = 1;
    			q.add(new State(ny, nx, cur.cnt+1));
    		}
    	}
    	System.out.println("-1");
    }
