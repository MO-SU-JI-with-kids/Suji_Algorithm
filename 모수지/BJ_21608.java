import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week2 BJ_21608
class State{
	int y, x;
	int empty, like;
	
	State(int y, int x, int empty, int like){
		this.y = y;
		this.x = x;
		this.empty = empty;
		this.like = like;
	}
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, cur, like_cnt, empty_cnt, score;
    static int[][] map;
    static int[][] info;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    
    public static void main(String[] args) throws NumberFormatException, IOException  {
    	N = Integer.parseInt(br.readLine());
    	info = new int[N+1][N+1];
    	map = new int[N*N+1][N*N+1];
    	for(int i=0; i<N*N; i++) {
    		String s = br.readLine();
    		StringTokenizer st = new StringTokenizer(s);
    		cur = Integer.parseInt(st.nextToken());
    		
    		for(int j=0; j<4; j++) map[cur][Integer.parseInt(st.nextToken())] = 1;		
    		seat(); // 앉힌다.
    	}

    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			int student = info[i][j];
    			int cur_score = 0;
    			for(int k =0; k<4; k++) {
    				int ny = i + dy[k]; int nx = j + dx[k];
    				if(ny <0 || ny >= N || nx<0 || nx>=N) continue;
    				cur_score += map[student][info[ny][nx]];
    			}
    			
    			if(cur_score == 2) cur_score = 10;
    			else if(cur_score == 3) cur_score = 100;
    			else if(cur_score == 4) cur_score = 1000;
    			
    			score+=cur_score;
    		}
    	}
    	System.out.println(score);
    }
    
    public static void seat() {
    	
    	PriorityQueue<State> pq = new PriorityQueue<>(
    			// 랑다 구현 
    			(a,b)->{
    				if(a.like == b.like) {
    					if(a.empty == b.empty) {
    						if(a.y == b.y) return a.x - b.x;
    						return a.y - b.y;
    					}
    					return b.empty - a.empty;
    				}
    				return b.like - a.like;
    			});
    	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			
    			// 아직 아무도 앉지 않은 경우 
    			if(info[i][j] == 0) {
    				check(i, j);
    				pq.add(new State(i,j,empty_cnt,like_cnt));
    			}
    		}
    	}
    	
    	State front = pq.poll();
    	info[front.y][front.x] = cur; // 자리에 앉힌다.
    }
    
    public static void check(int y, int x) {
    	like_cnt = 0; empty_cnt = 0;
    	for(int i=0; i<4; i++) {
    		int ny = y + dy[i];
    		int nx = x + dx[i];
    		if(ny <0 || ny >= N || nx<0 || nx>=N) continue;
    		if(info[ny][nx] == 0) empty_cnt++;
    		else like_cnt += map[cur][info[ny][nx]];
    	}
    }
}
