import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week2 AI 로봇청소기 


class Cleaner{
	int y, x;
	Cleaner(int y, int x){
		this.y = y;
		this.x = x;
	}
}

class Position{
	int y, x, t;
	Position(int y, int x, int t){
		this.y = y;
		this.x = x;
		this.t = t;
	}
}

class Area{
	int d,sum;
	Area(int d, int sum){
		this.d = d;
		this.sum = sum;
	}
}


public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	static int N, K, L;
	static int[][] info, visited;
	static Cleaner[] cleaner;
	static int[] dy = {0,1,0,-1}, dx = {1,0,-1,0};
	static int[][] direction = {
			{0,1,3}, // 오른쪽 바라보기
			{1,0,2}, // 아래 바라보기
			{2, 1, 3}, // 왼 바라보기
			{3, 0, 2} // 위 바라보기
	};
	
    public static void main(String[] args)  {
    	N = sc.nextInt();
    	K = sc.nextInt();
    	L = sc.nextInt();
    	info = new int[N][N];
    	cleaner = new Cleaner[K];
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) info[i][j] = sc.nextInt();
    	}

    	for(int i=0; i<K; i++) {
    		int y = sc.nextInt();
    		int x = sc.nextInt();
    		cleaner[i] = new Cleaner(y-1,x-1);
    	}
    	
    	for(int i=0; i<L; i++) {
    		clean();
    	}
    }
    
    // 테스트 시작
    public static void clean() {
    	visited = new int[N][N]; // 청소기의 위치 저장 배열

    	//1. 각각의 청소기 이동
    	for(int i=0; i<K; i++) cleaner_move(i); // i번째 청소기를 움직인다.

    	// 2. 청소하기
    	for(int i=0; i<K; i++) do_clean(i);    	

    	// 3. 먼지 축적
    	dustdust();

    	// 4.먼지 확산 
    	diffuse();

    	// 전체 먼지의 양 더하기 
    	System.out.println(cnt_total());
    }
    
    public static int cnt_total() {
    	
    	int ans = 0;
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) if(info[i][j] > 0) ans += info[i][j];
    	}
    	return ans;
    }
    
    
    public static void diffuse() {
    	
    	int[][] before = new int[N][N];
    	for(int i=0; i<N; i++) {
    		System.arraycopy(info[i],0, before[i], 0, N); // 배열 복사
    	}
    	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			
    			// 빈 공간이라면 
    			if(info[i][j] == 0) {
    				int sum = 0;
    				for(int d=0; d<4; d++) {
    					int ny = i + dy[d];
    					int nx = j + dx[d];
    					if(ny < 0 || ny >= N || nx < 0 || nx>=N) continue;
    					if(before[ny][nx] < 0) continue; // 물제 있는 공간이라면 무시 
    					sum += before[ny][nx];
    				}
    				
    				sum/=10;
    				info[i][j] += sum; // 먼지량 더해주기
    			}
    		}	
    	}
    }
    
    
    public static void dustdust() {
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			// 먼지가 있는 곳이라면
    			if(info[i][j]>0) info[i][j] += 5; // 먼지량 5씩 증가시키기 			
    		}
    	}
    }

    
    // 청소를 한다 = 먼지를 줄인다.
    public static void do_clean(int num) {
    	Cleaner cur = cleaner[num];
    	
    	Area[] area = new Area[4]; // 4 방향에 대한 합 구하기 
    	for(int i=0; i<4; i++) {
    		int cur_sum = 0;
    		
    		
    		for(int j=0; j<3; j++) {
    			int d = direction[i][j];
    			int ny = cur.y + dy[d], nx = cur.x + dx[d];
    			if(ny < 0 || ny >= N || nx < 0 || nx>=N) continue; //  범위 벗어나는 경우 
    			if(info[ny][nx] == -1) continue;
    			
    			// 실제 청소할 수 있는 먼지량의 한계 = 20 : 난 바보인가봐... 
    			if(info[ny][nx] >20) cur_sum+= 20;
    			else cur_sum+= info[ny][nx]; // 먼지의 양을 더한다.
    		}
    		
    		area[i] = new Area(i, cur_sum); // 방향값, 해당 방향에서의 먼지 양 합 저장	
    	}
    	// Area 정렬 -> 최적화 가능. 어차피 하나만 알면 되어서..
    	Arrays.sort(area, (a1, a2)-> {
    		if(a1.sum == a2.sum) {
    			return a1.d - a2.d;
    		} return a2.sum - a1.sum;
    	});
    	
    	// 확정된 방향 = 0번 원소 
    	Area selected = area[0];    	
    	
    	// 현재 위치의 먼지 양 먼저 줄이기
    	info[cur.y][cur.x] = Math.max(0,info[cur.y][cur.x]-20 );
    	for(int i=0; i<3; i++) {
    		int d = direction[selected.d][i];
			int ny = cur.y + dy[d], nx = cur.x + dx[d];
			if(ny < 0 || ny >= N || nx < 0 || nx>=N) continue; //  범위 벗어나는 경우 
			if(info[ny][nx] == -1) continue;
			info[ny][nx] = Math.max(0, info[ny][nx]-20);
    	}	
    }
    
    // 청소기 이동시키기 
    // 먼지  있는 곳까지 이동 못할 수도 있음! 유의하자.
    public static void cleaner_move(int num) {
    	boolean[][] cur_visited = new boolean[N][N];
    	List<Position> list = new ArrayList<>(); // 최단 거리 위치 저장할.. 
    	Cleaner cur = cleaner[num]; // 현재 청소기
    	boolean done = false;
    	
    	// BFS 이용해 움직이기 
    	Queue<Position> q  = new ArrayDeque<>();
    	q.add(new Position(cur.y, cur.x, 0)); // 현재 위치 넣기
    	cur_visited[cur.y][cur.x] = true; // 방문 표시
    	
    	//! 현재 모든 청소기의 위치를 방문 표시해야함
    	for(int i=0; i<K; i++) {
    		Cleaner c = cleaner[i];
    		cur_visited[c.y][c.x] = true;
    	}
    	
    	
    	int y=cur.y, x=cur.x; // 끝까지 도착 못 했을 경우에 대한..
    	int minDist = Integer.MAX_VALUE;
    	while(!q.isEmpty()) {
    		Position cur_pos = q.poll();

    		
    		if(cur_pos.t > minDist) break;
    		
    		// 먼지가 있으면서 아직 다른 청소기가 없는 경우
    		if(info[cur_pos.y][cur_pos.x] >0 && visited[cur_pos.y][cur_pos.x]==0) {
    			list.add(cur_pos);
    			minDist = cur_pos.t;			
    			continue;
    		}
    		
    		else {
        		for(int i=0; i<4; i++) {
        			int ny = cur_pos.y + dy[i];
        			int nx = cur_pos.x + dx[i];
        			
        			if(ny < 0 || ny >= N || nx < 0 || nx>=N) continue; //  범위 벗어나는 경우 
        			if(visited[ny][nx] == 1) continue; // 이미 다른 청소기가 위치하고 있는 곳인 경우 종료
        			if(cur_visited[ny][nx] || info[ny][nx] == -1) continue; // 이미 방문 한 곳이라면 종료 + 장애물이라면 종료
        			q.add(new Position(ny, nx, cur_pos.t+1));
        			cur_visited[ny][nx] = true; // 방문 표시
        		}	
    		}
    	}	
    	
    	
    	if(list.size() !=0) {
    		if(list.size() >1) {
    			Collections.sort(list, (p1, p2)->{
    				if(p1.t == p2.t) {
    					if(p1.y == p2.y) {
    						return p1.x - p2.x;
    					}return p1.y - p2.y;
    				}return p1.t - p2.t;
    			});
    		}
    		Position sol = list.get(0);
    		visited[sol.y][sol.x] = 1;
    		cur.y = sol.y;
    		cur.x = sol.x;
    		
    	}
    	
    	else {
    		// while 에서 return 하지 못했다면 마지막 위치까지 가지 못함.
        	// 마지막 위치를 저장 
        	cur.y = y;
        	cur.x = x;
        	visited[cur.y][cur.x] = 1;
    	}

    }  
}
