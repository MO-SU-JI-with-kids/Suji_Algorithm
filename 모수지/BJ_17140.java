import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week1 BJ_17140

class State{
	int num, cnt;
	
	State(int num, int cnt){
		this.num = num;
		this.cnt = cnt;
	}
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int r, c, k, y_size, x_size ;
    static int[][] list = new int[101][101];
    static int[][] cnt_y = new int[101][101];
    static int[][] cnt_x = new int[101][101];
    public static void main(String[] args) throws IOException {
    	String s = br.readLine();
    	StringTokenizer st = new StringTokenizer(s);
    	r = Integer.parseInt(st.nextToken()) -1; // 실제 배열 인덱스는 0부터 시작함.
    	c = Integer.parseInt(st.nextToken()) -1;
    	k = Integer.parseInt(st.nextToken()); 
    	
    	for(int i=0; i<3; i++) {
    		List<Integer> li = new ArrayList<>();
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<3; j++) {
    			int cur = Integer.parseInt(st.nextToken());
    			list[i][j] = cur;
    			cnt_y[i][cur]++;
    			cnt_x[j][cur]++;
    		}
    	}
    	
    	y_size = 3; x_size = 3;
    	
    	// 100초 동안 진행함.
    	int t = 0;
    	while(t<=100) {
    		if(y_size >= r && x_size >= c && list[r][c] == k) break;// 검사하기
    		if(y_size >= x_size) toR(); // R 연산 수행
    		else toC();// C 연산
    		t++;
    	}
    	
    	if(t > 100) System.out.println(-1);
    	else System.out.println(t);
    }
    
    
    // cnt_y 조사하기 -> x_size 변화함
    public static void toR() {
    	PriorityQueue<State> pq = new PriorityQueue<>(
    			(s1,s2)->{
    				if(s1.cnt == s2.cnt) return s1.num - s2.num;
    				return s1.cnt - s2.cnt;
    			});
    	
    	int max_x = 0;
    	int[][] next = new int[101][101];
    	for(int i=0; i<y_size; i++) {
    		for(int j=1; j<=100; j++) {
    			if(cnt_y[i][j]!=0) pq.add(new State(j, cnt_y[i][j]));
    		}
    		
    		int pq_cnt = 0;
    		while(!pq.isEmpty() && pq_cnt <= 100) {
    			State cur = pq.poll();
    			next[i][pq_cnt] = cur.num;
    			next[i][pq_cnt+1] = cur.cnt;
    			pq_cnt+=2;
    		}
    		if(pq_cnt >=100) pq_cnt = 100;
    		max_x = Math.max(max_x, pq_cnt);
    	}
    	
    	x_size = max_x;
    	list = next;
    	updateCnt();
    }
    
    
    // cnt_x 조사하기 -> y_size 변화함
    public static void toC() {
    	PriorityQueue<State> pq = new PriorityQueue<>(
    			(s1,s2)->{
    				if(s1.cnt == s2.cnt) return s1.num - s2.num;
    				return s1.cnt - s2.cnt;
    			});
    	
    	int max_y = 0;
    	int[][] next = new int[101][101];
    	for(int i=0; i<x_size; i++) {
    		for(int j=1; j<=100; j++) {
    			if(cnt_x[i][j]!=0) pq.add(new State(j, cnt_x[i][j]));
    		}
    		
    		int pq_cnt = 0;
    		while(!pq.isEmpty() && pq_cnt <= 100) {
    			State cur = pq.poll();
    			next[pq_cnt][i] = cur.num;
    			next[pq_cnt+1][i] = cur.cnt;
    			pq_cnt+=2;
    		}
    		if(pq_cnt >=100) pq_cnt = 100;
    		max_y = Math.max(max_y, pq_cnt);
    	}
    	
    	y_size = max_y;
    	list = next;
    	updateCnt();
    }
    
    public static void updateCnt() {
    	cnt_y = new int[101][101];
    	cnt_x = new int[101][101];

    	for(int i=0; i<y_size; i++) {
    		for(int j=0; j<x_size; j++) {
    			int cur = list[i][j];
    			cnt_y[i][cur]++;
    			cnt_x[j][cur]++;
    		}
    	}
    }
}
