import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week2 BJ_17141

class State{
	int y, x, t;
	State(int y, int x, int t){
		this.y = y;
		this.x = x;
		this.t = t;
	}
}

public class Main {
	public static Scanner sc = new Scanner(System.in);
    static int N, M, ans = -1, total;
    static int[][] info, virus;
    static int[] dy = {0,0,1,-1}, dx = {1,-1,0,0}, selected;
    static Queue<State> q;
    static List<State> start;
    public static void main(String[] args)  {
    	start = new ArrayList<>();
    	N = sc.nextInt();
    	M = sc.nextInt();
    	selected = new int[M];
    	
    	info = new int[N][N]; 	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			info[i][j] = sc.nextInt();
    			if(info[i][j] != 1) total++;
                if(info[i][j] ==2) {
    				start.add(new State(i,j,0));
    			}
    		}
    	}
    	total-=M;
    	
    	visited= new int[start.size()+1];
    	select(0, 0); // m 개의 연구소를 선택한다. 
    	System.out.println(ans);	
    }
    
    public static void select(int idx, int cnt) {
    	
    	// M 개의 위치를 선택했다면 
    	if(cnt == M) {
    		int cur_ans = BFS(); // BFS 수행
    		if(ans == -1) ans = cur_ans;
    		else if(cur_ans != -1) ans = Math.min(cur_ans, ans);
    		return;
    	}
    	
    	for(int i= idx; i < start.size(); i++) {
    		selected[cnt] = i;
    		select(i+1, cnt+1);
    	}
    }
    
    
    public static int BFS() {
    	int cur_total = total;
      if(total == 0) return 0;
        
    	q = new LinkedList<>();
    	virus = new int[N][N];
    	for(int i=0; i<M; i++) {
    		State s = start.get(selected[i]);
    		virus[s.y][s.x] = -1; 
    		q.add(s);
    	}

    	while(!q.isEmpty()) {
    		State cur = q.poll();

    		for(int i=0; i<4; i++) {
  			  int ny = cur.y + dy[i];
  			  int nx = cur.x + dx[i];
  			  
  			  if(ny < 0 || ny >=N || nx <0 || nx >= N) continue;
  			  if(info[ny][nx] == 1 || virus[ny][nx] !=0 ) continue;
  			  
  			  virus[ny][nx] = cur.t+1;
  			  cur_total--; // 남은 개수를 줄인다.
  			  if(cur_total == 0) return cur.t + 1;
  			  q.add(new State(ny, nx, cur.t+1));
  		  }
    	}
    	return -1;
    } 
}
